package com.example.fakestoreapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.di.NetworkConnectivityException
import com.example.fakestoreapp.domain.usecases.ProductsUseCase
import com.example.fakestoreapp.presentation.ui.interfaces.RetryCallBack
import com.example.fakestoreapp.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsUseCase: ProductsUseCase) :
    ViewModel() {

    private val _products = MutableStateFlow<Resource<List<ProductItem>>>(Resource.loading())
    val products: Flow<Resource<List<ProductItem>>> get() = _products


    init {
        getProducts()
    }
    /*
    private fun getProducts(isForced: Boolean = false) {
        viewModelScope.launch {
            try {
                _products.emit(Resource.loading())
                val products = productsUseCase.invoke(isForced)
                _products.emit(Resource.success(response = products))
            } catch (networkException: NetworkConnectivityException) {
                _products.emit(Resource.error(error = networkException))
            } catch (ex: Exception) {
                _products.emit(Resource.error(error = ex))
            }
        }
    }
     */

    private fun getProducts(isForced: Boolean = false) = viewModelScope.launch {
        flowOf(productsUseCase.invoke(isForced))
            .onStart { _products.emit(Resource.loading()) }
            .catch { _products.emit(Resource.error(error = it)) }
            .collect { _products.emit(Resource.success(response = it)) }
    }

    fun retry(): RetryCallBack {
        return object : RetryCallBack {
            override fun invoke() {
                getProducts(isForced = true)
            }
        }
    }

}