package com.example.fakestoreapp.utilities

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.show(visible: Boolean = true) {
    visibility = if (visible) View.VISIBLE else View.GONE
}


fun Fragment.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    view?.let { Snackbar.make(it, message, duration).show() }
}


fun AppCompatActivity.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    findViewById<View>(android.R.id.content)?.let {
        Snackbar.make(it, message, duration).show()
    }
}