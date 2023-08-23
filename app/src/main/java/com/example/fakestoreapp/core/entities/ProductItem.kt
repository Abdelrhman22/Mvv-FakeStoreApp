package com.example.fakestoreapp.core.entities

import androidx.room.Entity
import com.example.fakestoreapp.data.models.Rating
import com.example.fakestoreapp.utilities.Constants
import com.google.gson.annotations.SerializedName

@Entity(tableName = Constants.TABLE_NAME)
class ProductItem (

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("rating") var rating: Rating? = Rating()

)