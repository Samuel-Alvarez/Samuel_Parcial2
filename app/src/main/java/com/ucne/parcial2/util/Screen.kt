package com.ucne.parcial2.util

sealed class Screen(val route: String) {
    object ticketsListado: Screen("ticketsListado")
    object ticketsRegistro: Screen("ticketsRegistro")
}