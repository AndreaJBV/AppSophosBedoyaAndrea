package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class GetSendDocumentModel( //The response whit retrofit is convert in this model
    @SerializedName("IdRegistro")       val IdRegistro: String,
    @SerializedName("Fecha")            val Fecha: String,
    @SerializedName("TipoId")           val TipoId: Number,
    @SerializedName("Identificacion")   val Identificacion: String,
    @SerializedName("Nombre")           val Nombre: String,
    @SerializedName("Apellido")         val Apellido: String,
    @SerializedName("Ciudad")           val Ciudad: String,
    @SerializedName("Correo")           val Correo: String,
    @SerializedName("TipoAdjunto")      val TipoAdjunto: String,
    @SerializedName("Adjunto")          val Adjunto: String

)
