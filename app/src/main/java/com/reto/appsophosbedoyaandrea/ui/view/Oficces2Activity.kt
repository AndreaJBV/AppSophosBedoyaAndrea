package com.reto.appsophosbedoyaandrea.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.reto.appsophosbedoyaandrea.R
import com.reto.appsophosbedoyaandrea.ui.viewmodel.OficcesViewModel
import java.util.*

class Oficces2Activity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    private lateinit var map: GoogleMap

    private val menu2Activity = Menu2Activity()

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    var isDarkModeEnabled = false
    var isChangeLanguage = false


    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_FILENAME = "com.example.myapp.prefs"


    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oficces2)

        var isDarkModeEnabled = menu2Activity.isDarkModeEnabled
        println("Ovservando isDarkModeEnabled desde el OfficesActivity")
        println(isDarkModeEnabled)



        //Call all components of Oficces
        val allOficces : ConstraintLayout = findViewById(R.id.allOficces)
        val buttonRegresar2: Button = findViewById(R.id.buttonRegresar2)
        val imageBurgerMenu: ImageView = findViewById(R.id.imageBurgerMenu)
        val textOficces: TextView = findViewById(R.id.textOficces)

        val drawableMenuDark = ContextCompat.getDrawable(this, R.drawable.menu_dark)
        val drawableMenuLight = ContextCompat.getDrawable(this, R.drawable.hamburger_menu)
        val drawableArrowLefthDark = ContextCompat.getDrawable(this, R.drawable.arrow_lefth_dark)
        val drawableArrowLefthLight = ContextCompat.getDrawable(this, R.drawable.keyboard_backspace)

        //Create maps
        createFragment()

        //Call object observe
        val viewModel = ViewModelProvider(this).get(OficcesViewModel::class.java)
        val allCities = viewModel.getAllOficces()
        val allLongitud = viewModel.getAllLongitud()
        val allLatitud = viewModel.getAllLatitude()
        val allNameOficces = viewModel.getAllNameOficce()
        /*val allCitiesCity = viewModel.getOficceByCity("Medellín")
        val allLongitudCity = viewModel.getAllLongitudCity("Medellín")
        val allLatitudCity = viewModel.getAllLatitudeCity("Medellín")
        val allNameOficcesCity = viewModel.getAllNameOficceCity("Medellín")*/

        fun createCities() : String{
            val city1 = allCities[0]
            return city1
        }


        //Create office1
        val city1 = allCities[0]
        val length1 = allLongitud[0].toDouble()
        val latitude1 = allLatitud[0].toDouble()
        val nameOficce1 = allNameOficces[0]

        //Create office2
        val city2 = allCities[1]
        val length2 = allLongitud[1].toDouble()
        val latitude2 = allLatitud[1].toDouble()
        val nameOficce2 = allNameOficces[1]

        //Create office3
        val city3 = allCities[2]
        val length3 = allLongitud[2].toDouble()
        val latitude3 = allLatitud[2].toDouble()
        val nameOficce3 = allNameOficces[2]

        //Create office4
        val city4 = allCities[3]
        val length4 = allLongitud[3].toDouble()
        val latitude4 = allLatitud[3].toDouble()
        val nameOficce4 = allNameOficces[3]

        //Create office5
        val city5 = allCities[4]
        val length5 = allLongitud[4].toDouble()
        val latitude5 = allLatitud[4].toDouble()
        val nameOficce5 = allNameOficces[4]

        //Create office6
        val city6 = allCities[5]
        val length6 = allLongitud[5].toDouble()
        val latitude6 = allLatitud[5].toDouble()
        val nameOficce6 = allNameOficces[5]

        //Create office7
        val city7 = allCities[6]
        val length7 = allLongitud[6].toDouble()
        val latitude7 = allLatitud[6].toDouble()
        val nameOficce7 = allNameOficces[6]

        //Create office8
        val city8 = allCities[7]
        val length8 = allLongitud[7].toDouble()
        val latitude8 = allLatitud[7].toDouble()
        val nameOficce8 = allNameOficces[7]

        val points = listOf(
            LatLng(latitude1, length1),
            LatLng(latitude2, length2),
            LatLng(latitude3, length3),
            LatLng(latitude4, length4),
            LatLng(latitude5, length5),
            LatLng(latitude6, length6),
            LatLng(latitude7, length7),
            LatLng(latitude8, length8)
        )

        val nameOficces = listOf(
            nameOficce1,
            nameOficce2,
            nameOficce3,
            nameOficce4,
            nameOficce5,
            nameOficce6,
            nameOficce7,
            nameOficce8
        )

        /*createMarker(length1,latitude1, nameOficce1)
        createMarker(length2,latitude2, nameOficce2)
        createMarker(length3,latitude3, nameOficce3)
        createMarker(length4,latitude4, nameOficce4)
        createMarker(length5,latitude5, nameOficce5)
        createMarker(length6,latitude6, nameOficce6)
        createMarker(length7,latitude7, nameOficce7)
        createMarker(length8,latitude8, nameOficce8)*/



        //Call Button "Regresar"
        buttonRegresar2.setOnClickListener {
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
                    //rollButtonModo.text ="Modo día"
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                    allOficces.setBackgroundColor("#3A275B".toColorInt())
                    textOficces.setTextColor("#FFFFFF".toColorInt())
                    buttonRegresar2.setTextColor("#FFFFFF".toColorInt())


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageBurgerMenu.setImageDrawable(drawableMenuDark)

                    }
                } else {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    allOficces.setBackgroundColor("#FFFFFF".toColorInt())
                    textOficces.setTextColor("#7800FD".toColorInt())
                    buttonRegresar2.setTextColor("#7800FD".toColorInt())

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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        recreate()
                    }
                }
            }

            rollButtonLanguage.setOnClickListener {
                window.dismiss()
                println("Idioma ingles")
                val language = if (sharedPreferences.getString("LANGUAGE", "en") == "en") "es" else "en"
                sharedPreferences.edit().putString("LANGUAGE", language).apply()
            }
        }
    }


    private fun createFragment(){
        val mapFragment : SupportMapFragment? = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val length1 = -70.64851440000001
        val latitude1 = 33.440570099999995
        val nameOficce1 = "Agustinas 833 – Piso 10"
        val length2 = -73.98382049999998
        val latitude2 = 40.7504055
        val nameOficce2 = "404 Fifth Avenue Tenant LLC"
        val length3 = -74.044757
        val latitude3 = 4.6796679999999995
        val nameOficce3 =  "Edificio Davivienda - Piso 4"
        val length4 = -75.58021739999998
        val latitude4 = 6.218229100000025
        val nameOficce4 = "CEOH - 107"
        val length5 = -74.06940610000004
        val latitude5 = 4.612525050021438
        val nameOficce5 = "Edificio Tequendama - Piso 30"
        val length6 = -79.5875616999999
        val latitude6 = 9.005743200000007
        val nameOficce6 = "Ciudad del Saber"
        val length7 = -75.57474939999997
        val latitude7 = 6.224464299999994
        val nameOficce7 = "Ciudad del Rio - 1009"
        val length8 = -99.16522090000001
        val latitude8 = 19.42736700000002
        val nameOficce8 =  "Torre Reforma Latino - Piso 41"

        createMarker(length1,latitude1,nameOficce1)
        createMarker(length2,latitude2,nameOficce2)
        createMarker(length3,latitude3,nameOficce3)
        createMarker(length4,latitude4,nameOficce4)
        createMarker(length5,latitude5,nameOficce5)
        createMarker(length6,latitude6,nameOficce6)
        createMarker(length7,latitude7,nameOficce7)
        createMarker(length8,latitude8,nameOficce8)

        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        enableLocation()


    }

    private fun createMarker(length: Double,latitude:Double,nameOficce: String){
        val coordinates = LatLng(latitude,length)
        val marker : MarkerOptions = MarkerOptions().position(coordinates).title(nameOficce)
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates,12f),4000,null
        )
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation(){
        if(!::map.isInitialized) return
        if (isLocationPermissionGranted()){
            map.isMyLocationEnabled = true
        }else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this,"Por favor aceptar los permisos",Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled = true
            }else{
                Toast.makeText(this,"Por favor aceptar los permisos, ve a ajustes",Toast.LENGTH_SHORT).show()
            }else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if(!::map.isInitialized) return
        if(!isLocationPermissionGranted()){
            map.isMyLocationEnabled = false
            Toast.makeText(this,"Por favor aceptar los permisos, ve a ajustes",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this,"Buscando ubicación actual",Toast.LENGTH_SHORT).show()

        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this,"Estas en ${p0.latitude}, ${p0.longitude}",Toast.LENGTH_SHORT).show()

    }


}