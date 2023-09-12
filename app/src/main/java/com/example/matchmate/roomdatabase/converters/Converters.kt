package com.example.matchmate.roomdatabase.converters

import androidx.room.TypeConverter
import com.example.matchmate.model.CardModelTransformation
import com.example.matchmate.model.Info
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun resultsToString(results: List<CardModelTransformation>): String {
        return gson.toJson(results)
    }

    @TypeConverter
    fun stringToResults(value: String): List<CardModelTransformation> {
        val listType = object : TypeToken<List<CardModelTransformation>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun infoToString(info: Info): String {
        return gson.toJson(info)
    }

    @TypeConverter
    fun stringToInfo(value: String): Info {
        return gson.fromJson(value, Info::class.java)
    }
}