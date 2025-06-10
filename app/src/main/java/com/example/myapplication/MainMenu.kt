package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainMenu : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        var intent: Intent
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_menu)
        val b1 = findViewById<Button>(R.id.botonL)
        val b2 = findViewById<Button>(R.id.botonP)
        val b3 = findViewById<Button>(R.id.btnReserve)
        val b4 = findViewById<Button>(R.id.btnSchedule)
        b2.setOnClickListener {
            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        b1.setOnClickListener{
            Toast.makeText(this, "TU MAMÁ", Toast.LENGTH_SHORT).show()
        }
        b3.setOnClickListener{
            intent = Intent(this, StudyRoom::class.java)
            startActivity(intent)
        }
        b4.setOnClickListener {
            intent = Intent(this, Schedules::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}