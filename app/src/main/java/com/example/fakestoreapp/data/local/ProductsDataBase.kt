package com.example.fakestoreapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fakestoreapp.core.entities.ProductItem
import com.example.fakestoreapp.utilities.Constants

// TODO try make exportSchema with false
@Database(entities = [ProductItem::class], version = 1, exportSchema = true)
abstract class ProductsDataBase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object {

        @Volatile
        private var INSTANCE: ProductsDataBase? = null

        private fun create(context: Context): ProductsDataBase {
            return Room.databaseBuilder(
                context,
                ProductsDataBase::class.java,
                Constants.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(mContext: Context): ProductsDataBase {
            synchronized(this) {
                var instance: ProductsDataBase? = INSTANCE
                if (instance == null)
                    instance = create(context = mContext)
                INSTANCE = instance
                return instance
            }
        }

    }

}