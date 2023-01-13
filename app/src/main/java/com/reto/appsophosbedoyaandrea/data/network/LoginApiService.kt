package com.reto.appsophosbedoyaandrea.data.network

import com.reto.appsophosbedoyaandrea.data.model.LoginModel
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

interface LoginApiService {

    @GET("/RS_Usuarios")
    suspend fun getUserLogin(
        @Query("idUsuario") idUsuario: String,
        @Query("clave") clave: String): Response<LoginModel>
}

object LoginApi {
    val retrofitService : LoginApiService by lazy {
        retrofit.create(LoginApiService::class.java)
    }
}