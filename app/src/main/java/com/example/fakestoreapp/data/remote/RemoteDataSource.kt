package com.example.fakestoreapp.data.remote

import com.example.fakestoreapp.data.models.ProductItemRemote

interface RemoteDataSource {

    suspend fun getProductsFromServer() : List<ProductItemRemote>

}