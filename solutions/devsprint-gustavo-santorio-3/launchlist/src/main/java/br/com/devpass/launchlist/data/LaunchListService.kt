package br.com.devpass.launchlist.data

import br.com.devpass.launchlist.domain.LaunchesDTO
import retrofit2.http.POST

interface LaunchListService {

    @POST("/v5/launches/query")
    suspend fun fetchLaunches() : LaunchesDTO
}