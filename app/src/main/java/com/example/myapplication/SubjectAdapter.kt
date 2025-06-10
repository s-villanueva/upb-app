package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubjectAdapter(private val subjectList: List<Subject>) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvSubjectName)
        val dates: TextView = itemView.findViewById(R.id.tvDates)
        val time: TextView = itemView.findViewById(R.id.tvTime)
        val room: TextView = itemView.findViewById(R.id.tvRoom)
        val professors: TextView = itemView.findViewById(R.id.tvProfessors)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjectList[position]
        holder.name.text = subject.name
        holder.dates.text = "${subject.startDate} — ${subject.endDate}"
        holder.time.text = subject.time
        holder.room.text = subject.room
        holder.professors.text = subject.professors.joinToString("\n")
    }

    override fun getItemCount() = subjectList.size
}
