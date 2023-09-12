package com.example.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matchmate.repository.ResultsRepository

class ViewModelFactory(private val repository: ResultsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InformationViewModel::class.java)) {
            return InformationViewModel(repository) as T

        }
        throw IllegalArgumentException("Unknown Exception")
    }

}