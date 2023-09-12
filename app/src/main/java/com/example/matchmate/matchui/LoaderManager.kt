package com.example.matchmate.matchui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoaderManager {
    private val isLoadingLiveData:MutableLiveData<Boolean> = MutableLiveData(false)
    fun isLoading():LiveData<Boolean>{
        return isLoadingLiveData
    }
    fun showLoader(){
        isLoadingLiveData.postValue(true)
    }
    fun hideLoader(){
        isLoadingLiveData.postValue(false)
    }
}