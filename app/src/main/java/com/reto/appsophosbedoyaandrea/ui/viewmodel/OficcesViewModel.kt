package com.reto.appsophosbedoyaandrea.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reto.appsophosbedoyaandrea.data.network.OficceApi
import kotlinx.coroutines.runBlocking

class OficcesViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val _status2 = MutableLiveData<String>()
    private val _status3 = MutableLiveData<String>()
    private val _status4 = MutableLiveData<String>()
    private val _status5 = MutableLiveData<String>()
    private val _status6 = MutableLiveData<String>()
    private val _status7 = MutableLiveData<String>()
    private val _status8 = MutableLiveData<String>()
    var arrayCities1 : MutableList<String> = mutableListOf()
    var arrayCities2 : MutableList<String> = mutableListOf()
    var allLength: MutableList<String> = mutableListOf()
    var allLatitudes : MutableList<String> = mutableListOf()
    var allNameOficces : MutableList<String> = mutableListOf()
    var allLengthCity: MutableList<String> = mutableListOf()
    var allLatitudesCity : MutableList<String> = mutableListOf()
    var allNameOficcesCity : MutableList<String> = mutableListOf()



    var size : Int = 0
    var size2 : Int = 0
    var size3 : Int = 0
    var size4 : Int = 0
    var size5 : Int = 0
    var size6 : Int = 0
    var size7 : Int = 0
    var size8 : Int = 0

    var message2 : String = ""
    var message3 : String = ""

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    val status2: LiveData<String> = _status2
    val status3: LiveData<String> = _status3
    val status4: LiveData<String> = _status4
    val status5: LiveData<String> = _status5
    val status6: LiveData<String> = _status6
    val status7: LiveData<String> = _status7
    val status8: LiveData<String> = _status8

    /*init {
        getOficceByCity(ciudad.toString())
        getAllOficces()
    }*/


    //______________ FUNCTIONS OF ALL OFICCES _________________
    fun getAllOficces() : MutableList<String> {

        runBlocking{
            try {
                val result2 = OficceApi.retrofitService.getAllOficces()
                _status.value = "Success: ${result2.body()} "
                println("Entro al try")
                size2 = result2.body()?.Count?.toInt()!!

                if(result2.body()?.Count != 0){
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size2){
                        arrayCities2.add(result2.body()?.Items?.get(i)?.Ciudad.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status2.value = "Failure: ${e.message}"
            }
            println( _status2.value)
            println("Mostrando todas las ciudades desde el OfficesViewModel")
            println(arrayCities2.toString())
            return@runBlocking arrayCities2
        }
        return arrayCities2
    }


    fun getAllLongitud() : MutableList<String> {

        runBlocking{
            try {
                val result3 = OficceApi.retrofitService.getAllOficces()
                _status3.value = "Success: ${result3.body()} "
                println("Entro al try")
                size3 = result3.body()?.Count?.toInt()!!

                if(result3.body()?.Count != 0){
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size3){
                        allLength.add(result3.body()?.Items?.get(i)?.Longitud.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status3.value = "Failure: ${e.message}"
            }
            println( _status3.value)
            println("Mostrando todas las longitudes desde el OfficesViewModel")
            println(allLength.toString())
            return@runBlocking allLength
        }
        return allLength
    }

    fun getAllLatitude() : MutableList<String> {

        runBlocking{
            try {
                val result4= OficceApi.retrofitService.getAllOficces()
                _status4.value = "Success: ${result4.body()} "
                println("Entro al try")
                size4 = result4.body()?.Count?.toInt()!!

                if(result4.body()?.Count != 0){
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size4){
                        allLatitudes.add(result4.body()?.Items?.get(i)?.Latitud.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status4.value = "Failure: ${e.message}"
            }
            println( _status4.value)
            println("Mostrando todas las latitudes el OfficesViewModel")
            println(allLatitudes.toString())
            return@runBlocking allLatitudes
        }
        return allLatitudes
    }


    fun getAllNameOficce() : MutableList<String> {

        runBlocking{
            try {
                val result5= OficceApi.retrofitService.getAllOficces()
                _status5.value = "Success: ${result5.body()} "
                println("Entro al try")
                size5 = result5.body()?.Count?.toInt()!!

                if(result5.body()?.Count != 0){
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size4){
                        allNameOficces.add(result5.body()?.Items?.get(i)?.Nombre.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status5.value = "Failure: ${e.message}"
            }
            println( _status5.value)
            println("Mostrando todas los nombres de las oficinas desde el OfficesViewModel")
            println(allNameOficces.toString())
            return@runBlocking allNameOficces
        }
        return allNameOficces
    }

    //______________ FUNCTIONS OF ALL OFICCES BY CITY _________________
    fun getOficceByCity(ciudad: String) : MutableList<String> {
        runBlocking{
            try {

                var result1 = OficceApi.retrofitService.getOficcesByCity(ciudad)
                _status2.value = "Success: ${result1.body()} "
                println("Entro al try")
                size = result1.body()?.Count?.toInt()!!
                println(size)

                if(result1.body()?.Count != 0){
                    message2 = "Si se encontraron oficinas"
                    println("Entro al if")
                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size){
                        arrayCities1.add(result1.body()?.Items?.get(i)?.Ciudad.toString())
                        i++
                    }

                    println("Mostrando las ciudades dentro del if: ")
                    println(arrayCities1.toString())
                    println(result1.body()?.Items?.get(0)?.Ciudad.toString())

                }else{
                    message2 = "No se encontraron oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar oficinas."
                println("Entro al catch")
                _status2.value = "Failure: ${e.message}"
            }
            println( _status.value)
            println("Mostrando las ciudades CITY desde el OfficesViewModel")
            println(arrayCities1.toString())
            return@runBlocking arrayCities1
        }
        return arrayCities1
    }

    fun getAllLongitudCity(ciudad: String) : MutableList<String> {

        runBlocking{
            try {
                val result6 = OficceApi.retrofitService.getOficcesByCity(ciudad)
                _status6.value = "Success: ${result6.body()} "
                println("Entro al try")
                size6 = result6.body()?.Count?.toInt()!!

                if(result6.body()?.Count != 0){
                    message2 = "Si se encontraron longitudes"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size6){
                        allLengthCity.add(result6.body()?.Items?.get(i)?.Longitud.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron longitudes"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar longitudes."
                println("Entro al catch")
                _status6.value = "Failure: ${e.message}"
            }
            println( _status6.value)
            println("Mostrando todas las longitudes CITY desde el OfficesViewModel")
            println(allLengthCity.toString())
            return@runBlocking allLengthCity
        }
        return allLengthCity
    }

    fun getAllLatitudeCity(ciudad: String) : MutableList<String> {

        runBlocking{
            try {
                val result7= OficceApi.retrofitService.getOficcesByCity(ciudad)
                _status7.value = "Success: ${result7.body()} "
                println("Entro al try")
                size7 = result7.body()?.Count?.toInt()!!

                if(result7.body()?.Count != 0){
                    message2 = "Si se encontraron latitudes"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size7){
                        allLatitudesCity.add(result7.body()?.Items?.get(i)?.Latitud.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron latitudes"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar latitudes."
                println("Entro al catch")
                _status7.value = "Failure: ${e.message}"
            }
            println( _status7.value)
            println("Mostrando todas las latitudes CITY el OfficesViewModel")
            println(allLatitudesCity.toString())
            return@runBlocking allLatitudesCity
        }
        return allLatitudesCity
    }


    fun getAllNameOficceCity(ciudad: String) : MutableList<String> {

        runBlocking{
            try {
                val result8= OficceApi.retrofitService.getOficcesByCity(ciudad)
                _status8.value = "Success: ${result8.body()} "
                println("Entro al try")
                size8 = result8.body()?.Count?.toInt()!!

                if(result8.body()?.Count != 0){
                    message2 = "Si se encontraron nombres de oficinas"
                    println("Entro al if")

                    var i = 0
                    //result2.body()?.Items?.get(i)?.Ciudad.toString()
                    while (i <= size8){
                        allNameOficcesCity.add(result8.body()?.Items?.get(i)?.Nombre.toString())
                        i++
                    }

                }else{
                    message2 = "No se encontraron nombres de oficinas"
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                message2 = "No es correcto buscar nombres de oficinas."
                println("Entro al catch")
                _status8.value = "Failure: ${e.message}"
            }
            println( _status8.value)
            println("Mostrando todas los nombres de las oficinas CITY desde el OfficesViewModel")
            println(allNameOficcesCity.toString())
            return@runBlocking allNameOficcesCity
        }
        return allNameOficcesCity
    }
}









