package com.example.fakestoreapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestoreapp.R
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.databinding.CardItemBinding

class ProductsAdapter(private val listener: OnItemClicked) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var products: List<ProductItem> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setList(newList: List<ProductItem>) {
        val diffResult = DiffUtil.calculateDiff(ItemDiffCallBack(products, newList))
        this.products = newList
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(products[position])
    }


    interface OnItemClicked {
        fun onItemClicked(productItem: ProductItem)
    }

    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindItem(productItem: ProductItem) {
            with(binding) {
                root.setOnClickListener {
                    listener.onItemClicked(products[adapterPosition])
                }
                Glide.with(root)
                    .load(productItem.image)
                    .placeholder(R.drawable.ic_loading) // Optional: Placeholder image while loading
                    .error(R.drawable.ic_error) // Optional: Error image if the URL is invalid
                    .into(binding.ivProduct)

                txtProductTitle.text = productItem.title
                txtProductPrice.text = productItem.price?.toString()

            }
        }

    }


}