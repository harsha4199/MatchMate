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


    private val cardModelTransformationList =
        ArrayList<CardModelTransformation>()//Creating an empty arraylist
    private val loaderManager =LoaderManager()


    var recyclerListLiveData: MutableLiveData<DataManager> = MutableLiveData()


    fun getRecyclerListObserver(): MutableLiveData<DataManager> {

        return recyclerListLiveData
    }

    fun makeApiCall() {
        loaderManager.showLoader()
        viewModelScope.launch(Dispatchers.IO)
        {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.fetchData(10)


            for (items in response.body()!!.results) {
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


        }

    }

    fun loadFromRepository() {
        viewModelScope.launch(Dispatchers.IO) {

            val resultsEntity = repository.getAllResults()
            val dataManager = DataManager(resultsEntity as ArrayList<CardModelTransformation>)
            recyclerListLiveData.postValue(dataManager)
        }
    }


}