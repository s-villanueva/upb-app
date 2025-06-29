package com.example.myapplication.pagos

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class DataPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnElegirMetodoPago = view.findViewById<Button>(R.id.pay_button)
        val numeroDoc = view.findViewById<EditText>(R.id.numero_documento)
        val confirmacionDoc = view.findViewById<EditText>(R.id.confirmacion_documento)
        val correo = view.findViewById<EditText>(R.id.correo_electronico)
        val confirmacionCorreo = view.findViewById<EditText>(R.id.confirmacion_correo_electronico)

        btnElegirMetodoPago.setOnClickListener {
            val doc = numeroDoc.text.toString().trim()
            val docConfirm = confirmacionDoc.text.toString().trim()
            val email = correo.text.toString().trim()
            val emailConfirm = confirmacionCorreo.text.toString().trim()

            when {
                doc.isEmpty() || docConfirm.isEmpty() || email.isEmpty() || emailConfirm.isEmpty() -> {
                    Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                }
                doc != docConfirm -> {
                    Toast.makeText(requireContext(), "Los números de documento no coinciden", Toast.LENGTH_SHORT).show()
                }
                email != emailConfirm -> {
                    Toast.makeText(requireContext(), "Los correos no coinciden", Toast.LENGTH_SHORT).show()
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(requireContext(), "El correo ingresado no es válido", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MethodPaymentFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

    }

}