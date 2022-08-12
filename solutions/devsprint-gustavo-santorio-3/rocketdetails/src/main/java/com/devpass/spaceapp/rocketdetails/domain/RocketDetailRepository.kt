package com.devpass.spaceapp.rocketdetails.domain

import com.devpass.spaceapp.rocketdetails.domain.dto.RocketDetailVO
import kotlinx.coroutines.flow.Flow

interface RocketDetailRepository {
    suspend fun getDetailRocket(id: String) : Flow<Result<RocketDetailVO>>
}