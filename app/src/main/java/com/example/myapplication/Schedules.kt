package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Schedules: AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SubjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedules)

        recyclerView = findViewById(R.id.subjectRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Simulación de datos como si vinieran del backend
        val subjectList = listOf(
            Subject(
                name = "Relational Databases",
                startDate = "02/04/25",
                endDate = "03/24/25",
                time = "10:00 – 12:00",
                room = "Room A1",
                professors = listOf("RODRIGUEZ", "ZEGADA JOSE")
            ),
            Subject(
                name = "Differential Equations",
                startDate = "02/04/25",
                endDate = "03/24/25",
                time = "14:30 – 16:30",
                room = "Room A2",
                professors = listOf("MEDRANO ROCHA", "DODOVROSKY")
            ),
            Subject(
                name = "English High Intermediate",
                startDate = "02/04/25",
                endDate = "06/30/25",
                time = "12:15 – 14:15",
                room = "Room B1",
                professors = listOf("MERCADO", "VILLAGOMEZ NICOLE")
            ),
            // Nueva materia 1
            Subject(
                name = "Computer Networks",
                startDate = "02/04/25",
                endDate = "06/30/25",
                time = "08:00 – 10:00",
                room = "Room B3",
                professors = listOf("GUTIERREZ", "SALAZAR")
            ),
            // Nueva materia 2
            Subject(
                name = "Artificial Intelligence",
                startDate = "02/04/25",
                endDate = "06/30/25",
                time = "16:45 – 18:45",
                room = "Room C1",
                professors = listOf("FERNANDEZ", "ALVAREZ")
            )
        )

        // Adaptador conectado a la lista
        adapter = SubjectAdapter(subjectList)
        recyclerView.adapter = adapter
    }
}