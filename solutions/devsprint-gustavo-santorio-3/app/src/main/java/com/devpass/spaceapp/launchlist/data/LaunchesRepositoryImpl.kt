package com.devpass.spaceapp.launchlist.data

import com.devpass.spaceapp.data.api.NetworkModule
import com.devpass.spaceapp.network.SpaceXAPIService
import com.devpass.spaceapp.launchlist.domain.LaunchVO
import com.devpass.spaceapp.launchlist.domain.LaunchesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchesRepositoryImpl(
    private val service: SpaceXAPIService =
        NetworkModule.retrofitInstance
            .create(SpaceXAPIService::class.java))
    : LaunchesRepository {

    override suspend fun fetchLaunch(): Flow<Result<List<LaunchVO>>> =
        flow {
            runCatching {
                val dto = service.fetchLaunches()
                dto.docs.map {
                    LaunchVO(it.name, it.flight_number.toString(), it.date_utc, getFlightStatus(it.success), it.links.patch.small)
                }
            }.also {
                emit(it)
            }
        }

    private fun getFlightStatus(success : Boolean) =
        if(success)
            "sucesso"
        else
            "falha"
}