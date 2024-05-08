package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejerciciosavanzadosjetpackcompose.R
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Producto


val listaProductosS = listOf(
    Producto("Caja", 500.0, R.drawable.caja,"Cajas"),
    Producto("Monitor", 200.0, R.drawable.monitor,"Electronica"),
    Producto("Teclado", 50.0, R.drawable.teclado,"Perifericos"),
    Producto("Raton", 30.0, R.drawable.raton,"Perifericos"),
    Producto("Placa", 100.0, R.drawable.placa,"Electronica"),
    Producto("Grafica", 30.0, R.drawable.grafica,"Electronica"),
    Producto("Microfono", 30.0, R.drawable.microfono,"Perifericos"),
    Producto("Ram", 30.0, R.drawable.ram,"Electronica"),
    Producto("SSD", 30.0, R.drawable.ssd,"Discos"),
    Producto("M2", 30.0, R.drawable.m2,"Discos")


    )

@Composable
fun ComponenteProductoS(
    producto: Producto,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Image(painter = painterResource(id = producto.imagenResId),
            contentDescription = "Imagen de ${producto.nombre}",
            modifier = Modifier
                .size(128.dp)
                .padding(start = 16.dp, end = 16.dp))

        Checkbox(
            checked = isSelected,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(text = producto.nombre, modifier = Modifier.padding(start = 16.dp))
    }
}


@Composable
fun ListaProductosS(
    productos: List<Producto>,
    productosSeleccionados: List<Producto>,
    onProductoSeleccionadoChange: (Producto, Boolean) -> Unit
) {
    LazyColumn {
        items(productos) { producto ->
            val isSelected = productosSeleccionados.contains(producto)
            ComponenteProducto(
                producto = producto,
                isSelected = isSelected
            ) { seleccionado ->
                onProductoSeleccionadoChange(producto, seleccionado)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppS(navController: NavController) {
    var productosSeleccionados by remember { mutableStateOf(listOf<Producto>()) }
    var contadorProductos by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                actions = {
                    CarritoCompraIconoS(contadorProductos,navController)
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Costo Total: $${productosSeleccionados.sumByDouble { it.precio }}")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp) // dejar espacio para el BottomAppBar
        ) {


            
            ListaProductos(
                productos = listaProductos,
                productosSeleccionados = productosSeleccionados,
                onProductoSeleccionadoChange = { producto, seleccionado ->
                    if (seleccionado) {
                        productosSeleccionados = productosSeleccionados + producto
                        contadorProductos++
                    } else {
                        productosSeleccionados = productosSeleccionados - producto
                        contadorProductos--
                    }
                }
            )
        }
    }
}

@Composable
fun CarritoCompraIconoS(contadorProductos: Int, navController: NavController) {

    IconButton(onClick = { navController.navigate(Screens.Carrito.route) }) {
        Icon(Icons.Default.ShoppingCart,contentDescription = "Carrito de compra")
        if (contadorProductos > 0) {
            BadgeS(valor = contadorProductos)
        }
    }
}

@Composable
fun BadgeS(valor: Int) {
    Surface(
        modifier = Modifier.padding(start = 30.dp, top = 20.dp)
    ) {
        Text(
            text = valor.toString(),
            modifier = Modifier.padding(1.dp)
        )
    }
}