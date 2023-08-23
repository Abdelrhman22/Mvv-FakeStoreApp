package com.example.fakestoreapp.domain.repository

import com.example.fakestoreapp.core.entities.ProductItem

interface ProductsRepository {

    suspend fun getProducts(isForceUpdate :Boolean = false) : List<ProductItem>

}