package com.example.fakestoreapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.utilities.Constants

@Dao
interface ProductsDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    suspend fun getProductsFromCache(): List<ProductItem>

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    suspend fun clearCache()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheProductsList(vararg productItem: ProductItem)
}