package com.example.fakestoreapp.core.usecases

import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.domain.repository.ProductsRepository
import com.example.fakestoreapp.domain.usecases.ProductsUseCase
import javax.inject.Inject

class ProductsUseCaseImp @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductsUseCase {
    override suspend fun invoke(isForceUpdate: Boolean): List<ProductItem> {
        return productsRepository.getProducts(isForceUpdate)
    }

}