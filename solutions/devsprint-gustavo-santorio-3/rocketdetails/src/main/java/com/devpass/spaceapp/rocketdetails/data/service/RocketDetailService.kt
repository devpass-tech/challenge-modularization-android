package com.devpass.spaceapp.rocketdetails.data.service

import com.devpass.spaceapp.rocketdetails.domain.dto.RocketDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketDetailService {

    @GET("/v4/rockets/{id}")
    suspend fun fetchDetailRocket(@Path("id") idRocket: String) : RocketDetailDTO
}