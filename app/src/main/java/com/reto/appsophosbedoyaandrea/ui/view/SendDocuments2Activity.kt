package com.reto.appsophosbedoyaandrea.ui.view


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.camera.core.ImageCapture
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.reto.appsophosbedoyaandrea.R
import com.reto.appsophosbedoyaandrea.data.network.LoginListener
import com.reto.appsophosbedoyaandrea.ui.viewmodel.OficcesViewModel
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import com.reto.appsophosbedoyaandrea.data.model.RequestBodyModel
import com.reto.appsophosbedoyaandrea.ui.viewmodel.SendDocumentsViewModel
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class SendDocuments2Activity : AppCompatActivity(),
    LoginListener, AdapterView.OnItemSelectedListener {

    //For send document
    //private var  requestBodyModel = RequestBodyModel(null,null,null,null,null,null,null,null)
    var imageSelect = ""

    //For select take photo
    private val REQUEST_IMAGE_CAPTURE_CAMERA = 2
    val REQUEST_WRITE_EXTERNAL_STORAGE =1
    val REQUEST_TAKE_PHOTO = 1


    //For select photo of galery
    var pickedPhoto: Uri? = null
    var pickedBitmap : Bitmap? = null
    val REQUEST_IMAGE_CAPTURE = 1
    private val PICK_IMAGE_REQUEST = 1
    lateinit var currentPhotoPath: String
    val imageView = ImageCapture.Builder()
    private val REQUEST_GALLERY = 1
    private var returnedData: String? = null

    //For others activities or view or model
    private val oficcesViewModel = OficcesViewModel()

    var isDarkModeEnabled = false



    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_FILENAME = "com.example.myapp.prefs"


    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_documents2)

        //Call all components of Send Documents
        val allSendDocuments : ConstraintLayout = findViewById(R.id.allSendDocuments)
        val buttonRegresar: Button = findViewById(R.id.buttonRegresar)
        val imageBurgerMenu: ImageView = findViewById(R.id.imageBurgerMenu)
        val textSendDocuments: TextView = findViewById(R.id.textSendDocuments)
        val imageCamera: ImageView = findViewById(R.id.imageCamera)
        val imageBadge: ImageView = findViewById(R.id.imageBadge)
        val editTextNumber: EditText = findViewById(R.id.editTextNumber)
        val imageLine1: ImageView = findViewById(R.id.imageLine1)
        val editTextName: EditText = findViewById(R.id.editTextName)
        val imageLine2: ImageView = findViewById(R.id.imageLine2)
        val ediTextApellidos: EditText = findViewById(R.id.ediTextApellidos)
        val imageLine3: ImageView = findViewById(R.id.imageLine3)
        val editTextCorreo: EditText = findViewById(R.id.editTextCorreo)
        val imageLine4: ImageView = findViewById(R.id.imageLine4)
        val spinner: Spinner = findViewById(R.id.spinner_view)
        val rollButtonDocument: Button = findViewById(R.id.buttonDocument)
        val rollButtonSend: Button = findViewById(R.id.buttonSend)
        val spinner2: Spinner = findViewById(R.id.spinner_view2)
        val imageLine5: ImageView = findViewById(R.id.imageLine5)
        val editTextTipoAdjunto: EditText = findViewById(R.id.editTextTipoAdjunto)
        val imageLine6: ImageView = findViewById(R.id.imageLine6)



        val drawableMenuDark = ContextCompat.getDrawable(this, R.drawable.menu_dark)
        val drawableMenuLight = ContextCompat.getDrawable(this, R.drawable.hamburger_menu)
        val drawableArrowLefthDark = ContextCompat.getDrawable(this, R.drawable.arrow_lefth_dark)
        val drawableArrowLefthLight = ContextCompat.getDrawable(this, R.drawable.keyboard_backspace)
        val drawableLineDark = ContextCompat.getDrawable(this, R.drawable.line_dark)
        val drawableLineLight = ContextCompat.getDrawable(this, R.drawable.line_1)
        val drawableAddPhotoDark = ContextCompat.getDrawable(this, R.drawable.add_a_photo_dark)
        val drawableAddPhotoLight = ContextCompat.getDrawable(this, R.drawable.add_a_photo)
        val drawableBadgeDark = ContextCompat.getDrawable(this, R.drawable.badge_dark)
        val drawableBadgeLight= ContextCompat.getDrawable(this, R.drawable.badge)

        //Generate de number random for id that requestBody
        /*val random = Random()
        val randomInt = random.nextInt(100) + 1
        val randomString = randomInt.toString()*/



        //Call Button "Regresar"
        buttonRegresar.setOnClickListener {
            val oppenMenu2 = Intent(this, Menu2Activity::class.java).apply {
                println("Se devolvio al menu2")
            }
            startActivity(oppenMenu2)
        }

        //Call Button imageCamera
        imageCamera.setOnClickListener {
            /*val openPopUp1 = Intent(this, PopUp1Activity::class.java)
            openPopUp1.putExtra("darkstatusbar", false)
            startActivity(openPopUp1)*/

            // Set the data
            var rollButtonTakePhoto: Button? = null
            var rollButtonGalery: Button? = null


            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.activity_pop_up1, null)
            var relativeLayout = view.findViewById<ConstraintLayout>(R.id.popup_window_background)
            window.contentView = view
            window.showAsDropDown(imageCamera)

            val popup_window_title = view.findViewById<AppCompatTextView>(R.id.popup_window_title)
            val popup_window_background_container = view.findViewById<ConstraintLayout>(R.id.popup_window_background_container)
            val popup_window_view = view.findViewById<CardView>(R.id.popup_window_view)



            rollButtonTakePhoto = view.findViewById(R.id.popup_takephoto_button)
            rollButtonGalery = view.findViewById(R.id.popup_imagengalery_button)

            // Close the Popup Window when you press the button
            rollButtonTakePhoto?.setOnClickListener {
                window.dismiss()
                println("Tomar foto")
                //dispatchTakePictureIntent()
                //galleryAddPic()
                takePicture()
                galleryAddPic()
            }
            rollButtonGalery?.setOnClickListener {
                window.dismiss()
                println("Abrir galeria")
                selectImage()
                /*println("La imagen convertida en Base64 es:")
                println()
                println("Fin de la conversion")*/
            }

            relativeLayout.setOnClickListener{
                window.dismiss()
            }

            popup_window_view.setOnClickListener{
                window.dismiss()
            }

            popup_window_background_container.setOnClickListener{
                window.dismiss()
            }

            popup_window_title.setOnClickListener{
                window.dismiss()
            }
            view.setOnClickListener{
                window.dismiss()
            }
        }

        //Call Button Document
        rollButtonDocument.setOnClickListener {
            println("click en el boton documento, se abre la galeria")
            selectImage()

        }



        //Call object observe
        val viewModelSendDocuments = ViewModelProvider(this).get(SendDocumentsViewModel::class.java)
        //Call Button Send
        rollButtonSend.setOnClickListener {
            println("click en el boton enviar")

            editTextName
            ediTextApellidos
            editTextTipoAdjunto

            //viewModelSendDocuments.postDocument()
            val TipoId = spinner.selectedItem.toString()
            val number = editTextNumber.text.toString()
            val name = editTextName.text.toString()
            val lastName = ediTextApellidos.text.toString()
            val Ciudad = spinner2.selectedItem.toString()
            val correo = editTextCorreo.text.toString()
            val tipoAdjunto = editTextTipoAdjunto.text.toString()
            val imagenPost = retrieveData()
            println(TipoId)
            println(number)
            println(name)
            println(lastName)
            println(Ciudad)
            println(correo)
            println(tipoAdjunto)
            println(imagenPost)

            var palabra = "hello"

            var requestBodyModel = RequestBodyModel(TipoId,number,name,lastName,Ciudad,correo,tipoAdjunto,imagenPost)
            viewModelSendDocuments.postDocument(requestBodyModel)
            onMessage(viewModelSendDocuments.message2)
            println("Imprimiendo la respuesta del Post desde el SendActivity")
            println(viewModelSendDocuments.postDocument(requestBodyModel).toString())

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

            rollButtonSendDocuments = view.findViewById(R.id.button5)
            rollButtonViewDocuments = view.findViewById(R.id.button6)
            rollButtonOficces = view.findViewById(R.id.button7)
            rollButtonModo = view.findViewById(R.id.button8)
            rollButtonLanguage = view.findViewById(R.id.button9)

            relativeLayout.setOnClickListener{
                window.dismiss()
            }
            view.setOnClickListener{
                window.dismiss()
            }

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

                    allSendDocuments.setBackgroundColor("#3A275B".toColorInt())
                    buttonRegresar.setTextColor("#FFFFFF".toColorInt())
                    editTextNumber.setTextColor("#FFFFFF".toColorInt())
                    editTextName.setTextColor("#FFFFFF".toColorInt())
                    ediTextApellidos.setTextColor("#FFFFFF".toColorInt())
                    editTextCorreo.setTextColor("#FFFFFF".toColorInt())
                    editTextTipoAdjunto.setTextColor("#FFFFFF".toColorInt())
                    textSendDocuments.setTextColor("#FFFFFF".toColorInt())

                    editTextNumber.setHintTextColor("#FFFFFF".toColorInt())
                    editTextName.setHintTextColor("#FFFFFF".toColorInt())
                    ediTextApellidos.setHintTextColor("#FFFFFF".toColorInt())
                    editTextCorreo.setHintTextColor("#FFFFFF".toColorInt())
                    editTextTipoAdjunto.setHintTextColor("#FFFFFF".toColorInt())

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageBurgerMenu.setImageDrawable(drawableMenuDark)
                        imageCamera.setImageDrawable(drawableAddPhotoDark)
                        imageBadge.setImageDrawable(drawableBadgeDark)
                        imageLine1.setImageDrawable(drawableLineDark)
                        imageLine2.setImageDrawable(drawableLineDark)
                        imageLine3.setImageDrawable(drawableLineDark)
                        imageLine4.setImageDrawable(drawableLineDark)
                        imageLine5.setImageDrawable(drawableLineDark)
                        imageLine5.setImageDrawable(drawableLineDark)
                        imageLine6.setImageDrawable(drawableLineDark)

                    }

                } else {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )

                    allSendDocuments.setBackgroundColor("#FFFFFF".toColorInt())
                    buttonRegresar.setTextColor("#000000".toColorInt())
                    editTextNumber.setTextColor("#000000".toColorInt())
                    editTextName.setTextColor("#000000".toColorInt())
                    ediTextApellidos.setTextColor("#000000".toColorInt())
                    editTextCorreo.setTextColor("#000000".toColorInt())
                    editTextTipoAdjunto.setTextColor("#000000".toColorInt())
                    textSendDocuments.setTextColor("#000000".toColorInt())

                    editTextNumber.setHintTextColor("#000000".toColorInt())
                    editTextName.setHintTextColor("#000000".toColorInt())
                    ediTextApellidos.setHintTextColor("#000000".toColorInt())
                    editTextCorreo.setHintTextColor("#000000".toColorInt())
                    editTextTipoAdjunto.setHintTextColor("#000000".toColorInt())


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        imageBurgerMenu.setImageDrawable(drawableMenuLight)
                        imageCamera.setImageDrawable(drawableAddPhotoLight)
                        imageBadge.setImageDrawable(drawableBadgeLight)
                        imageLine1.setImageDrawable(drawableLineLight)
                        imageLine2.setImageDrawable(drawableLineLight)
                        imageLine3.setImageDrawable(drawableLineLight)
                        imageLine4.setImageDrawable(drawableLineLight)
                        imageLine5.setImageDrawable(drawableLineLight)
                        imageLine5.setImageDrawable(drawableLineLight)
                        imageLine6.setImageDrawable(drawableLineLight)

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

        //Call spinner type document
        spinner.onItemSelectedListener = this
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.type_documents_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        //Call object observe
        val viewModel = ViewModelProvider(this).get(OficcesViewModel::class.java)


        //Call all cities
        viewModel.getAllOficces()
        var cities = viewModel.arrayCities2
        println(cities)



        println("Todas las ciudades son: ")
        println(cities)


        //Call spinner city
        spinner2.onItemSelectedListener = this
        spinner2.setPrompt("Cuidad:")
        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            cities
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
        }

    }



    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

    //__________Fuction of take photo
    fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(this,"com.reto.appsophosbedoyaandrea", photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                this.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE_CAMERA)
            }
        }

    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        /*// Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)

        return image*/
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }



    //_____________Function of oppend the galery
    fun pickedPhoto (/*view: View*/){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }else {
            val galleryIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntext,2)
        }

    }

    fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }



    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null){
            pickedPhoto = data?.data
            if (Build.VERSION.SDK_INT >= 28){
                val source = ImageDecoder.createSource(this.contentResolver, pickedPhoto!!)
                pickedBitmap = ImageDecoder.decodeBitmap(source)
                //imageView.setImagetBitmap(pickedBitmap)

            }else{
                pickedBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,pickedPhoto)
                //imageView.setImagetBitmap(pickedBitmap)

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            val imageStream = contentResolver.openInputStream(imageUri!!)

            /*val bitmap = Glide.
                .with(context)
                .asBitmap()
                .load(imageStream)
                .submit(300, 300)
                .get()*/

            val selectedImage = BitmapFactory.decodeStream(imageStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val imageBytes = byteArrayOutputStream.toByteArray()
            val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)

            storeData(imageString)

        }

    }
    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    private fun storeData(data: String?) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()
        editor.putString("RETURNED_DATA", data)
        editor.apply()
    }

    private fun retrieveData(): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        return prefs.getString("RETURNED_DATA", "")
    }



    //______________Function for message
    override fun onMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



}






