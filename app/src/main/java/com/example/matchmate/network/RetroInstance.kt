package com.example.matchmate.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{
        val BaseURL="https://randomuser.me/"

        fun getRetroInstance():Retrofit{
            return  Retrofit.Builder()
                .baseUrl(BaseURL) // Base URL for the endpoint
                .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON serialization/deserialization
                .build()
        }
    }
}