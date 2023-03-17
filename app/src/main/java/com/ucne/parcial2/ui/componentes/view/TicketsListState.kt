package com.ucne.parcial2.ui.componentes.view

import com.ucne.parcial2.data.remote.dto.TicketDto

data class TicketsListState(
    val isLoading: Boolean = false,
    val Coins: List<TicketDto> = emptyList(),
    val error: String = ""
)