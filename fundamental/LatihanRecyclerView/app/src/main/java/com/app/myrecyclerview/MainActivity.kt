package com.app.myrecyclerview

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
//	declare late variable
	private lateinit var rvHeroes: RecyclerView
	private val list = ArrayList<Hero>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		rvHeroes = findViewById(R.id.rv_heroes)
		rvHeroes.setHasFixedSize(true)

		list.addAll(listHeroes)
		showRecyclerList()
	}

//	Get the list of hero data
	private val listHeroes: ArrayList<Hero>
		get() {
			val dataName = resources.getStringArray(R.array.data_name)
			val dataDescription = resources.getStringArray(R.array.data_description)
			val dataPhoto = resources.getStringArray(R.array.data_photo)
			val listHero = ArrayList<Hero>()
			for (i in dataName.indices) {
				val hero = Hero(dataName[i],dataDescription[i], dataPhoto[i])
				listHero.add(hero)
			}
			return listHero
		}

//  Function to show the recycler list
	private fun showRecyclerList() {
//		If orientation is landscape set the layout to grid, else set to list view
		if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			rvHeroes.layoutManager = GridLayoutManager(this, 2)
		} else {
			rvHeroes.layoutManager = LinearLayoutManager(this)
		}

		val listHeroAdapter = ListHeroAdapter(list)
		rvHeroes.adapter = listHeroAdapter

//	    set item onclick callback
		listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
			override fun onItemClicked(data: Hero) {
				showSelectedHero(data)
			}
		})
	}

//	Function to show toast when recycler is clicked
	private fun showSelectedHero(hero: Hero) {
		Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
	}
}