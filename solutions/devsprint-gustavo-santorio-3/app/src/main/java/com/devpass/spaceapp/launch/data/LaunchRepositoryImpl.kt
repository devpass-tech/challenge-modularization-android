package com.devpass.spaceapp.launch.data

import com.devpass.spaceapp.data.api.NetworkModule
import com.devpass.spaceapp.launch.domain.LaunchRepository
import com.devpass.spaceapp.launch.domain.LaunchVO
import com.devpass.spaceapp.network.SpaceXAPIService
import com.devpass.spaceapp.utils.getStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchRepositoryImpl(
    private val service : SpaceXAPIService =
        NetworkModule.retrofitInstance
        .create(SpaceXAPIService::class.java)
) : LaunchRepository {

    override suspend fun fetchLaunch(id: String): Flow<Result<LaunchVO>> =
        flow {
            runCatching {
                with(service.fetchLaunch(id)){
                    LaunchVO(
                        image = links.patch.small,
                        title = name,
                        date = date_utc,
                        status = success.getStatus(),
                        rocket = rocket,
                        launchpad = launchpad,
                        details = details
                    )
                }

            }.also {
                emit(it)
            }
        }

}