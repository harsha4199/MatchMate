package com.example.matchmate.repository

import com.example.matchmate.model.CardModelTransformation

interface ResultsRepository {
    fun getAllResults(): List<CardModelTransformation>
    suspend fun insertResult(result: CardModelTransformation)
    suspend fun deleteResult(result: CardModelTransformation)

}