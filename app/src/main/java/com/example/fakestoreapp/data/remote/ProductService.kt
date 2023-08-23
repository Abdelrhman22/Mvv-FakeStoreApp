package com.example.fakestoreapp.data.remote

import com.example.fakestoreapp.data.models.ProductItemRemote
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProducts(): List<ProductItemRemote>


}