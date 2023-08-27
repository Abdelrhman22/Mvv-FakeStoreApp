package com.example.fakestoreapp.di

import androidx.annotation.StringRes
import java.io.IOException

class NetworkConnectivityException(@StringRes private val errorMessageResourceId: Int) :
    IOException() {
}