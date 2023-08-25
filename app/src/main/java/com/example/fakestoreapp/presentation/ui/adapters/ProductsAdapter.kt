package com.example.fakestoreapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestoreapp.R
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.databinding.CardItemBinding

class ProductsAdapter(private val listener: OnItemClicked) :
    ListAdapter<ProductItem, ProductsAdapter.ViewHolder>(DIFF) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }


    interface OnItemClicked {
        fun onItemClicked(productItem: ProductItem)
    }

    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindItem(item: ProductItem) {
            with(binding) {
                root.setOnClickListener {
                    listener.onItemClicked(getItem(adapterPosition))
                }
                productItem = item
            }
        }
    }

    companion object {

        val DIFF = object : DiffUtil.ItemCallback<ProductItem>() {
            override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean =
                oldItem == newItem

        }


    }

}