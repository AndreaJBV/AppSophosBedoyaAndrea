package com.reto.appsophosbedoyaandrea.data.network

import com.reto.appsophosbedoyaandrea.data.model.OficcesModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://6w33tkx4f9.execute-api.us-east-1.amazonaws.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface OfficesApiService {

    @GET("/RS_Oficinas")
    suspend fun getOficcesByCity(
        @Query("ciudad") ciudad: String
    ): Response<OficcesModel>

    @GET("/RS_Oficinas")
    suspend fun getAllOficces(): Response<OficcesModel>

}

object OficceApi {
    val retrofitService : OfficesApiService by lazy {
        retrofit.create(OfficesApiService::class.java)
    }
}