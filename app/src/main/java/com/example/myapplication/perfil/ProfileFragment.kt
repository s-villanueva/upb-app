package com.example.myapplication.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Obtener SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)

        val codigo = sharedPref.getString("codigo", "")
        val nombres = sharedPref.getString("nombres", "")
        val apellidos = sharedPref.getString("apellidos", "")
        val correo = sharedPref.getString("correo", "")

        // Buscar y asignar a los TextViews
        view.findViewById<TextView>(R.id.name).text = "$nombres $apellidos"
        view.findViewById<TextView>(R.id.student_id).text = codigo
        view.findViewById<TextView>(R.id.email).text = correo

        // Otros campos que aún no llegan del backend
        view.findViewById<TextView>(R.id.carrera).text = "Ingeniería"
        view.findViewById<TextView>(R.id.phone).text = "No definido"
        view.findViewById<TextView>(R.id.address).text = "No definido"

        return view
    }

}