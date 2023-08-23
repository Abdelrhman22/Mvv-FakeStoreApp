package com.example.fakestoreapp.data.remote

import com.example.fakestoreapp.data.models.ProductItemRemote
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val productService: ProductService) :
    RemoteDataSource {

    override suspend fun getProductsFromServer(): List<ProductItemRemote> {
        return productService.getProducts()
    }

}