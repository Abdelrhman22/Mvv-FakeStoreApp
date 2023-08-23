package com.example.fakestoreapp.core.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fakestoreapp.data.models.Rating
import com.example.fakestoreapp.utilities.Constants
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME)
class ProductItem (
    @PrimaryKey
    var id: Int? = null,
    var title: String? = null,
    var price: Double? = null,
    var description: String? = null,
    var category: String? = null,
    var image: String? = null,
    @Embedded // to save object
    var rating: Rating? = Rating()

) : Serializable