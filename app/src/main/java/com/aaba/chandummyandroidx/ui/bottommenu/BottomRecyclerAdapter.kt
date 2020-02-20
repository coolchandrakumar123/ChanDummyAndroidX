package com.aaba.chandummyandroidx.ui.bottommenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aaba.chandummyandroidx.R

/**
 * Created by chandra-1765$ on 18/02/20$.
 */
class BottomRecyclerAdapter: RecyclerView.Adapter<BottomRecyclerAdapter.ItemViewHolder>() {

    private val data: ArrayList<String> = arrayListOf()

    fun setList(items: ArrayList<String>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bottom_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind((data[position]))
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(data: String) {
            itemView.findViewById<TextView>(R.id.label).text = data
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "Item Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}