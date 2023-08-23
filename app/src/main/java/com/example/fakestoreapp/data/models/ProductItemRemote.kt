package com.example.fakestoreapp.data.models

import com.example.fakestoreapp.core.entities.ProductItem
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductItemRemote(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("rating") var rating: Rating? = Rating()

) {
    fun asEntity(): ProductItem {
        return ProductItem(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = rating
        )
    }

}


