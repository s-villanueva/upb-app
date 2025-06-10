package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StudyRoom : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.studyroom)

        val rootView = findViewById<android.view.View>(R.id.main_layout_studyroom)
        val bt1 = findViewById<Button>(R.id.reservarSala)
        bt1.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

        val tvFecha = findViewById<TextView>(R.id.fecha)
        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tvFecha.text = fechaActual

        val editHoraIn = findViewById<EditText>(R.id.hora_in)
        val editHoraOut = findViewById<EditText>(R.id.hora_out)

        editHoraIn.addTextChangedListener(object : TextWatcher {
            private var isEditing = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return
                isEditing = true

                val digits = s.toString().filter { it.isDigit() }

                val formatted = when {
                    digits.length >= 3 -> "${digits.take(2)}:${digits.drop(2).take(2)}"
                    digits.length >= 1 -> digits
                    else -> ""
                }

                editHoraIn.setText(formatted)
                editHoraIn.setSelection(formatted.length)
                isEditing = false
            }
        })

        editHoraOut.addTextChangedListener(object : TextWatcher {
            private var isEditing = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isEditing) return
                isEditing = true

                val digits = s.toString().filter { it.isDigit() }

                val formatted = when {
                    digits.length >= 3 -> "${digits.take(2)}:${digits.drop(2).take(2)}"
                    digits.length >= 1 -> digits
                    else -> ""
                }

                editHoraOut.setText(formatted)
                editHoraOut.setSelection(formatted.length)
                isEditing = false
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
