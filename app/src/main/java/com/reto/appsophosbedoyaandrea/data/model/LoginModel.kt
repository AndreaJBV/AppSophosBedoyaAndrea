package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class LoginModel ( //The response whit retrofit is convert in this model
    @SerializedName("id")       val id: String,
    @SerializedName("nombre")   val nombre: String,
    @SerializedName("apellido") val apellido: String,
    @SerializedName("acceso")   val acceso: Boolean,
    @SerializedName("admin")    val admin: Boolean
)