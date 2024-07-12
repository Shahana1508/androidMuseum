package com.example.practice.ui

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.api.CityService
import com.example.practice.api.MuseumRepository
import com.example.practice.api.NetworkResponse
import com.example.practice.model.Data
import com.example.practice.model.MuseumData
import com.example.practice.model.MuseumResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MuseumViewModel @Inject constructor(val museumRepository: MuseumRepository):ViewModel(){
    val museums=MutableLiveData<List<MuseumData>>()
    val error=MutableLiveData<Boolean>(false)
    fun getMuseums(city:String,district:String){

        viewModelScope.launch (Dispatchers.IO){
            when(val response=museumRepository.get_museums(city=city,district=district)){
                is NetworkResponse.Success->{
                    withContext(Dispatchers.Main){
                        response.data?.data?.let {
                            museums.value =it
                        }
                    }
                }
                is NetworkResponse.Error->{
                    withContext(Dispatchers.Main){
                        error.value=true
                    }
                }
            }

        }
    }
}