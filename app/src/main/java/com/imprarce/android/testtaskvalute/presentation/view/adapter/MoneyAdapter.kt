package com.imprarce.android.testtaskvalute.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtaskvalute.R
import com.imprarce.android.testtaskvalute.data.model.MoneyItem

class MoneyAdapter(private val moneyList: List<MoneyItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_money_header, parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_money_recycler_view, parent, false)
                MoneyViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MoneyViewHolder) {
            val currentItem = moneyList[position - 1]
            holder.bind(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return moneyList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_HEADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class MoneyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val charCodeTextView: TextView = itemView.findViewById(R.id.char_code)
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val nominalTextView: TextView = itemView.findViewById(R.id.nominal)
        private val valueTextView: TextView = itemView.findViewById(R.id.value)

        fun bind(item: MoneyItem) {
            charCodeTextView.text = item.charCode
            nameTextView.text = item.name
            nominalTextView.text = item.nominal
            valueTextView.text = item.value
        }
    }
}