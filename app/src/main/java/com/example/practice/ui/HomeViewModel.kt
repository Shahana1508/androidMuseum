package com.example.practice.ui

import android.provider.ContactsContract.Data
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.api.CityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api:CityService):ViewModel() {
    val cities=MutableLiveData<List<com.example.practice.model.Data>>()
    init {
        getcities()
    }
    fun getcities(){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response=api.getCities()
                if(response.isSuccessful) {
                    response.body()?.data?.let{
                        withContext(Dispatchers.Main) {
                            cities.value=it
                        }
                    }
                }
            }catch(e:Exception){

            }
        }
    }
}