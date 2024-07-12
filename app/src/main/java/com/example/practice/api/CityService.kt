package com.example.practice.api

import com.example.practice.api.Constants.api_Key
import com.example.practice.model.CityResponse
import com.example.practice.model.MuseumResponse
import com.example.practice.model.RegionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityService {
    @GET("museum/cities")
    suspend fun getCities(@Query("apiKey") apiKey: String = api_Key): Response<CityResponse>

    @GET("museum/cities")
    suspend fun getDistricts(
        @Query("apiKey") apiKey: String = api_Key,
        @Query("city") city: String,
    ):Response<RegionResponse>

    @GET("museum")
    suspend fun getMuseums(@Query("apiKey") apiKey: String = api_Key,
                           @Query("city") city: String,
                           @Query("district") district: String,
                           ):Response<MuseumResponse>
}