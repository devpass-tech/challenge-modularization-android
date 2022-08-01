package com.devpass.spaceapp.rocketdetails.data

import com.devpass.spaceapp.data.api.NetworkModule
import com.devpass.spaceapp.network.SpaceXAPIService
import com.devpass.spaceapp.rocketdetails.domain.dto.RocketDetailVO
import com.devpass.spaceapp.rocketdetails.domain.RocketDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketDetailRepositoryImpl(private val rocketService: SpaceXAPIService =
    NetworkModule.retrofitInstance.create(SpaceXAPIService::class.java)) : RocketDetailRepository {

    override suspend fun getDetailRocket(id: String): Flow<Result<RocketDetailVO>> =
        flow {
            runCatching {
                val dataResult = rocketService.getDetailRocket(id)
                emit(Result.success(RocketDetailVO(dataResult.name, dataResult.flickr_images.first(), dataResult.description)))
            }
        }
}