package com.example.fakestoreapp.presentation.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.fakestoreapp.core.entities.ProductItem

class ItemDiffCallBack(
    private val oldList: List<ProductItem>,
    private val newList: List<ProductItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}