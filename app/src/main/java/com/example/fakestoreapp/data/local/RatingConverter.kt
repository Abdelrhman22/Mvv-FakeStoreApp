package com.example.fakestoreapp.data.local

import androidx.room.TypeConverter
import com.example.fakestoreapp.data.models.Rating
import com.google.gson.Gson

class RatingConverter {

    @TypeConverter
    fun convertJsonToRatingModel(json: String?): Rating? {
        if (json.isNullOrEmpty()) {
            return null
        }
        return Gson().fromJson(json, Rating::class.java)
    }

    @TypeConverter
    fun convertFromRatingModelToJson(rating: Rating?): String? {
        if (rating == null)
            return null
        return Gson().toJson(rating)
    }

}