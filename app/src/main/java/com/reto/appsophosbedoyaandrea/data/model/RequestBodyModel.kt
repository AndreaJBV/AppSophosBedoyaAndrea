package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class RequestBodyModel(
    @SerializedName("TipoId")         val TipoId: String?,
    @SerializedName("Identificacion") val Identificacion: String?,
    @SerializedName("Nombre")         val Nombre: String?,
    @SerializedName("Apellido")       val Apellido: String?,
    @SerializedName("Ciudad")         val Ciudad: String?,
    @SerializedName("Correo")         val Correo: String?,
    @SerializedName("TipoAdjunto")    val TipoAdjunto: String?,
    @SerializedName("Adjunto")        val Adjunto:String?
)
