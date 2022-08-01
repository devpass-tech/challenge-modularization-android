package com.devpass.spaceapp.launchpad.domain

import com.devpass.spaceapp.launchpad.domain.model.LaunchpadVO
import kotlinx.coroutines.flow.Flow

interface FetchLaunchesRepository {
    suspend fun fetchLaunchpadDetails(id: String): Flow<Result<LaunchpadVO>>
}