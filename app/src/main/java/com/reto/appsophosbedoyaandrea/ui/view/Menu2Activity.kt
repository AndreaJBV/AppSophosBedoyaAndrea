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
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import com.reto.appsophosbedoyaandrea.R
import java.util.Locale
import kotlin.math.absoluteValue


class Menu2Activity : AppCompatActivity() {
    var isDarkModeEnabled = false
    var isChangeLanguage = false


    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_FILENAME = "com.example.myapp.prefs"


    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu2)

        //Call all components of Menu2
        val rollUserName: TextView = findViewById(R.id.textNameMenu)
        val allMenu2: ConstraintLayout = findViewById(R.id.all_Menu2)
        val imageHamburgerMenu: ImageView = findViewById(R.id.imageHamburgerMenu)
        val allButtonSendDocuments : LinearLayout = findViewById(R.id.allButtonSendDocuments)
        val imageAllDescription: ImageView = findViewById(R.id.imageAllDescription)
        val textSendDocuments: TextView = findViewById(R.id.textSendDocuments)
        val allButtonIntoPink: LinearLayout = findViewById(R.id.allButtonIntoPink)
        val buttonIntoPink: Button = findViewById(R.id.buttonIntoPink)
        val allButtonViewDocuments: LinearLayout = findViewById(R.id.allButtonViewDocuments)
        val imagePlagiarism: ImageView = findViewById(R.id.imagePlagiarism)
        val texViewDocuments : TextView = findViewById(R.id.texViewDocuments)
        val allButtonIntoBlue : LinearLayout = findViewById(R.id.allButtonIntoBlue)
        val buttonIntoBlue: Button = findViewById(R.id.buttonIntoBlue)
        val allButtonOficces :LinearLayout = findViewById(R.id.allButtonOficces)
        val imageLocationOn : ImageView = findViewById(R.id.imageLocationOn)
        val textOficces: TextView = findViewById(R.id.textOficces)
        val allButtonIntoGreen: LinearLayout = findViewById(R.id.allButtonIntoGreen)
        val buttonIntoGreen: Button = findViewById(R.id.buttonIntoGreen)

        val drawableMenuDark = ContextCompat.getDrawable(this, R.drawable.menu_dark)
        val drawableMenuLight = ContextCompat.getDrawable(this, R.drawable.hamburger_menu)
        val drawableDescriptionDark = ContextCompat.getDrawable(this, R.drawable.description_dark)
        val drawableDescriptionLight = ContextCompat.getDrawable(this, R.drawable.description)
        val drawableArrowRightDark = ContextCompat.getDrawable(this, R.drawable.arrow_right_alt_dark)
        val drawableArrowRightPink = ContextCompat.getDrawable(this, R.drawable.arrow_right_alt_pink)
        val drawableArrowRightBlue = ContextCompat.getDrawable(this, R.drawable.arrow_right_alt_blue)
        val drawableArrowRightGreen = ContextCompat.getDrawable(this, R.drawable.arrow_right_alt_green)
        val drawablePlagiarismDark = ContextCompat.getDrawable(this, R.drawable.plagiarism_dark)
        val drawablePlagiarismLight = ContextCompat.getDrawable(this, R.drawable.plagiarism)
        val drawableLocationOnDark = ContextCompat.getDrawable(this, R.drawable.location_on_dark)
        val drawableLocationOnLight = ContextCompat.getDrawable(this, R.drawable.location_on)
        val drawableAllBtnSendDocumentsPink = ContextCompat.getDrawable(this, R.drawable.bton_all_button_send_documents)
        val drawableButtonSendDocumentsPink = ContextCompat.getDrawable(this, R.drawable.btn_button_into_pink_dark)
        val drawableAllBtnViewDocumentsBlue = ContextCompat.getDrawable(this, R.drawable.bton_all_button_view_documents)
        val drawableButtonViewDocumentsBlue = ContextCompat.getDrawable(this, R.drawable.btn_button_into_blue_dark)
        val drawableAllBtnOficcesGreen = ContextCompat.getDrawable(this, R.drawable.bton_all_button_oficces)
        val drawableButtonOficcesGreen = ContextCompat.getDrawable(this, R.drawable.btn_button_into_green_dark)

        val drawableAllBtnSendDocumentsPinkLight = ContextCompat.getDrawable(this, R.drawable.btn_ingresar_pink)
        val drawableButtonSendDocumentsPinkLight = ContextCompat.getDrawable(this, R.drawable.btn_ingresar_pink)
        val drawableAllBtnViewDocumentsBlueLight = ContextCompat.getDrawable(this, R.drawable.btn_ingresar_blue)
        val drawableButtonViewDocumentsBlueLight = ContextCompat.getDrawable(this, R.drawable.btn_ingresar_blue)
        val drawableAllBtnOficcesGreenLight = ContextCompat.getDrawable(this, R.drawable.btn_ingresar_green)
        val drawableButtonOficcesGreenLight = ContextCompat.getDrawable(this, R.drawable.btn_ingresar_green)


        //Call LoginActivity with its datas
        val name = intent.getStringExtra("name")

        //Call user name
        rollUserName.text = name

        //Call Button "IngresarPink"
        buttonIntoPink.setOnClickListener {
            val oppenSendDocuments = Intent(this, SendDocuments2Activity::class.java).apply {
                println("Entró a enviar documentos")
            }
            startActivity(oppenSendDocuments)
        }

        //Call Button "IngresarBlue"
        buttonIntoBlue.setOnClickListener {
            val oppenSeeDocuments = Intent(this, ViewDocuments2Activity::class.java).apply {
                println("Entró a ver documentos")
            }
            startActivity(oppenSeeDocuments)
        }

        //Call Button "IngresarGreen"
        buttonIntoGreen.setOnClickListener {
            val oppenOffices = Intent(this, Oficces2Activity::class.java).apply {
                println("Entró a ver oficinas")
            }
            startActivity(oppenOffices)
        }

        //Call ImageView imageHamburgerMenu

        imageHamburgerMenu.setOnClickListener {

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
            window.showAsDropDown(imageHamburgerMenu)
            view.setOnClickListener {
                Toast.makeText(this, "Item: ", Toast.LENGTH_SHORT).show()
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
                    //AppCompatDelegate.MODE_NIGHT_YES
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                    allMenu2.setBackgroundColor("#3A275B".toColorInt())
                    rollButtonModo.text = "Modo día"
                    imageHamburgerMenu.setImageDrawable(drawableMenuDark)
                    imageAllDescription.setImageDrawable(drawableDescriptionDark)
                    imagePlagiarism.setImageDrawable(drawablePlagiarismDark)
                    imageLocationOn.setImageDrawable(drawableLocationOnDark)
                    rollUserName.setTextColor("#FFFFFF".toColorInt())
                    textSendDocuments.setTextColor("#FFFFFF".toColorInt())
                    buttonIntoPink.setTextColor("#FFFFFF".toColorInt())
                    texViewDocuments.setTextColor("#FFFFFF".toColorInt())
                    buttonIntoBlue.setTextColor("#FFFFFF".toColorInt())
                    textOficces.setTextColor("#FFFFFF".toColorInt())
                    buttonIntoGreen.setTextColor("#FFFFFF".toColorInt())

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        allButtonSendDocuments.background = drawableAllBtnSendDocumentsPink
                        allButtonIntoPink.background = drawableButtonSendDocumentsPink
                        allButtonViewDocuments.background = drawableAllBtnViewDocumentsBlue
                        allButtonIntoBlue.background = drawableButtonViewDocumentsBlue
                        allButtonOficces.background = drawableAllBtnOficcesGreen
                        allButtonIntoGreen.background = drawableButtonOficcesGreen

                    }

                } else {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    //AppCompatDelegate.MODE_NIGHT_NO
                    rollButtonModo.text = "Modo nocturno"
                    allMenu2.setBackgroundColor("#FFFFFF".toColorInt())
                    imageHamburgerMenu.setImageDrawable(drawableMenuLight)
                    imageAllDescription.setImageDrawable(drawableDescriptionLight)
                    imagePlagiarism.setImageDrawable(drawablePlagiarismLight)
                    imageLocationOn.setImageDrawable(drawableLocationOnLight)
                    rollUserName.setTextColor("#7800FD".toColorInt())
                    textSendDocuments.setTextColor("#FE1191".toColorInt())
                    buttonIntoPink.setTextColor("#FE1191".toColorInt())
                    texViewDocuments.setTextColor("#4500FF".toColorInt())
                    buttonIntoBlue.setTextColor("#4500FF".toColorInt())
                    textOficces.setTextColor("#05667C".toColorInt())
                    buttonIntoGreen.setTextColor("#05667C".toColorInt())


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        allButtonSendDocuments.background = drawableAllBtnSendDocumentsPinkLight
                        allButtonIntoPink.background = drawableButtonSendDocumentsPinkLight
                        allButtonViewDocuments.background = drawableAllBtnViewDocumentsBlueLight
                        allButtonIntoBlue.background = drawableButtonViewDocumentsBlueLight
                        allButtonOficces.background = drawableAllBtnOficcesGreenLight
                        allButtonIntoGreen.background = drawableButtonOficcesGreenLight
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








}