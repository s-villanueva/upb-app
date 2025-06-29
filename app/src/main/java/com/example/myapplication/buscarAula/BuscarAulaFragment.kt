package com.example.myapplication.buscarAula

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class BuscarAulaFragment : Fragment() {

    private lateinit var etBuscarAula: EditText
    private lateinit var btnCalcularRuta: Button
    private lateinit var txtIndicaciones: TextView
    private lateinit var txtMapaSimulado: ImageView

    // Simulación de rutas a aulas
    private val rutasAulas = mapOf(
        "A101" to "Edificio A - Piso 1 - Cerca de la entrada principal.",
        "B203" to "Edificio B - Piso 2 - Al fondo del pasillo.",
        "C305" to "Edificio C - Piso 3 - Cerca del laboratorio de física."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buscar_aula, container, false)

        etBuscarAula = view.findViewById(R.id.etBuscarAula)
        btnCalcularRuta = view.findViewById(R.id.btnCalcularRuta)
        txtIndicaciones = view.findViewById(R.id.txtIndicaciones)
        txtMapaSimulado = view.findViewById(R.id.MapaSimulado)

        btnCalcularRuta.setOnClickListener {
            val aulaIngresada = etBuscarAula.text.toString().uppercase().trim()
            val indicacion = rutasAulas[aulaIngresada]

            if (indicacion != null) {
                txtIndicaciones.text = "Ruta al aula $aulaIngresada:\n$indicacion"
            } else {
                txtIndicaciones.text = "No se encontró una ruta para el aula \"$aulaIngresada\"."
            }
        }

        return view
    }
}