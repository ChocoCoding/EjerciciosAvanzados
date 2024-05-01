package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Ejercicio1ViewModel
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities.Producto

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun AppCarrito(navController: NavController,
               viewModel: Ejercicio1ViewModel){
    var context = LocalContext.current
    val productosSeleccionados = viewModel.productosSeleccionados.value

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

                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text("Costo Total: $${viewModel.productosSeleccionados.value.sumByDouble { it.precio }}")
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Text("Productos Seleccionados: ${viewModel.contadorProductos.value}")
            Spacer(modifier = Modifier.height(8.dp))
            ListaProductosSeleccionados(
                productosSeleccionados = viewModel.productosSeleccionados.value,
                viewModel = viewModel
            )
        }

    }

}


@Composable
fun ComponenteProducto2(
    producto: Producto,
    isSelected: Boolean,
    viewModel: Ejercicio1ViewModel
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

        Row {
            Text(text = producto.nombre)
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {viewModel.eliminarProducto(producto = producto)}) {
                Icon(Icons.Default.Delete,contentDescription = "Eliminar")
            }
        }
    }
}



@Composable
fun ListaProductosSeleccionados(
    productosSeleccionados: List<Producto>,
    viewModel: Ejercicio1ViewModel
) {
    LazyColumn {
        items(productosSeleccionados) { producto ->
            val isSelected = productosSeleccionados.contains(producto)
            ComponenteProducto2(
                producto = producto,
                isSelected = isSelected,
                viewModel = viewModel
            )
        }
    }
}
