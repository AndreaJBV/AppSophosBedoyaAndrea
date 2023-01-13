package com.reto.appsophosbedoyaandrea.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reto.appsophosbedoyaandrea.data.model.RequestBodyModel
import com.reto.appsophosbedoyaandrea.data.network.LoginApi
import com.reto.appsophosbedoyaandrea.data.network.OficceApi
import com.reto.appsophosbedoyaandrea.data.network.SendDocumentsApi
import kotlinx.coroutines.runBlocking

class SendDocumentsViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val _status2 = MutableLiveData<String>()
    private val correo = MutableLiveData<String>()
    private val requestBodyModel = MutableLiveData<RequestBodyModel>()


    var arrayCities1: MutableList<String> = mutableListOf()
    var arrayCities2: MutableList<String> = mutableListOf()

    //var arrayCities1 = MutableLiveData<MutableList<String>>()
    //var arrayCities2 = MutableLiveData<MutableList<String>>()
    var cities2 = MutableLiveData<MutableList<String>>()

    var size: Int = 0
    var size2: Int = 0
    var message2: String = ""
    var message3: String = ""
    var date: String = ""
    var typeAdjun: String = ""
    var name: String = ""
    var lastName : String = ""


    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    val status2: LiveData<String> = _status2

    /*init {
        getOficceByCity(ciudad.toString())
        getAllOficces()
    }*/

    fun getDocuments(idUsuario: String, clave: String): MutableList<String> {
        runBlocking {
            try {

                var result1 = OficceApi.retrofitService.getOficcesByCity(idUsuario)
                _status2.value = "Success: ${result1.body()} "
                println("Entro al try")
                size = result1.body()?.Count?.toInt()!!
                println(size)

                if (result1.body()?.Count != 0) {
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")
                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size) {
                        arrayCities1.add(result1.body()?.Items?.get(i)?.Ciudad.toString())
                        i++
                    }

                    println("Mostrando las ciudades dentro del if: ")
                    println(arrayCities1.toString())
                    println(result1.body()?.Items?.get(0)?.Ciudad.toString())

                } else {
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status2.value = "Failure: ${e.message}"
            }
            println(_status.value)
            println("Mostrando las ciudades desde el OfficesViewModel")
            println(arrayCities1.toString())
            return@runBlocking arrayCities1
        }
        return arrayCities1
    }

    fun getAllOficces(): MutableList<String> {

        runBlocking {
            try {
                val result2 = OficceApi.retrofitService.getAllOficces()
                _status.value = "Success: ${result2.body()} "
                println("Entro al try")
                size2 = result2.body()?.Count?.toInt()!!

                if (result2.body()?.Count != 0) {
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size2) {
                        arrayCities2.add(result2.body()?.Items?.get(i)?.Ciudad.toString())
                        i++
                    }
                } else {
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status.value = "Failure: ${e.message}"
            }
            println(_status.value)
            println("Mostrando todas las ciudades desde el OfficesViewModel")
            println(arrayCities2.toString())
            return@runBlocking arrayCities2
        }
        return arrayCities2
    }


    fun postDocument(requestBodyModel: RequestBodyModel) {
        runBlocking {
            try {
                val result3 = SendDocumentsApi.retrofitService.postDocuments(requestBodyModel)
                _status.value = "Success: ${result3.body()} "
                println("Mostrando la respuesta del post")
                println(result3)
                if (result3.body()?.put == true) {
                    message2 = "Envio correcto"
                } else {
                    message2 = "Envio Incorrecto"
                }


            } catch (e: Exception) {
                message2 = "No es correcto enviar la información."
                println("Entro al catch")
                _status.value = "Failure: ${e.message}"
            }
            println("Respuesta del PostDocument")
            println(_status.value)

        }
    }

    fun getDocuments(correo: String) : String? {
        runBlocking {
            try {

                val result4 = SendDocumentsApi.retrofitService.getAllDocuments(correo)
                _status.value = "Success: ${result4.body()} "
                println("Entro al try")


            } catch (e: Exception) {
                println("Entro al catch")
                _status.value = "Failure: ${e.message}"
            }
            println(_status.value)
            return@runBlocking _status.value

        }
        return _status.value

    }

}








