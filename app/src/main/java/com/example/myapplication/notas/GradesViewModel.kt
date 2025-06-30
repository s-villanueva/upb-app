package com.example.myapplication.notas

import android.util.Log
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.RetrofitServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradesViewModel : ViewModel() {
    private val _grades = MutableLiveData<List<Grade>>()
    val grades: LiveData<List<Grade>> = _grades

    fun loadGrades(codigo: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val service = RetrofitServiceFactory.makeRetrofitService()
                val response = service.getNotas(codigo)
                val mapped = response.groupBy { it.materia }.map { (materia, items) ->
                    val g1 = items.getOrNull(0)?.puntaje ?: 0
                    val f1 = items.getOrNull(0)?.faltas ?: 0
                    val g2 = items.getOrNull(1)?.puntaje ?: 0
                    val f2 = items.getOrNull(1)?.faltas ?: 0
                    val g3 = items.getOrNull(2)?.puntaje ?: 0
                    val f3 = items.getOrNull(2)?.faltas ?: 0
                    val finalGrade = (g1*0.3 + g2*0.3 + g3*0.4)
                    Grade(materia, g1, f1, g2, f2, g3, f3, finalGrade.toInt())
                }
                _grades.postValue(mapped)
            } catch (e: Exception) {
                Log.d("GradesViewModel", "Error al cargar notas")
            }
        }
    }
}