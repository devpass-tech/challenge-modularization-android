package br.com.devpass.launchpad.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devpass.launchpad.data.FetchLaunchesRepositoryImpl
import br.com.devpass.launchpad.domain.FetchLaunchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class State {
    data class Success(
        val name: String,
        val fullName: String,
        val locale: String,
        val attempts: String,
        val successes: String
    ) : State()

    object Loading : State()
    data class Failure(val error: String) : State()
}

class LaunchDetailsViewModel() : ViewModel() {

    companion object {
        const val ID = "5e9e4502f509092b78566f87"
    }

    private val repository: FetchLaunchesRepository = FetchLaunchesRepositoryImpl()
    val state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    init {
        fetchLaunchpadData()
    }

    private fun fetchLaunchpadData(id: String = ID) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchLaunchpadDetails(id = id).collect { result ->
                result.onSuccess {
                    state.value = State.Success(
                        name = it.name,
                        fullName = it.fullName,
                        locale = it.region,
                        attempts = it.launchAttempts,
                        successes = it.launchSuccesses
                    )
                }.onFailure {
                    state.value = State.Failure(
                        error = it.message.toString()
                    )
                }
            }
        }
    }
}