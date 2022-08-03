package com.devpass.spaceapp.launch.domain

import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    suspend fun fetchLaunch(id : String) : Flow<Result<LaunchVO>>
}