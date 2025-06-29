package com.example.myapplication.model

data class EstadoCuentaItem(
    val Concepto: String,
    val Importe_Pagar: Double,
    val Descuento: Double,
    val Importe_Pagado: Double,
    val Importe_Por_Pagar: Double
)
data class EstadoCuentaResponse(
    val usuario_codigo: String,
    val estado_cuenta: List<EstadoCuentaItem>
)


