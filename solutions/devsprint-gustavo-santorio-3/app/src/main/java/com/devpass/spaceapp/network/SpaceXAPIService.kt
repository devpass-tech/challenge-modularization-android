package com.devpass.spaceapp.network

import br.com.devpass.launchlist.domain.LaunchDTO
import com.devpass.spaceapp.rocketdetails.domain.dto.RocketDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXAPIService {

    @GET("/v5/launches/{id}")
    suspend fun fetchLaunch(@Path("id") id: String) : LaunchDTO

    @GET("/v4/rockets/{id}")
    suspend fun fetchDetailRocket(@Path("id") idRocket: String) : RocketDetailDTO
}