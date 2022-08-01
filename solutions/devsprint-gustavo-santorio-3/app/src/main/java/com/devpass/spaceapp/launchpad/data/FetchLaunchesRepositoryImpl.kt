package com.devpass.spaceapp.launchpad.data

import com.devpass.spaceapp.data.api.NetworkModule
import com.devpass.spaceapp.network.SpaceXAPIService
import com.devpass.spaceapp.launchpad.domain.FetchLaunchesRepository
import com.devpass.spaceapp.launchpad.domain.model.LaunchpadVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchLaunchesRepositoryImpl(
    private val service: SpaceXAPIService = NetworkModule.retrofitInstance.create(SpaceXAPIService::class.java)
) : FetchLaunchesRepository {

    override suspend fun fetchLaunchpadDetails(id: String): Flow<Result<LaunchpadVO>> {
           return flow{
               runCatching {
                   val response = service.fetchLaunchpadDetails(id = id)
                   with(response) {
                       LaunchpadVO(
                           name = name,
                           fullName = full_name,
                           region = region,
                           launchAttempts = launch_attempts.toString(),
                           launchSuccesses = launch_successes.toString()
                       )
                   }
               }.also {
                   emit(it)
               }
           }
     }
}
