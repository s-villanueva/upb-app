package com.example.myapplication.buses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BusViewModel : ViewModel() {
    private val _busList = MutableLiveData<List<BusItem>>()
    val busList: LiveData<List<BusItem>> get() = _busList

    fun loadBuses() {
        // Datos de prueba
        _busList.value = listOf(
            BusItem("Ruta A - Centro → Campus", "07:30"),
            BusItem("Ruta B - Sur → Campus", "08:00"),
            BusItem("Ruta C - Norte → Campus", "08:30"),
            BusItem("Ruta D - Campus → Centro", "12:30"),
            BusItem("Ruta E - Campus → Sur", "13:00")
        )
    }
}