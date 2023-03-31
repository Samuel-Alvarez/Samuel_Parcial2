package com.ucne.parcial2.data.remote

import com.ucne.parcial2.data.remote.dto.TicketDto
import retrofit2.Response
import retrofit2.http.*


interface TicketsApi{

    @GET("api/Tickets")
    suspend fun gestTickes(): List<TicketDto>

    @GET("/api/tickets/{id}")
    suspend fun getTicketsbyId(@Path("id") id: Int): TicketDto

    @PUT("api/Tickets/{id}")
    suspend fun putTickets(@Path("id") id:Int, @Body ticketDto: TicketDto): Response<Unit>

    @POST("api/Tickets")
    suspend fun postTicket(@Body ticketDto: TicketDto): TicketDto
    @DELETE("api/Tickets/{id}")
    suspend fun deleteticket(@Path("id") id: Int) : Response<Unit>
}