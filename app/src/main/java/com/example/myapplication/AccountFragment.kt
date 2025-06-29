package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 🔢 Montos (ejemplo fijo)
        val montoTotal = 6500
        val montoPagado = 1800
        val montoPendiente = montoTotal - montoPagado

        // Actualizar TextViews
        view.findViewById<TextView>(R.id.monto_total).text = "$${montoTotal}"
        view.findViewById<TextView>(R.id.monto_pagado).text = "$${montoPagado}"
        view.findViewById<TextView>(R.id.monto_pendiente).text = "$${montoPendiente}"

        // Lista de pagos
        val paymentList = listOf(
            PaymentItem("10/02/2025", "1,300.00"),
            PaymentItem("10/03/2025", "1,300.00"),
            PaymentItem("10/04/2025", "1,300.00"),
            PaymentItem("10/05/2025", "1,300.00"),
            PaymentItem("10/06/2025", "1,300.00")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.payment_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PaymentAdapter(paymentList)

        val btnVerPagos = view.findViewById<View>(R.id.pay_button)
        btnVerPagos.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DataPaymentFragment())
                .addToBackStack(null)
                .commit()
        }
    }


}