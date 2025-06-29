package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.model.LoginRequest
import com.example.myapplication.network.RetrofitServiceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Referencias a vistas
        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val bt1 = findViewById<Button>(R.id.login_btn)
        val rootView = findViewById<android.view.View>(R.id.main_layout)

        // Ajuste de padding según las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Acción de login
        bt1.setOnClickListener {
            val codigo = etCodigo.text.toString()
            val contrasena = etContrasena.text.toString()
//            Toast.makeText(this, contrasena, Toast.LENGTH_SHORT).show()
            if (codigo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Campos vacíos", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            val retrofitService = RetrofitServiceFactory.makeRetrofitService()
            val request = LoginRequest(codigo, contrasena)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = retrofitService.login(request)
                    runOnUiThread {
                        Toast.makeText(this@Login, response.message, Toast.LENGTH_SHORT).show()
                        if (response.usuario != null) {
                            val intent = Intent(this@Login, MainMenu::class.java)
                            // Puedes enviar datos aquí si quieres pasarlos al menú
                            startActivity(intent)
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@Login,
                            "Error de red o credenciales incorrectas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
