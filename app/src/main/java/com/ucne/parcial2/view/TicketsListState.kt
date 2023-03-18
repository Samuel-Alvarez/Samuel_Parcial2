package com.ucne.parcial2.view

import com.ucne.parcial2.data.remote.dto.TicketDto

data class TicketsListState(
    val isLoading: Boolean = false,
    val Tickets: List<TicketDto> = emptyList(),
    val error: String = ""
)