package com.reto.appsophosbedoyaandrea.data.network

import com.reto.appsophosbedoyaandrea.data.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SendDocumentsApiService {

    @GET("/RS_Documentos")
    suspend fun getAllDocuments(
        @Query("correo") correo: String): Response<GetSendDocumentsModel>

    @POST("/RS_Documentos")
    suspend fun postDocuments(@Body requestBodyModel: RequestBodyModel): Response<PostSendDocumentsModel>
}

object SendDocumentsApi {
    val retrofitService : SendDocumentsApiService by lazy {
        retrofit.create(SendDocumentsApiService::class.java)
    }
}