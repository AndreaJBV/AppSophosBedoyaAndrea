package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class OficceModel(//The response whit retrofit is convert in this model
    @SerializedName("Ciudad")    val Ciudad: String,
    @SerializedName("Longitud")  val Longitud: String,
    @SerializedName("IdOficina") val IdOficina: Number,
    @SerializedName("Latitud")   val Latitud: String,
    @SerializedName("Nombre")    val Nombre: String

)
