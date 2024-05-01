package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AppLogin(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var intentos by remember { mutableStateOf(3) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = {username = it},
            label = {Text("Username")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("password")},
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation())

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Verificar credenciales
                val isValidCredentials = users.any {it.username == username && it.password == password }
                if (isValidCredentials) {
                    navController.navigate(Screens.Ejercicio1.route)
                } else {
                    intentos--
                    if (intentos == 0) {
                        
                    }else{
                        Toast.makeText(context, "Intentos restantes: $intentos", Toast.LENGTH_SHORT).show()
                        username = ""
                        password = ""
                    }
                }
            }
        ) {
            Text("Log in")
        }
    }
}


data class User(val username: String, val password: String)

val users: MutableList<User> = mutableListOf(
    User("u1", "pass1"),
    User("u2", "pass2"),
    User("u3", "pass3")
)