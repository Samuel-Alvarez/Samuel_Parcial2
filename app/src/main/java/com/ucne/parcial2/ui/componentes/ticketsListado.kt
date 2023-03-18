package com.ucne.parcial2.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Screen
import com.ucne.parcial2.view.TicketsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ticketsListado(navHostController: NavHostController, viewModel: TicketsViewModel = hiltViewModel()){

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Tickets") },

                actions = {
                    IconButton(onClick = {

                    }) {
                        //Icon(Icons.Filled.Add, "Add")
                    }
                })
        },

        ){it
        val uiState by viewModel.uiState.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            TicketListBody(navHostController = navHostController, uiState.Tickets, Onclick = {})
        }

    }
}

@Composable
fun TicketListBody( navHostController: NavHostController, ticketList: List<TicketDto>, Onclick : (TicketDto) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) { tickets ->
                TicketRow(navHostController = navHostController, tickets)
            }
        }
    }
}

@Composable
fun TicketRow( navHostController: NavHostController,ticket: TicketDto) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navHostController.navigate(Screen.ticketsRegistro.route + "/${ticket.ticketId}") }
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()

        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = ticket.empresa,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = ticket.fecha.substring(0,10),
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(2f)
                )

            }

            Row(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = ticket.asunto,
                )

                Text(
                    text = "",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(2f)
                )

                Icon(
                    imageVector = when (ticket.estatus) {
                        "Solicitado" -> {
                            Icons.Default.Star
                        }
                        "En espera" -> {
                            Icons.Default.Update
                        }
                        else -> {
                            Icons.Default.TaskAlt

                        }
                    }, contentDescription = ticket.estatus,
                )
            }

            Divider(Modifier.fillMaxWidth())

        }
    }
}
