package br.com.devpass.launchlist.domain

import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {
    suspend fun fetchLaunch(): Flow<Result<List<LaunchVO>>>
}