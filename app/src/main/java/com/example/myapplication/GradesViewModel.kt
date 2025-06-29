package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GradesViewModel : ViewModel() {
    private val _grades = MutableLiveData<List<Grade>>()
    val grades: LiveData<List<Grade>> get() = _grades

    fun loadGrades() {
        // Datos de prueba
        _grades.value = listOf(
            Grade("Matemática", 85, 0, 88, 1, 92, 0, 88),
            Grade("Física", 78, 2, 80, 0, 85, 1, 81),
            Grade("Química", 90, 0, 87, 1, 89, 0, 89)
        )
    }
}
