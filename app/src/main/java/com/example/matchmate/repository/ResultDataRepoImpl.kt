package com.example.matchmate.repository

import com.example.matchmate.dao.ResultsDao

import com.example.matchmate.model.CardModelTransformation

class ResultDataRepoImpl(private val resultsDao: ResultsDao) : ResultsRepository {
    override fun getAllResults(): List<CardModelTransformation> {
        return resultsDao.getAllResult()
    }

    override suspend fun insertResult(result: CardModelTransformation) {
        resultsDao.insertResult(result)
    }

    override suspend fun deleteResult(result: CardModelTransformation) {
        resultsDao.deleteResult(result)
    }

}
