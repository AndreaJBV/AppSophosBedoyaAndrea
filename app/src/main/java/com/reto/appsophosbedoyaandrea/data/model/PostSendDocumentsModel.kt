package com.reto.appsophosbedoyaandrea.data.model

import com.google.gson.annotations.SerializedName

data class PostSendDocumentsModel( //The response whit retrofit is convert in this model
    @SerializedName("put")       val put: Boolean,
)
