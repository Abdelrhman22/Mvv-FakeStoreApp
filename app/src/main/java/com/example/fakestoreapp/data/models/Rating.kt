package com.example.fakestoreapp.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    @SerializedName("rate") var rate: Double? = null,
    @SerializedName("count") var count: Int? = null
) {

    fun getRateValue(): Int {
        val scaledRate = (rate?.div(5))?.times(100)
        return scaledRate?.toInt() ?: 0
    }

}