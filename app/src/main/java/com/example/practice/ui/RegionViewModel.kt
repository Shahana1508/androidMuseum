package com.example.practice.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.api.CityService
import com.example.practice.api.MuseumRepository
import com.example.practice.api.NetworkResponse
import com.example.practice.model.RegionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegionViewModel @Inject constructor(private val museumRepository:MuseumRepository):ViewModel() {
    val districts= MutableLiveData<List<RegionData>>()
    val error=MutableLiveData<Boolean>(false)
    fun getDistrictsforcity(city: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = museumRepository.get_regions(city = city)
//                response.body()?.data?.let {
//                    withContext(Dispatchers.Main) {
//                        districts.value =it
//                        error.value=false
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    error.value=true
//                }
//            }
//        }
        viewModelScope.launch (Dispatchers.IO){
            when(val response=museumRepository.get_regions(city=city)){
                is NetworkResponse.Success->{
                    response.data?.data?.let {
                        withContext(Dispatchers.Main){
                            districts.value=it
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