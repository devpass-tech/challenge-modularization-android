package com.devpass.spaceapp.rocketdetails.data

import br.com.devpass.network.NetworkModule
import com.devpass.spaceapp.rocketdetails.data.service.RocketDetailService
import com.devpass.spaceapp.rocketdetails.domain.dto.RocketDetailVO
import com.devpass.spaceapp.rocketdetails.domain.RocketDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RocketDetailRepositoryImpl(
    private val rocketService: RocketDetailService =
        NetworkModule.retrofitInstance.create(RocketDetailService::class.java)
) : RocketDetailRepository {

    override suspend fun getDetailRocket(id: String): Flow<Result<RocketDetailVO>> =
        flow {
            runCatching {
                val dataResult = rocketService.fetchDetailRocket(id)
                emit(
                    Result.success(
                        RocketDetailVO(
                            dataResult.name,
                            dataResult.flickr_images.first(),
                            dataResult.description
                        )
                    )
                )
            }
        }
}