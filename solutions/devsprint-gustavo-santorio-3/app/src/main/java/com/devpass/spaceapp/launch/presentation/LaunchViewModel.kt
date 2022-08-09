package com.devpass.spaceapp.launch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.launch.data.LaunchRepositoryImpl
import com.devpass.spaceapp.launch.domain.LaunchRepository
import com.devpass.spaceapp.launch.domain.LaunchVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class State {
    data class Success(
        val vo: LaunchVO
    ) : State()

    object Loading : State()
    data class Failure(val error: String) : State()
}

class LaunchViewModel : ViewModel(){

    private val repository: LaunchRepository = LaunchRepositoryImpl()

    val state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    init {
        fetchLaunch()
    }

    private fun fetchLaunch(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchLaunch("").collect{
                it.onSuccess {
                    state.value = State.Success(it)
                }.onFailure {
                    state.value = State.Failure(it.message.toString())
                }
            }
        }
    }
}