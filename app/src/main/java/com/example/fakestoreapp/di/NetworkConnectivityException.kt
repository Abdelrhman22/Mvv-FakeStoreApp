package com.example.fakestoreapp.di

import java.io.IOException

class NetworkConnectivityException(val errorMessage: String) :
    IOException() {
}