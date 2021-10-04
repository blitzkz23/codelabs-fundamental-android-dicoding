package com.app.restaurantreview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val listReview: List<String>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val tvItem: TextView = itemView.findViewById(R.id.tvItem)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.tvItem.text = listReview[position]
	}

	override fun getItemCount(): Int = listReview.size
}