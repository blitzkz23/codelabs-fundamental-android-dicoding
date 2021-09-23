package com.app.myactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

//	This function is used to inflate menu
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		val inflater = menuInflater
		inflater.inflate(R.menu.option_menu, menu)

//	    Create listener for search view
		val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
		val searchView = menu!!.findItem(R.id.search).actionView as SearchView

		searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
		searchView.queryHint = resources.getString(R.string.search_hint)
		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//			This function is used after we submit the search text
			override fun onQueryTextSubmit(query: String?): Boolean {
				Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT)
				return true
			}

//			This function is gonna respond on every change of word that we enter while searching
			override fun onQueryTextChange(newText: String?): Boolean {
				return false
			}
		})
		return true
	}

//	Event listener for menu
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.menu1 -> {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, MenuFragment())
					.addToBackStack(null)
					.commit()
				return true
			}

			R.id.menu2 -> {
				val intent = Intent(this, MenuActivity::class.java)
				startActivity(intent)
				return true
			}
			else -> return true
		}
	}
}