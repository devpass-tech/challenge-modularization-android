package br.com.devpass.launchlist.data

import br.com.devpass.launchlist.domain.LaunchVO
import br.com.devpass.launchlist.domain.LaunchesRepository
import br.com.devpass.network.NetworkModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LaunchesRepositoryImpl(
    private val service: LaunchListService = NetworkModule.retrofitInstance
        .create(LaunchListService::class.java)
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
                        if (it.success) "sucesso" else "falha",
                        it.links.patch.small
                    )
                }
            }.also {
                emit(it)
            }
        }


}