package com.ucne.parcial2.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ticketsRegistro(backToListado:() -> Unit){
    Scaffold(
        topBar ={
            TopAppBar(title = { Text(text = "Registro de Tickets") })
        },

        ){it

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(8.dp)

        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Descripcion")},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null)}
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(15.dp),
                onClick = { backToListado() }
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