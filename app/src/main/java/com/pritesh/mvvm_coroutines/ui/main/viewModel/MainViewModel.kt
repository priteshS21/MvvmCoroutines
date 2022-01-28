package com.pritesh.mvvm_coroutines.ui.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pritesh.mvvm_coroutines.repo.RestClient
import com.pritesh.mvvm_coroutines.ui.main.models.AleList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val responseObserver: MutableLiveData<List<AleList>> =
        MutableLiveData<List<AleList>>()

    val statusObserver: MutableLiveData<String> =
        MutableLiveData<String>()

    init {
        getAle()
    }

    private fun getAle() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, t ->
            t.printStackTrace()
        }) {
            val response = RestClient.getInstance().getAleList()

            if (response!!.isSuccessful) {
                responseObserver.postValue(response.body())
            } else {
                statusObserver.postValue(response.message())
            }
        }
    }
}