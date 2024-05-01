package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens

sealed class Screens(val route: String){
    object Menu: Screens("initial_screen")
    object Ejercicio1 : Screens ("ejExamen")
    object Ejercicio2 : Screens ("ejExamen2")
    object Login: Screens("login")
    object Carrito: Screens("carrito")
}