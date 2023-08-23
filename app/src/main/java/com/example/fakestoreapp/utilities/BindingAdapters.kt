package com.example.fakestoreapp.utilities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.fakestoreapp.R

@BindingAdapter("imageURL") //
fun setImageFromURL(imageView: ImageView, imageURL: String?) {
    imageURL?.let {
        Glide.with(imageView.context)
            .load(it)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}

@BindingAdapter("intValue")
fun setIntValue(textView: TextView, value: Int?) {
    value?.let {
        textView.text = value.toString()
    }
}

@BindingAdapter("doubleValue")
fun setDoubleValue(textView: TextView, value: Double?) {
    value?.let {
        textView.text = value.toString()
    }
}


@BindingAdapter("visibleGone")
fun bindViewsVisibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}