package com.example.myapplication.salaEstudio

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class HorarioItem(
    val horaInicio: Int,
    val horaFin: Int,
    val disponible: Boolean
)

class HorarioAdapter : RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

    private var horarios: List<Pair<Int, Int>> = listOf()

    fun setData(nuevosHorarios: List<Pair<Int, Int>>) {
        horarios = nuevosHorarios
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_list_item_1, parent, false)
        return HorarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
        val (inicio, fin) = horarios[position]
        holder.textView.text = "Disponible: %02d:00 - %02d:00".format(inicio, fin)
    }

    override fun getItemCount() = horarios.size

    inner class HorarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text1)
    }
}
