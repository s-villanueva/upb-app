package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit

class MainMenu : AppCompatActivity(), HomeFragment.OnHomeButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_menu)

        // Mostrar HomeFragment por defecto
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, HomeFragment.newInstance("", ""))
            }
        }

        // Ajustar padding para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Implementación de la interfaz de HomeFragment para manejar clics en botones
    override fun onButtonClicked(id: Int) {
        when (id) {
            R.id.btnReserve -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, StudyRoomFragment())
                    addToBackStack(null)
                }
            }
            R.id.btnSchedule -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, SchedulesFragment())
                    addToBackStack(null)
                }
            }
            else -> {
                // Opcional: manejar otros botones o default
            }
        }
    }
}
