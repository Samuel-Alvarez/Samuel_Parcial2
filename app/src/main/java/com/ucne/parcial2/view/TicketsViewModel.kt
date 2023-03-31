package com.ucne.parcial2.view

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.data.repository.TicketsRepository
import com.ucne.parcial2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TicketsState(
    val isLoading: Boolean = false,
    val ticket: TicketDto ? =  null,
    val error: String = ""
)

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val ticketsRepository: TicketsRepository
):ViewModel() {

    val ticketsEstatus = listOf("Solicitado", "En espera", "Finalizado")
    var expanded by mutableStateOf(false)

    var uiState = MutableStateFlow(TicketsListState())
        private set

    var uiStateTicket = MutableStateFlow(TicketsState())
        private set

    var ticketId by mutableStateOf(0)
    var empresa by mutableStateOf("")
    var asunto by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var estatus by mutableStateOf("")

    var fecha by mutableStateOf("")
    var encargadoId by mutableStateOf("")
    var orden by mutableStateOf("")

    private var _state = mutableStateOf(TicketsListState())
    val state: State<TicketsListState> = _state

    init {
        ticketsRepository.gestTickes().onEach { result->
            when(result){
                is Resource.Loading -> {
                    uiState.update {
                        it.copy(isLoading = true)
                    }
                }

                is Resource.Success -> {
                    uiState.update {
                        it.copy(Tickets = result.data ?: emptyList())
                    }
                }

                is Resource.Error -> {
                    uiState.update {
                        it.copy(error = result.message ?: "Error desconocido")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setTicket(id:Int){
        ticketId = id
        ticketsRepository.getTicketsbyId(ticketId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateTicket.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateTicket.update {
                        it.copy(ticket = result.data )
                    }
                    empresa = uiStateTicket.value.ticket!!.empresa
                    asunto = uiStateTicket.value.ticket!!.asunto
                    estatus = uiStateTicket.value.ticket!!.estatus
                    especificaciones = uiStateTicket.value.ticket!!.especificaciones
                }
                is Resource.Error -> {
                    uiStateTicket.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun modificar(){
        viewModelScope.launch {
            ticketsRepository.putTickets(ticketId.toInt(),
                TicketDto(
                    ticketId = ticketId.toInt(),
                    uiStateTicket.value.ticket!!.fecha,
                    empresa = empresa,
                    asunto = asunto,
                    especificaciones = especificaciones,
                    uiStateTicket.value.ticket!!.encargadoId,
                    estatus = estatus,
                    uiStateTicket.value.ticket!!.orden
                )
            )
        }
    }

    fun guardar(){
        viewModelScope.launch {
            ticketsRepository.postTicket(
                TicketDto(
                    ticketId = 0,
                    fecha = "2023-03-31T01:04:28.866Z",
                    empresa = empresa,
                    asunto = asunto,
                    especificaciones = especificaciones,
                    encargadoId = encargadoId.toInt(),
                    estatus = estatus,
                    orden = orden.toInt()
                )
            )
        }
    }

    fun eliminar(id:Int){
        viewModelScope.launch {
            ticketsRepository.deleteTicket(id)

            ticketsRepository.gestTickes().onEach { result->
                when(result){
                    is Resource.Loading -> {
                        uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        uiState.update {
                            it.copy(Tickets = result.data ?: emptyList())
                        }
                    }

                    is Resource.Error -> {
                        uiState.update {
                            it.copy(error = result.message ?: "Error desconocido")
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

}