package com.ucne.parcial2.ui.componentes.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.repository.TicketsRepository
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsViewModel @Inject constructor(
    private val ticketsRepository: TicketsRepository
):ViewModel() {

    var id by mutableStateOf("")
    var fecha by mutableStateOf("")
    var empresa by mutableStateOf("")
    var asunto by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var encargadoId by mutableStateOf("")
    var estatus by mutableStateOf("")
    var orden by mutableStateOf("")

    private var _state = mutableStateOf(TicketsListState())
    val state: State<TicketsListState> = _state

    init {
        ticketsRepository.gestTickes().onEach { result->
            when(result){
                is Resource.Loading -> {
                    _state.value = TicketsListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = TicketsListState(Coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = TicketsListState(error = result.message?: "Error desconocido")
                }
            }
        }
    }

    fun modificar(){
        viewModelScope.launch {
            ticketsRepository.putTickets(id.toInt(),
                TicketDto(
                    ticketId = id.toInt(),
                    fecha = fecha,
                    empresa = empresa,
                    asunto = asunto,
                    especificaciones = especificaciones,
                    encargadoId =encargadoId.toInt(),
                    estatus = estatus,
                    orden = orden.toInt()
                )
            )
        }
    }

}