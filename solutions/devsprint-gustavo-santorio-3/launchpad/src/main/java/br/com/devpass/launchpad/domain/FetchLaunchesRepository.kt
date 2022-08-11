package br.com.devpass.launchpad.domain

import br.com.devpass.launchpad.domain.model.LaunchpadVO
import kotlinx.coroutines.flow.Flow

interface FetchLaunchesRepository {
    suspend fun fetchLaunchpadDetails(id: String): Flow<Result<LaunchpadVO>>
}