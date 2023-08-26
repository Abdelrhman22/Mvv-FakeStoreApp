package com.example.fakestoreapp.di

import android.content.Context
import com.example.fakestoreapp.core.usecases.ProductsUseCaseImp
import com.example.fakestoreapp.data.local.LocalDataSource
import com.example.fakestoreapp.data.local.LocalDataSourceImp
import com.example.fakestoreapp.data.local.ProductsDao
import com.example.fakestoreapp.data.local.ProductsDataBase
import com.example.fakestoreapp.data.remote.ProductService
import com.example.fakestoreapp.data.remote.RemoteDataSource
import com.example.fakestoreapp.data.remote.RemoteDataSourceImp
import com.example.fakestoreapp.data.repository.ProductsRepositoryImp
import com.example.fakestoreapp.domain.repository.ProductsRepository
import com.example.fakestoreapp.domain.usecases.ProductsUseCase
import com.example.fakestoreapp.utilities.Constants
import com.example.fakestoreapp.utilities.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ProductService =
        retrofit.create(ProductService::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: ProductService): RemoteDataSource =
        RemoteDataSourceImp(service)

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ProductsDataBase =
        ProductsDataBase.getInstance(context)

    @Provides
    @Singleton
    fun provideDao(productsDataBase: ProductsDataBase): ProductsDao = productsDataBase.productsDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(productsDao: ProductsDao): LocalDataSource =
        LocalDataSourceImp(productsDao)


    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): ProductsRepository = ProductsRepositoryImp(localDataSource, remoteDataSource)


    @Provides
    @Singleton
    fun provideUserCase(productsRepository: ProductsRepository): ProductsUseCase =
        ProductsUseCaseImp(productsRepository)


    // for network checker
    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker =
        NetworkChecker(context)

}