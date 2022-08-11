package com.devpass.spaceapp.network
import com.devpass.spaceapp.launchlist.domain.LaunchDTO
import com.devpass.spaceapp.launchlist.domain.LaunchesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import com.devpass.spaceapp.launchpad.domain.dto.LaunchpadDTO
import retrofit2.http.POST

interface SpaceXAPIService {

    @GET("/v5/launches/{id}")
    suspend fun fetchLaunch(@Path("id") id: String) : LaunchDTO

    @GET("/v4/launchpads/{id}")
    suspend fun fetchLaunchpadDetails(@Path("id") id: String): LaunchpadDTO

    @POST("/v5/launches/query")
    suspend fun fetchLaunches() : LaunchesDTO
}