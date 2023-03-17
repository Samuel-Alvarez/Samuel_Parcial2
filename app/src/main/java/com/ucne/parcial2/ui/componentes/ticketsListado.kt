package com.ucne.parcial2.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.parcial2.view.TicketsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ticketsListado(goToRegistro :() -> Unit){
    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Listado de Tickets") },

                actions = {
                    IconButton(onClick = {
                        goToRegistro()
                    }) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                })
        },

        ){it
    }
}

