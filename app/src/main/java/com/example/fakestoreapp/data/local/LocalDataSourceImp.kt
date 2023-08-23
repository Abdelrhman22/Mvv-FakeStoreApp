package com.example.fakestoreapp.data.local

import com.example.fakestoreapp.core.entities.ProductItem
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val productsDao: ProductsDao) :
    LocalDataSource {

    override suspend fun getProductsFromCache(): List<ProductItem> {
        return productsDao.getProductsFromCache()
    }

    override suspend fun clearCache() {
        productsDao.clearCache()
    }

    override suspend fun cacheProductsList(list: List<ProductItem>) {
        // convert list before saved to DB
        productsDao.cacheProductsList(*list.toTypedArray())
    }
}