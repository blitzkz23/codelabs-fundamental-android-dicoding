package com.app.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.app.myrecyclerview.databinding.ItemRowHeroBinding
import com.bumptech.glide.Glide

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
//	Variable to set callback click
	private lateinit var onItemClickCallback: OnItemClickCallback

//  Untuk inheritance terhadap RecyclerView.ViewHolder ditambahkan secara manual
//	This class is consisted of the view of item row hero layout that are to be inflated on onCreateViewHolder
	class ListViewHolder(var binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root) {
//		var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//		var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//		var tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
	}

//	This function create a ViewHolder that will inflate the view
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
		val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ListViewHolder(binding)
	}

//	This function will bind the data into the viewholder based on the position of the data
	override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
		val (name, description, photo) = listHero[position]
		Glide.with(holder.itemView.context)
			.load(photo) // URL Gambar
			.circleCrop() // Mengubah image menjadi lingkaran
			.into(holder.binding.imgItemPhoto) // imageView mana yang akan diterapkan
		holder.binding.tvItemName.text = name
		holder.binding.tvItemDescription.text = description
		holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
	}

//	Size of datas
	override fun getItemCount(): Int = listHero.size

	interface OnItemClickCallback {
		fun onItemClicked(data: Hero)
	}

//	This function is gonna be overrided on MainActivity
	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}
}