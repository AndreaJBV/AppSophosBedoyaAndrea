package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class RecyclerModel(
    @SerializedName("fecha")       val fecha: String,
    @SerializedName("tipoAdjunto") val tipoAdjunto: String,
    @SerializedName("nombre")      val nombre: String,
    @SerializedName("apellido")    val apellido: String

)
