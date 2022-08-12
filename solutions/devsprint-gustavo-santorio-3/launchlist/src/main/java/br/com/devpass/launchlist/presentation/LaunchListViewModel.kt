package br.com.devpass.launchlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.devpass.launchlist.data.LaunchesRepositoryImpl
import br.com.devpass.launchlist.domain.LaunchVO
import br.com.devpass.launchlist.domain.LaunchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class State {
    data class Success(
        val list: List<LaunchVO>
    ) : State()

    object Loading : State()
    data class Failure(val error: String) : State()
}

class LaunchListViewModel: ViewModel() {

    private val repository: LaunchesRepository = LaunchesRepositoryImpl()
    val state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    init {
        getLaunch()
    }

    private fun getLaunch() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchLaunch().collect{
                it.onSuccess {
                    state.value = State.Success(it)
                }.onFailure {
                    state.value = State.Failure(it.message.toString())
                }
            }
        }
    }
}