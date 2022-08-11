package br.com.devpass.launchpad.data

import br.com.devpass.launchpad.domain.FetchLaunchesRepository
import br.com.devpass.launchpad.domain.model.LaunchpadVO
import br.com.devpass.network.NetworkModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchLaunchesRepositoryImpl(
    private val service: LaunchpadService = NetworkModule.retrofitInstance.create(LaunchpadService::class.java)
) : FetchLaunchesRepository {

    override suspend fun fetchLaunchpadDetails(id: String): Flow<Result<LaunchpadVO>> {
        return flow {
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
