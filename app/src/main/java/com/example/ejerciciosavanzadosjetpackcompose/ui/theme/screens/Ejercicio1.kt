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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ejerciciosavanzadosjetpackcompose.R
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Ejercicio1ViewModel
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Producto


val listaProductos = listOf(
    Producto("Caja", 500.0, R.drawable.caja),
    Producto("Monitor", 200.0, R.drawable.monitor),
    Producto("Teclado", 50.0, R.drawable.teclado),
    Producto("Raton", 30.0, R.drawable.raton),
    Producto("placa", 100.0, R.drawable.placa)
)

@Composable
fun ComponenteProducto(
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
fun ListaProductos(
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
fun App(navController: NavController,viewModel: Ejercicio1ViewModel) {
    var seleccionarTodos by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    CarritoCompraIcono(navController = navController, viewModel = viewModel)
                    Row {
                        Checkbox(
                            checked = seleccionarTodos,
                            onCheckedChange = {isChecked ->
                                seleccionarTodos = isChecked
                                viewModel.seleccionarTodos(isChecked)

                            })
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Costo Total: $${viewModel.productosSeleccionados.value.sumByDouble { it.precio }}")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp) // dejar espacio para el BottomAppBar
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Yellow)) {

                Text(text = "Coste total: $${viewModel.productosSeleccionados.value.sumByDouble { it.precio }}")

                CarritoCompraIcono(navController = navController, viewModel = viewModel)
            }

            ListaProductos(
                productos = listaProductos,
                productosSeleccionados = viewModel.productosSeleccionados.value,
                onProductoSeleccionadoChange = { producto, seleccionado ->
                    viewModel.onProductoSeleccionadoChange(producto,seleccionado)
                }
            )
        }
    }
}

@Composable
fun CarritoCompraIcono(navController: NavController,viewModel: Ejercicio1ViewModel) {

    IconButton(onClick = { navController.navigate(Screens.Carrito.route) }) {
        Icon(Icons.Default.ShoppingCart,contentDescription = "Carrito de compra")
        if (viewModel.contadorProductos.value > 0) {
            Badge(valor = viewModel.contadorProductos.value)
        }
    }
}

@Composable
fun Badge(valor: Int) {
    Surface(
        modifier = Modifier.padding(start = 30.dp, top = 20.dp)
    ) {
        Text(
            text = valor.toString(),
            modifier = Modifier.padding(1.dp)
        )
    }
}