package com.example.fakestoreapp.utilities

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.fakestoreapp.R

@BindingAdapter("imageURL" , "loader")
fun setImageFromURL(imageView: ImageView, iconUrl: String? , loader: LottieAnimationView) {
    iconUrl?.let {
        Glide.with(imageView.context)
            .load(it)
            .addListener(imageLoadingListener(loader))
            .error(R.drawable.ic_error)
            .into(imageView)
    }
}

private fun imageLoadingListener(pendingImage: LottieAnimationView): RequestListener<Drawable?>? {
    return object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            pendingImage.pauseAnimation()
            pendingImage.visibility = View.GONE
            return false
        }
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

@BindingAdapter("progressValue")
fun setProgressValue(progressBar: ProgressBar, value: Int?) {
    value?.let {
       progressBar.progress = it
    }
}


@BindingAdapter("showIfSuccessAndListEmpty")
fun <T> setState(view: View, resource: Resource<T>?) {
    resource?.let {
        val list = resource.response as? List<*>
        view.visibility =
            if (resource.status == Status.SUCCESS && list.isNullOrEmpty()) View.VISIBLE else View.GONE
    }
}