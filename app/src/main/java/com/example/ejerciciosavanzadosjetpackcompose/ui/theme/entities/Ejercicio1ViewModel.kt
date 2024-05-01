package com.example.ejerciciosavanzadosjetpackcompose.ui.theme.entities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ejerciciosavanzadosjetpackcompose.ui.theme.screens.listaProductos

class Ejercicio1ViewModel:ViewModel() {
    var productosSeleccionados = mutableStateOf(listOf<Producto>());
    var contadorProductos = mutableIntStateOf(0);
    var seleccionarTodos = mutableStateOf(false)


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
            listaProductos
        }else{
            emptyList()
        }
        contadorProductos.value = if (seleccionar){
            listaProductos.size
        }else{
            0
        }
    }



}