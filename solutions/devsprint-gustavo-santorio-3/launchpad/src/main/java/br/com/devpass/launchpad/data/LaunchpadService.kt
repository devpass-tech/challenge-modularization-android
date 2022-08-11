package br.com.devpass.launchpad.data

import br.com.devpass.launchpad.domain.dto.LaunchpadDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface LaunchpadService {

    @GET("/v4/launchpads/{id}")
    suspend fun fetchLaunchpadDetails(@Path("id") id: String): LaunchpadDTO

}