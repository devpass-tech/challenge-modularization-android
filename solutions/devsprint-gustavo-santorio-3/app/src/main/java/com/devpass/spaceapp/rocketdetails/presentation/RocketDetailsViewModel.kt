package com.devpass.spaceapp.rocketdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devpass.spaceapp.rocketdetails.data.RocketDetailRepositoryImpl
import com.devpass.spaceapp.rocketdetails.domain.RocketDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class State {
    data class Success(
        val name: String,
        val details: String,
        val image: String
    ) : State()

    object Loading : State()
    data class Failure(val error: String) : State()
}

class RocketDetailsViewModel(private val repository: RocketDetailRepository = RocketDetailRepositoryImpl()) : ViewModel(){

    val state: MutableStateFlow<State> = MutableStateFlow(State.Loading)

    init {
        fetchDetails()
    }

    private fun fetchDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDetailRocket(ID).collect{ result->
                result
                    .onSuccess {success ->
                        state.emit(State.Success(success.rocketName, success.description, success.image))
                    }
                    .onFailure { failure ->
                        state.emit(State.Failure(ERROR))
                    }

            }
        }
    }

    companion object {
        const val ID = "5e9e4502f509092b78566f87"
        const val ERROR = "Tivemos um problema"
    }

}