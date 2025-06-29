package com.example.myapplication.network


import com.example.myapplication.model.EstadoCuentaItem
import com.example.myapplication.model.EstadoCuentaResponse
import com.example.myapplication.model.RespuestaLogin
import com.example.myapplication.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Modelo de datos que enviarás
data class LoginRequest(val codigo: String, val contrasena: String)

interface RetrofitService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): RespuestaLogin
    @GET("api/estado-cuenta/{codigo}")
    suspend fun getEstadoCuenta(@Path("codigo") codigo: Long): EstadoCuentaResponse
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.68.57/UPBMovil/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }
}