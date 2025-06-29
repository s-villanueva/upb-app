package com.example.myapplication.pagos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class MethodPaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_method_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Referencias a las tarjetas
        val cardCredit = view.findViewById<CardView>(R.id.card_credit)
        val cardQR = view.findViewById<CardView>(R.id.card_qr)
        val cardKryptos = view.findViewById<CardView>(R.id.card_kryptos)

        // Acciones (de momento, solo muestra Toast)
        cardCredit.setOnClickListener {
            Toast.makeText(requireContext(), "Pago con Tarjeta de Crédito", Toast.LENGTH_SHORT).show()
            // Aquí puedes cargar un fragmento o abrir una vista para ingresar los datos de tarjeta
        }

        cardQR.setOnClickListener {
            Toast.makeText(requireContext(), "Pago con QR", Toast.LENGTH_SHORT).show()
            // Puedes cargar un fragmento con código QR aquí
        }

        cardKryptos.setOnClickListener {
            Toast.makeText(requireContext(), "Pago con Kryptos", Toast.LENGTH_SHORT).show()
            // O fragmento de criptomonedas si se desea
        }
    }
}