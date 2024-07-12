package com.example.practice.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.api.CityService
import com.example.practice.model.Data
import com.example.practice.model.MuseumData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(val api:CityService):ViewModel(){
    val museums=MutableLiveData<List<MuseumData>>()
    fun getMuseums(city:String,district:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val response=api.getMuseums(city=city, district = district)
                if(response.isSuccessful){
                    response.body()?.data?.let{
                        withContext(Dispatchers.Main){
                            museums.value=it
                        }
                    }
                }
            }catch (e:Exception){

            }
        }
    }
}