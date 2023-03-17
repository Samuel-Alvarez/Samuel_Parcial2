package com.ucne.parcial2.data.remote

import com.ucne.parcial2.data.remote.dto.TicketDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface TicketsApi{

    @GET("api/Tickets")
    suspend fun gestTickes(): List<TicketDto>

    @PUT("api/Tickets/{id}")
    suspend fun putTickets(@Path("id") id:Int, @Body ticketDto: TicketDto): Response<Unit>
}