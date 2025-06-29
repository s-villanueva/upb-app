package com.example.myapplication.salaEstudio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StudyRoomFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorarioAdapter

    // Reservas ocupadas según la sala (simulado)
    private val reservasPorSala = mapOf(
        "Sala B" to listOf(8 to 10, 13 to 15),
        "Sala D" to listOf(10 to 12),
        "Sala C" to listOf(14 to 16),
        "Sala E" to listOf()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_study_room, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mostrar fecha actual
        val fechaEditText = view.findViewById<EditText>(R.id.fecha)
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val hoy = Calendar.getInstance()
        fechaEditText.setText(formatoFecha.format(hoy.time))

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.horariosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = HorarioAdapter()
        recyclerView.adapter = adapter

        // Configurar Spinner de salas
        val salaSpinner = view.findViewById<Spinner>(R.id.salasSpinner)
        salaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val salaSeleccionada = parent.getItemAtPosition(position).toString()
                val ocupadas = reservasPorSala[salaSeleccionada] ?: emptyList()
                val disponibles = generarBloquesDisponibles(8, 20, ocupadas)
                adapter.setData(disponibles)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun generarBloquesDisponibles(
        inicioDia: Int,
        finDia: Int,
        ocupadas: List<Pair<Int, Int>>
    ): List<Pair<Int, Int>> {
        val disponibles = mutableListOf<Pair<Int, Int>>()
        val ordenadas = ocupadas.sortedBy { it.first }
        var actual = inicioDia

        for ((ini, fin) in ordenadas) {
            if (ini - actual >= 2) {
                disponibles.add(actual to ini)
            }
            actual = maxOf(actual, fin)
        }

        if (finDia - actual >= 2) {
            disponibles.add(actual to finDia)
        }

        return disponibles
    }
}