package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppAgenda(){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agenda de contactos") })
        },
        bottomBar = {
            BottomAppBar {}
        }
    ) {

    }
}