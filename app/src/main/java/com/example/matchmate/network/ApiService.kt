package com.example.matchmate.network

import com.example.matchmate.model.CardModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("api/")
    suspend fun fetchData(@Query("results")count:Int): Response<CardModel>
}