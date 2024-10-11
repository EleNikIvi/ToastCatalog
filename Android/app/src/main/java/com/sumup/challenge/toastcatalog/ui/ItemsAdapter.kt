package com.sumup.challenge.toastcatalog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sumup.challenge.toastcatalog.R
import com.sumup.challenge.toastcatalog.domain.model.ItemModel
import com.sumup.challenge.toastcatalog.ui.core.ItemUtils.setRoundedTextViewStyle

internal class ItemsAdapter :
    ListAdapter<ItemModel, ItemsAdapter.ViewHolder>(
        ItemDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImage = itemView.findViewById<TextView>(R.id.tv_item_id)
        private val itemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        private val itemPrice = itemView.findViewById<TextView>(R.id.tv_item_price)
        private val itemDate = itemView.findViewById<TextView>(R.id.tv_item_date)

        fun bind(item: ItemModel) {
            itemName.text = item.name
            itemPrice.text =
                itemView.context.getString(R.string.item_price, item.price, item.currency)
            itemDate.text = item.lastSold
            setImage(item.id.toString())
        }

        private fun setImage(value: String) {
            itemImage.apply {
                setRoundedTextViewStyle()
                text = value
            }
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<ItemModel>() {

        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }
    }
}
