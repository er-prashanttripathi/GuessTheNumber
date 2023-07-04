package com.example.guessthenumber

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RcvAdapter(private val msg: List<String>) : RecyclerView.Adapter<RcvAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val msgtxt: TextView = itemView.findViewById(R.id.txtrcv)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on ${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(item: String) {
            msgtxt.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(msg[position])
    }

    override fun getItemCount(): Int {
        return msg.size
    }
}

