package com.example.fakestoreapp.domain.usecases

import com.example.fakestoreapp.core.entities.ProductItem

interface ProductsUseCase {

    suspend operator fun invoke(isForceUpdate: Boolean = false): List<ProductItem>
    
}