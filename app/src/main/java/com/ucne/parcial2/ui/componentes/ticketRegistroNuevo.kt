package com.ucne.parcial2.ui.componentes

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ucne.parcial2.util.Screen
import com.ucne.parcial2.view.TicketsViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ticketRegistroNuevo(navHostController: NavHostController, viewModel: TicketsViewModel = hiltViewModel()){

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val contexto = LocalContext.current


    val date = DatePickerDialog(
        contexto, {d, year, month, day->
            val month = month + 1
            viewModel.fecha = "$year-$month-$day"
        },year, month, day
    )

    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Nuevo Ticket") },
            )
        },

        ){it

        Column(modifier = Modifier.fillMaxWidth().padding(8.dp, vertical = 60.dp)) {

            OutlinedTextField(
                value = viewModel.fecha,
                onValueChange = {viewModel.fecha = it},
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Fecha") },
                readOnly = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "",
                    )
                },

                trailingIcon = {
                    IconButton(
                        onClick = { date.show() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "",
                        )
                    }
                }

            )

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

            OutlinedTextField(
                value = viewModel.encargadoId,
                onValueChange = { viewModel.encargadoId = it },
                label = { Text(text = "Encargado") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Engineering,
                        contentDescription = null
                    )
                }
            )

            OutlinedTextField(
                value = viewModel.orden,
                onValueChange = { viewModel.orden = it },
                label = { Text(text = "Orden") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = null
                    )
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                onClick = {
                    viewModel.guardar()
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
