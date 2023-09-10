package com.example.fakestoreapp.data.local

import androidx.room.TypeConverter
import com.example.fakestoreapp.data.models.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RatingConverter {

    @TypeConverter
    fun convertJsonToRatingModel(json: String?): Rating? {
        if (json.isNullOrEmpty()) {
            return null
        }
        val type = object : TypeToken<Rating>() {}
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun convertFromRatingModelToJson(rating: Rating?): String? {
        if (rating == null)
            return null
        return Gson().toJson(rating)
    }

}