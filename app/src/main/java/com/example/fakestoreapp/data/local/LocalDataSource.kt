package com.example.fakestoreapp.data.local

import com.example.fakestoreapp.core.entities.ProductItem

interface LocalDataSource {

    suspend fun getProductsFromCache(): List<ProductItem>

    suspend fun clearCache()

    suspend fun cacheProductsList(list: List<ProductItem>)


}