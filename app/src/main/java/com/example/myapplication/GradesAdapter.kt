package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

data class Grade(
    val subject: String,
    val grade1: Int,
    val falta1: Int,
    val grade2: Int,
    val falta2: Int,
    val grade3: Int,
    val falta3: Int,
    val finalGrade: Int
)

class GradesAdapter : ListAdapter<Grade, GradesAdapter.GradeViewHolder>(
    object : DiffUtil.ItemCallback<Grade>() {
        override fun areItemsTheSame(oldItem: Grade, newItem: Grade) = oldItem.subject == newItem.subject
        override fun areContentsTheSame(oldItem: Grade, newItem: Grade) = oldItem == newItem
    }
) {

    class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Grade) {
            // Título y nota final
            itemView.findViewById<TextView>(R.id.txtSubject).text = item.subject
            itemView.findViewById<TextView>(R.id.txtFinalGrade).text = item.finalGrade.toString()

            // Primer parcial
            itemView.findViewById<TextView>(R.id.PrimerParcial).text = item.grade1.toString()
            itemView.findViewById<TextView>(R.id.FaltaPrimerParcial).text = item.falta1.toString()

            // Segundo parcial
            itemView.findViewById<TextView>(R.id.SegundoParcial).text = item.grade2.toString()
            itemView.findViewById<TextView>(R.id.FaltaSegundoParcial).text = item.falta2.toString()

            // Final
            itemView.findViewById<TextView>(R.id.Final).text = item.grade3.toString()
            itemView.findViewById<TextView>(R.id.FaltaFinal).text = item.falta3.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_grade, parent, false)
        return GradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
