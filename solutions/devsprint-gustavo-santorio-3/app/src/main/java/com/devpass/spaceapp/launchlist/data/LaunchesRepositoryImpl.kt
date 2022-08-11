package com.devpass.spaceapp.launchlist.data

import br.com.devpass.network.NetworkModule
import com.devpass.spaceapp.network.SpaceXAPIService
import com.devpass.spaceapp.launchlist.domain.LaunchVO
import com.devpass.spaceapp.launchlist.domain.LaunchesRepository
import com.devpass.spaceapp.utils.getStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchesRepositoryImpl(
    private val service: SpaceXAPIService = NetworkModule.retrofitInstance
        .create(SpaceXAPIService::class.java)
) : LaunchesRepository {

    override suspend fun fetchLaunch(): Flow<Result<List<LaunchVO>>> =
        flow {
            runCatching {
                val dto = service.fetchLaunches()
                dto.docs.map {
                    LaunchVO(
                        it.name,
                        it.flight_number.toString(),
                        it.date_utc,
                        it.success.getStatus(),
                        it.links.patch.small
                    )
                }
            }.also {
                emit(it)
            }
        }


}