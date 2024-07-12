package com.example.practice.api

import retrofit2.Response
import javax.inject.Inject

class MuseumRepository @Inject constructor(private val CityService:CityService){
    suspend fun get_cities ()=safeApiRequest {
        CityService.getCities()
    }
    private suspend fun <T> safeApiRequest(apiCall:suspend() -> Response<T>):NetworkResponse<T>{
        try {
            val response=apiCall.invoke()
            if(response.isSuccessful){
                response.body()?.let {
                    return NetworkResponse.Success(it)
                }?: return NetworkResponse.Error("empty response")
            }else{
                return NetworkResponse.Error(response.errorBody().toString())
            }
        }catch (e:Exception){
            return NetworkResponse.Error(e.localizedMessage.toString())
        }

    }
}