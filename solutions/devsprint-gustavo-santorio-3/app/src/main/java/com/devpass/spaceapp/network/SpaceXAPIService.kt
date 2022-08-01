package com.devpass.spaceapp.network
import com.devpass.spaceapp.launchlist.domain.LaunchesDTO
import com.devpass.spaceapp.rocketdetails.domain.dto.RocketDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path
import com.devpass.spaceapp.launchpad.domain.dto.LaunchpadDTO
import retrofit2.http.POST
import retrofit2.http.Query
interface SpaceXAPIService {

    @GET("/v4/rockets/{id}")
    suspend fun getDetailRocket(@Path("id") idRocket: String) : RocketDetailDTO

    @GET("/v4/launchpads/{id}")
    suspend fun fetchLaunchpadDetails(@Query("id") id: String): LaunchpadDTO

    @POST("/v5/launches/query")
    suspend fun fetchLaunches() : LaunchesDTO
}