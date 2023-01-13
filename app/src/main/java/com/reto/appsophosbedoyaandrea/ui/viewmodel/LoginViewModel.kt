package com.reto.appsophosbedoyaandrea.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reto.appsophosbedoyaandrea.data.model.LoginModel
import com.reto.appsophosbedoyaandrea.data.network.LoginApi
import com.reto.appsophosbedoyaandrea.data.network.OficceApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class LoginViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    private val idUsuario = MutableLiveData<String>()
    private val clave = MutableLiveData<String>()
    var message2 : String = ""
    var message3 : String = ""
    var singUp : Boolean = false
    var user : MutableList<String> = mutableListOf()




    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status
    /**
     * Call getLogin() on init so we can display status immediately.
     */
    /*init {
        getLogin(idUsuario.toString(), clave.toString())
    }*/

    fun getLogin(idUsuario: String, clave: String):  MutableList<String>{
        runBlocking{
            try {

                val result = LoginApi.retrofitService.getUserLogin(idUsuario, clave)
                _status.value = "Success: ${result.body()} "
                println("Entro al try")

                if(result.body()?.acceso == true){
                    user.add(result.body()?.nombre.toString())
                    user.add(result.body()?.apellido.toString())
                    user.add(result.body()?.acceso.toString())


                }else{
                    message3 = "Start denied"
                }

            } catch (e: Exception) {
                println("Entro al catch")
                _status.value = "Failure: ${e.message}"
            }
            println( _status.value)
            return@runBlocking user
        }
        return user
    }


}