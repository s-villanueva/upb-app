package com.example.myapplication.buses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

data class BusItem(
    val ruta: String,
    val horaSalida: String,
)


class BusAdapter(private val busList: List<BusItem>) :
    RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

    inner class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ruta = itemView.findViewById<TextView>(R.id.textRouteName)
        val hora = itemView.findViewById<TextView>(R.id.textRouteSchedule)
        // Ya no necesitamos 'origen' ni 'destino'
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bus_route, parent, false)
        return BusViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val item = busList[position]
        holder.ruta.text = item.ruta
        holder.hora.text = item.horaSalida
    }

    override fun getItemCount() = busList.size
}

