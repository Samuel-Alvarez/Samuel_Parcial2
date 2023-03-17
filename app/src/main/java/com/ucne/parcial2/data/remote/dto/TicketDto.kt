package com.ucne.parcial2.data.remote.dto

data class TicketDto(
    val ticketId: Int,
    val fecha: String,
    val empresa: String,
    val asunto: String,
    val especificaciones: String,
    val encargadoId: Int,
    val estatus: String,
    val orden: Int,
)