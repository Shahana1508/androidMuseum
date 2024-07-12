package com.example.practice.ui

import android.provider.ContactsContract.Data
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.api.CityService
import com.example.practice.api.MuseumRepository
import com.example.practice.api.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val museumRepository: MuseumRepository):ViewModel() {
    val cities=MutableLiveData<List<com.example.practice.model.Data>>()
    val error=MutableLiveData<Boolean>(false)
    val loading=MutableLiveData<Boolean>(false)
    init {
        getcities()
    }
    fun getcities(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val response=museumRepository.get_cities()){
                is NetworkResponse.Success->{
                    response.data?.data?.let {
                        withContext(Dispatchers.Main){
                            cities.value=it
                        }
                    }
                }
                is NetworkResponse.Error->{
                    error.value=true
                }
            }
        }
    }
}