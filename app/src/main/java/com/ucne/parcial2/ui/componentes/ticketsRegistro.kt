package com.ucne.parcial2.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ucne.parcial2.util.Screen
import com.ucne.parcial2.view.TicketsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ticketsRegistro(navHostController: NavHostController, Id:Int, viewModel: TicketsViewModel = hiltViewModel()) {

    remember {
        viewModel.setTicket(Id)
        0
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Registro de Tickets") })
        },

        ) {
        it

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, vertical = 50.dp),
            ) {
                OutlinedTextField(
                    value = viewModel.empresa,
                    onValueChange = { viewModel.empresa = it },
                    label = { Text(text = "Empresa") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null
                        )
                    }
                )

                OutlinedTextField(
                    value = viewModel.asunto,
                    onValueChange = { viewModel.asunto = it },
                    label = { Text(text = "Asunto") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Subject,
                            contentDescription = null
                        )
                    }
                )

                OutlinedTextField(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.expanded = true },
                    value = viewModel.estatus,
                    enabled = false, readOnly = true,
                    label = { Text(text = "Estatus") },
                    onValueChange = { viewModel.estatus = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.QueryStats,
                            contentDescription = null
                        )
                    }
                )

                DropdownMenu(
                    expanded = viewModel.expanded,
                    onDismissRequest = { viewModel.expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)

                ) {
                    viewModel.ticketsEstatus.forEach { opcion ->
                        DropdownMenuItem(
                            text = {
                                Text(text = opcion, textAlign = TextAlign.Center)
                            },
                            onClick = {
                                viewModel.expanded = false
                                viewModel.estatus = opcion
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }

                OutlinedTextField(
                    value = viewModel.especificaciones,
                    onValueChange = { viewModel.especificaciones = it },
                    label = { Text(text = "Especificaciones") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.FolderSpecial,
                            contentDescription = null
                        )
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    onClick = {
                        viewModel.modificar()
                        navHostController.navigate(Screen.ticketsListado.route)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Save, contentDescription = "Save")
                    Text(
                        text = "Guardar",
                        fontWeight = FontWeight.Black,

                        )
                }
            }
        }
    }
}