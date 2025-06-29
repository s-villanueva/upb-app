package com.example.myapplication.pagos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

// Representa un pago con fecha y monto
data class PaymentItem(val date: String, val amount: String)

// Adaptador para el RecyclerView
class PaymentAdapter(private val payments: List<PaymentItem>) :
    RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {

    class PaymentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateText: TextView = view.findViewById(R.id.text_date)
        val amountText: TextView = view.findViewById(R.id.text_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_payment_row, parent, false)
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val item = payments[position]
        holder.dateText.text = item.date
        holder.amountText.text = item.amount
    }

    override fun getItemCount(): Int = payments.size
}