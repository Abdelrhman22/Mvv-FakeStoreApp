package com.example.fakestoreapp.data.repository

import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.data.local.LocalDataSource
import com.example.fakestoreapp.data.remote.RemoteDataSource
import com.example.fakestoreapp.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ProductsRepository {
    override suspend fun getProducts(isForceUpdate: Boolean): List<ProductItem> {

        val cachedProducts = localDataSource.getProductsFromCache()
        if (cachedProducts.isNotEmpty() && !isForceUpdate)
            return cachedProducts

        val productsFromServer = remoteDataSource.getProductsFromServer()
        val listToBeCached = productsFromServer.map {
            it.asEntity()
        }
        localDataSource.clearCache()
        localDataSource.cacheProductsList(listToBeCached)
        return listToBeCached
    }
}