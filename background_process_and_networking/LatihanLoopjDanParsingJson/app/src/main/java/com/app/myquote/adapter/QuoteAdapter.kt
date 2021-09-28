package com.app.myquote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myquote.R

class QuoteAdapter(private val listQuote: ArrayList<String>)  : RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {
	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val tvItem: TextView = itemView.findViewById(R.id.tvItem)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.tvItem.text = listQuote[position]
	}

	override fun getItemCount(): Int {
		return listQuote.size
	}
}