package com.devpass.spaceapp.launchlist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.launchlist.domain.LaunchVO
import com.devpass.spaceapp.launchlist.domain.LaunchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

sealed class State {
    data class Success(
        val list: List<LaunchVO>
    ) : State()

    object Loading : State()
    data class Failure(val error: String) : State()
}

class LaunchViewModel(private val repository: LaunchesRepository) : ViewModel() {

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