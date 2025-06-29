package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ScheduleItem(
    val materia: String,
    val fecha: String,
    val hora: String,
    val aula: String,
    val docente: String
)

class ScheduleAdapter(private val scheduleList: List<ScheduleItem>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.optionsTitle)
        val date: TextView = view.findViewById(R.id.dateText)
        val hour: TextView = view.findViewById(R.id.hourText)
        val classroom: TextView = view.findViewById(R.id.classroomText)
        val teacher: TextView = view.findViewById(R.id.teacherText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule_box, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item = scheduleList[position]
        holder.title.text = item.materia
        holder.date.text = item.fecha
        holder.hour.text = item.hora
        holder.classroom.text = item.aula
        holder.teacher.text = item.docente
    }

    override fun getItemCount(): Int = scheduleList.size
}
