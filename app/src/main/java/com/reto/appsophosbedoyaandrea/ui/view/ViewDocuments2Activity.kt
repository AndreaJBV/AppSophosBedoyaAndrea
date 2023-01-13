package com.reto.appsophosbedoyaandrea.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reto.appsophosbedoyaandrea.R
import com.reto.appsophosbedoyaandrea.data.model.RecyclerModel
import com.reto.appsophosbedoyaandrea.data.network.RecyclerViewAdapter
import com.reto.appsophosbedoyaandrea.ui.viewmodel.SendDocumentsViewModel
import java.util.*
import kotlin.collections.ArrayList

class ViewDocuments2Activity : AppCompatActivity() {

    var isDarkModeEnabled = false
    var isChangeLanguage = false


    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_FILENAME = "com.example.myapp.prefs"

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_documents2)

        //Call all components of Send Documents
        val allViewDocuments : ConstraintLayout = findViewById(R.id.allViewDocuments)
        val buttonRegresar: Button = findViewById(R.id.buttonRegresar)
        val imageBurgerMenu: ImageView = findViewById(R.id.imageBurgerMenu)
        val textDocumentos: TextView = findViewById(R.id.textDocumentos)
        val textViewResponse: TextView = findViewById(R.id.textViewResponse)




        val drawableMenuDark = ContextCompat.getDrawable(this, R.drawable.menu_dark)
        val drawableMenuLight = ContextCompat.getDrawable(this, R.drawable.hamburger_menu)
        val drawableArrowLefthDark = ContextCompat.getDrawable(this, R.drawable.arrow_lefth_dark)
        val drawableArrowLefthLight = ContextCompat.getDrawable(this, R.drawable.keyboard_backspace)
        val drawableLineDark = ContextCompat.getDrawable(this, R.drawable.line_dark)
        val drawableLineLight = ContextCompat.getDrawable(this, R.drawable.line_1)

        //Call object observe
        val viewModelSendDocuments = ViewModelProvider(this).get(SendDocumentsViewModel::class.java)
        viewModelSendDocuments.getDocuments("ajuliethbvalencia@gmail.com")
        println("Imprimiendo la respuesta del get documents desde el ViewDocumentActivity")
        println(viewModelSendDocuments.getDocuments("ajuliethbvalencia@gmail.com"))

        textViewResponse.setText(viewModelSendDocuments.getDocuments("ajuliethbvalencia@gmail.com"))
        viewModelSendDocuments.getDocuments("ajuliethbvalencia@gmail.com")






        //Call Button "Regresar"
        buttonRegresar.setOnClickListener {
            val oppenMenu2 = Intent(this, Menu2Activity::class.java).apply {
                println("Se devolvio al menu2")
            }
            startActivity(oppenMenu2)
        }

        //Call ImageView imageHamburgerMenu
        imageBurgerMenu.setOnClickListener {

            // Set the data
            var rollButtonSendDocuments: Button? = null
            var rollButtonViewDocuments: Button? = null
            var rollButtonOficces: Button? = null
            var rollButtonModo: Button? = null
            var rollButtonLanguage: Button? = null


            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.activity_burger_menu, null)
            var relativeLayout = view.findViewById<ConstraintLayout>(R.id.popup_window_background)
            window.contentView = view
            window.showAsDropDown(imageBurgerMenu)
            view.setOnClickListener {
                Toast.makeText(this,"Item: ", Toast.LENGTH_SHORT).show()
                window.dismiss()
            }

            rollButtonSendDocuments = view.findViewById(R.id.button5)
            rollButtonViewDocuments = view.findViewById(R.id.button6)
            rollButtonOficces = view.findViewById(R.id.button7)
            rollButtonModo = view.findViewById(R.id.button8)
            rollButtonLanguage = view.findViewById(R.id.button9)

            rollButtonSendDocuments.setOnClickListener {
                window.dismiss()
                println("Enviar documentos")
                val oppenSendDocuments = Intent(this, SendDocuments2Activity::class.java).apply {
                    println("Entró a enviar documentos")
                }
                startActivity(oppenSendDocuments)
            }
            rollButtonViewDocuments.setOnClickListener {
                window.dismiss()
                println("Ver documentos")
                val oppenSeeDocuments = Intent(this, ViewDocuments2Activity::class.java).apply {
                    println("Entró a ver documentos")
                }
                startActivity(oppenSeeDocuments)
            }
            rollButtonOficces.setOnClickListener {
                window.dismiss()
                println("Oficinas")
                val oppenOffices = Intent(this, Oficces2Activity::class.java).apply {
                    println("Entró a ver oficinas")
                }
                startActivity(oppenOffices)
            }

            val preferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
            isDarkModeEnabled = preferences.getBoolean("dark_mode", false)
            AppCompatDelegate.setDefaultNightMode(if (isDarkModeEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)

            rollButtonModo.setOnClickListener {
                window.dismiss()
                println("Modo oscuro")

                isDarkModeEnabled = !isDarkModeEnabled

                if (isDarkModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )

                    allViewDocuments.setBackgroundColor("#3A275B".toColorInt())
                    buttonRegresar.setTextColor("#FFFFFF".toColorInt())
                    textDocumentos.setTextColor("#FFFFFF".toColorInt())

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageBurgerMenu.setImageDrawable(drawableMenuDark)

                    }

                } else {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    rollButtonModo.setText("Modo nocturno")
                    allViewDocuments.setBackgroundColor("#FFFFFF".toColorInt())
                    buttonRegresar.setTextColor("#000000".toColorInt())
                    textDocumentos.setTextColor("#000000".toColorInt())

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageBurgerMenu.setImageDrawable(drawableMenuLight)

                    }

                }
                val preferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
                preferences.edit().putBoolean("dark_mode", isDarkModeEnabled).apply()
            }

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

            // Get the selected language from preferences and set it in the config
            val config = Configuration()
            config.locale = Locale(sharedPreferences.getString("LANGUAGE", "en"))
            val resources = resources
            resources.updateConfiguration(config, resources.displayMetrics)

            // Listen for changes in the language preference
            sharedPreferences.registerOnSharedPreferenceChangeListener { _, key ->
                if (key == "LANGUAGE") {
                    config.locale = Locale(sharedPreferences.getString(key, "en"))
                    resources.updateConfiguration(config, resources.displayMetrics)
                    recreate()
                }
            }
            rollButtonLanguage.setOnClickListener {
                window.dismiss()

                val language = if (sharedPreferences.getString("LANGUAGE", "en") == "en") "es" else "en"
                sharedPreferences.edit().putString("LANGUAGE", language).apply()
            }
        }
    }

    private fun documentos(): MutableList<RecyclerModel> {
        var documentsModel : MutableList<RecyclerModel> = ArrayList()
        documentsModel.add(RecyclerModel("hoy","Ensayo1","And","Bed"))
        documentsModel.add(RecyclerModel("hoy","Ensayo1","And","Bed"))
        documentsModel.add(RecyclerModel("hoy","Ensayo1","And","Bed"))
        documentsModel.add(RecyclerModel("hoy","Ensayo1","And","Bed"))
        documentsModel.add(RecyclerModel("hoy","Ensayo1","And","Bed"))

        return documentsModel
    }

    override fun onStart() {
        super.onStart()
        val recyclerview: RecyclerView = findViewById(R.id.recyclerview)
        val adapter: RecyclerViewAdapter = RecyclerViewAdapter()

        //Configuration of adapter
        adapter.RecyclerViewAdapter(documentos(),this)

        //Configuration of recyclerView
        recyclerview.hasFixedSize()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }
}