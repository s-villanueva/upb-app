package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.example.myapplication.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainMenu : AppCompatActivity(), HomeFragment.OnHomeButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_menu)

        // Navegación del BottomNavigationView
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, HomeFragment.newInstance("", ""))
                    }
                    true
                }
                R.id.achievementsFragment -> {
                    Toast.makeText(this, "Sección de logros próximamente disponible", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.profileFragment -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, ProfileFragment())
                    }
                    true
                }
                else -> false
            }
        }


        // Mostrar HomeFragment por defecto
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, HomeFragment.newInstance("", ""))
            }
        }

        // Ajustar padding para barras del sistema
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

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
            R.id.btnAccount -> {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, AccountFragment())
                    addToBackStack(null)
                }
            }
        }
    }
}
