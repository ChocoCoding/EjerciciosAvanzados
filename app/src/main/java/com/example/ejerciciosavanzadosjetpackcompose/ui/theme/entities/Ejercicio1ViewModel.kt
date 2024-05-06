package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities


import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.listaProductos
import java.text.DecimalFormat

class Ejercicio1ViewModel:ViewModel() {
    var productosSeleccionados = mutableStateOf(listOf<Producto>());
    var contadorProductos = mutableIntStateOf(0);
    var seleccionarTodos = mutableStateOf(false)
    var showDialog =  mutableStateOf(false)

    val formato = DecimalFormat("#.##")
    fun confirmarCompra() {
        showDialog.value = true
    }

    fun onProductoSeleccionadoChange(producto: Producto,seleccionado: Boolean){
      val productoSeleccionadoActual = productosSeleccionados.value.toMutableList();
      if (seleccionado){
          productoSeleccionadoActual.add(producto)
          contadorProductos.value++
      }else{
          productoSeleccionadoActual.remove(producto)
          contadorProductos.value--
      }
        productosSeleccionados.value = productoSeleccionadoActual
    }

    fun eliminarProducto(producto:Producto){
        val productoSeleccionadoActual = productosSeleccionados.value.toMutableList()
        if (productoSeleccionadoActual.contains(producto)){
            productoSeleccionadoActual.remove(producto)
            contadorProductos.value--
            productosSeleccionados.value= productoSeleccionadoActual
        }
    }

    fun seleccionarTodos(seleccionar:Boolean){
        productosSeleccionados.value = if (seleccionar){
            listaProductos.toList()
        }else{
            emptyList()
        }
        contadorProductos.value = if (seleccionar){
            listaProductos.size
        }else{
            0
        }
    }

    fun resetearProductosSeleccionados() {
        productosSeleccionados.value = emptyList()
        contadorProductos.value = 0
    }



}