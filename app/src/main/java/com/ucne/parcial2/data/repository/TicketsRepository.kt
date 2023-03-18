package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketsRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun gestTickes(): Flow<Resource<List<TicketDto>>> = flow {
        try {
            emit(Resource.Loading())
            val tickes = api.gestTickes()
            emit(Resource.Success(tickes))
        }catch (e: HttpException){
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        }catch (e: IOException){
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun putTickets(id:Int, ticketDto: TicketDto){
        api.putTickets(id, ticketDto)
    }

    fun getTicketsbyId(id: Int): Flow<Resource<TicketDto>> = flow {
        try {
            emit(Resource.Loading())
            val tickets = api.getTicketsbyId(id)
            emit(Resource.Success(tickets))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
}