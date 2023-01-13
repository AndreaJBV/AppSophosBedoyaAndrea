package com.reto.appsophosbedoyaandrea.ui.viewmodel


import androidx.lifecycle.ViewModel

import kotlinx.coroutines.runBlocking


class Menu2ViewModel : ViewModel() {

    var isDarkModeEnabled2 : Boolean = false

    /*init {
        getLogin(idUsuario.toString(), clave.toString())
    }*/

    fun getModeDarkLigth(isDarkModeEnabled : Boolean):  Boolean{
        runBlocking{
            if(isDarkModeEnabled == false){
                isDarkModeEnabled2 = true
            }else{
                isDarkModeEnabled2 = false
            }
        }
        return isDarkModeEnabled2
    }


}