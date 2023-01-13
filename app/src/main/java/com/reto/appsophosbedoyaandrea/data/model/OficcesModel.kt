package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class OficcesModel( //The response whit retrofit is convert in this model
    @SerializedName("Items")         val Items: MutableList<OficceModel>,
    @SerializedName("Count")         val Count: Number,
    @SerializedName("ScannedCount")  val ScannedCount : Number

)
