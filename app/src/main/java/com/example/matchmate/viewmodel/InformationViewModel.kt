package com.example.matchmate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmate.matchui.LoaderManager
import com.example.matchmate.model.CardModelTransformation
import com.example.matchmate.model.DataManager
import com.example.matchmate.network.RetroInstance
import com.example.matchmate.network.RetroService
import com.example.matchmate.repository.ResultsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InformationViewModel(private val repository: ResultsRepository) : ViewModel() {

    private val cardModelTransformationList = ArrayList<CardModelTransformation>()
    private val loaderManager = LoaderManager()
    var recyclerListLiveData: MutableLiveData<DataManager> = MutableLiveData()
    var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getRecyclerListObserver(): MutableLiveData<DataManager> {
        return recyclerListLiveData
    }

    fun makeApiCall() {
        loaderManager.showLoader()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.fetchData(10)

                if (response.isSuccessful) {
                    for (items in response.body()?.results ?: emptyList()) {
                        cardModelTransformationList.add(
                            CardModelTransformation(
                                items.name.first,
                                items.name.last,
                                items.location.street.number,
                                items.location.country,
                                items.picture.large
                            )
                        )
                    }

                    val dataManager = DataManager(cardModelTransformationList)
                    recyclerListLiveData.postValue(dataManager)
                    loaderManager.hideLoader()

                    for (items in cardModelTransformationList) {
                        repository.insertResult(items)
                    }
                } else {
                    errorLiveData.postValue("API call failed: ${response.message()}")
                    loaderManager.hideLoader()
                }
            } catch (e: Exception) {
                errorLiveData.postValue("An error occurred: ${e.message}")
                loaderManager.hideLoader()
            }
        }
    }

    fun loadFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val resultsEntity = repository.getAllResults()
                val dataManager = DataManager(resultsEntity as ArrayList<CardModelTransformation>)
                recyclerListLiveData.postValue(dataManager)
            } catch (e: Exception) {
                errorLiveData.postValue("Error loading data from repository: ${e.message}")
            }
        }
    }
}
