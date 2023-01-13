package com.reto.appsophosbedoyaandrea.ui.view

import android.content.Intent
import android.content.SharedPreferences
import android.hardware.fingerprint.FingerprintManager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.reto.appsophosbedoyaandrea.R
import com.reto.appsophosbedoyaandrea.data.network.LoginListener
import com.reto.appsophosbedoyaandrea.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginListener {

    var isDarkModeEnabled = false

    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_FILENAME = "com.example.myapp.prefs"


    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //Call all components of Send Documents
        val layout_login: ConstraintLayout = findViewById(R.id.layout_login)
        val textIngresarDatos: TextView = findViewById(R.id.textIngresarDatos)
        val LayoutEmail: LinearLayout = findViewById(R.id.LayoutEmail)
        val LayoutIconoUser: LinearLayout = findViewById(R.id.LayoutIconoUser)
        val iconoUser: ImageView = findViewById(R.id.iconoUser)
        val TextEmailAddress : TextView = findViewById(R.id.TextEmailAddress)
        val LayoutPassword1: LinearLayout = findViewById(R.id.LayoutPassword1)
        val LayoutKey: LinearLayout = findViewById(R.id.LayoutKey)
        val TextPassword : TextView = findViewById(R.id.TextPassword)
        val imageView: ImageView = findViewById(R.id.imageView)
        val LayoutBtnSingUp: LinearLayout = findViewById(R.id.LayoutBtnSingUp)
        val buttonSingUp: Button = findViewById(R.id.buttonSingUp)
        val LayoutBtnSingUp2: LinearLayout = findViewById(R.id.LayoutBtnSingUp)
        val buttonSingUp2: Button = findViewById(R.id.buttonSingUp2)


        val preferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        isDarkModeEnabled = preferences.getBoolean("dark_mode", false)
        AppCompatDelegate.setDefaultNightMode(if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

        isDarkModeEnabled = !isDarkModeEnabled

        if (isDarkModeEnabled) {
            //AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            }

        } else {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            }

        }


        //Call idUsuario (email)
        val email = findViewById<EditText>(R.id.TextEmailAddress)
        val idUsuario = email.text

        //Call clave (password)
        val password = findViewById<EditText>(R.id.TextPassword)
        val clave = password.text

        //Call object observe
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        //Call Button
        val rollButton: Button = findViewById(R.id.buttonSingUp)
        rollButton.setOnClickListener {

            if (idUsuario.isNullOrEmpty() || clave.isNullOrEmpty()) {
                onMessage("Email o contraseña inválida")
                println(idUsuario)
                println(clave)
            } else {
                val userLogin = viewModel.getLogin(idUsuario.toString(), clave.toString())
                onMessage(viewModel.message2)
                println("Entro al la app")
                println(userLogin[2])
                val access = userLogin[2]
                println(access)


                if (access == "true") {
                    val intent = Intent(this, Menu2Activity::class.java).apply {
                        println("Entró al menu 2")
                    }
                    intent.putExtra("idUsuario",idUsuario)
                    intent.putExtra("clave",clave)
                    intent.putExtra("name",userLogin[0])
                    startActivity(intent)
                }
            }

        }

        val rollButtonFingerprint: Button = findViewById(R.id.buttonSingUp2)
        //Call rollButtonFingerprint
        rollButtonFingerprint.setOnClickListener{
            println("Modo huella")


        }

    }


    override fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}






