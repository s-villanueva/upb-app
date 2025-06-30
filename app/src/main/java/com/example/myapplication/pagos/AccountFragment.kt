package com.example.myapplication.pagos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.network.RetrofitServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class AccountFragment : Fragment() {

    private lateinit var montoTotalTextView: TextView
    private lateinit var montoPagadoTextView: TextView
    private lateinit var montoPendienteTextView: TextView
    private lateinit var montoPagadoBig : TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencias a vistas
        montoTotalTextView = view.findViewById(R.id.monto_total)
        montoPagadoTextView = view.findViewById(R.id.monto_pagado)
        montoPendienteTextView = view.findViewById(R.id.monto_pendiente)
        recyclerView = view.findViewById(R.id.payment_list)
        montoPagadoBig = view.findViewById(R.id.amount_due)
        // Código de usuario desde SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("UserData", AppCompatActivity.MODE_PRIVATE)
        val codigoUsuario = sharedPref.getString("codigo", null)

        if (codigoUsuario != null) {
            obtenerEstadoCuenta(codigoUsuario.toLong())
        } else {
            Toast.makeText(requireContext(), "No se encontró el código de usuario", Toast.LENGTH_SHORT).show()
        }

        val btnVerPagos = view.findViewById<View>(R.id.pay_button)
        btnVerPagos.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DataPaymentFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun obtenerEstadoCuenta(codigo: Long) {
        val retrofitService = RetrofitServiceFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {
            try {
            val response = retrofitService.getEstadoCuenta(codigo)
            val estado = response.estado_cuenta

            withContext(Dispatchers.Main) {
                val montoTotal = estado.sumOf { it.Importe_Pagar }
                val montoPagado = estado.sumOf { it.Importe_Pagado }
                val montoPendiente = estado.sumOf { it.Importe_Por_Pagar }

                montoTotalTextView.text = "Bs.$montoTotal"
                montoPagadoTextView.text = "Bs.$montoPagado"
                montoPendienteTextView.text = "Bs.$montoPendiente"
                montoPagadoBig.text = "Bs.$montoPagado"

                val cuotaMensual = montoPendiente / 5
                val listaPlanPagos = mutableListOf<PaymentItem>()

                for (i in 1..5) {
                    val concepto = "Cuota Mes $i"
                    val monto = "Bs. %.2f".format(cuotaMensual)
                    listaPlanPagos.add(PaymentItem(concepto, monto))
                }
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = PaymentAdapter(listaPlanPagos)



            }
            } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    requireContext(),
                    "Error al cargar estado de cuenta",
                    Toast.LENGTH_SHORT
                ).show()
            }
            }
        }
    }
}