package com.ucne.parcial2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.parcial2.ui.componentes.ticketsListado
import com.ucne.parcial2.ui.componentes.ticketsRegistro
import com.ucne.parcial2.ui.theme.Parcial2Theme
import com.ucne.parcial2.util.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myApp()
                }
            }
        }
    }
}
@Composable
fun myApp(){
    val navHostController = rememberNavController()
    NavHost(navController = navHostController,
        startDestination = Screen.ticketsListado.route){

        composable(route  = Screen.ticketsListado.route){
            ticketsListado(goToRegistro = {navHostController.navigate(Screen.ticketsRegistro.route)})
        }

        composable(route = Screen.ticketsRegistro.route){
            ticketsRegistro(backToListado = {navHostController.navigate(Screen.ticketsListado.route)})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Parcial2Theme {
        myApp()
    }
}