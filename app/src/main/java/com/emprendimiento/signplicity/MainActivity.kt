package com.emprendimiento.signplicity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//Menu principal
class MainActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val textView = findViewById<TextView>(R.id.name)

        val auth = Firebase.auth
        val user = auth.currentUser

        if (user != null) {
            val userName = user.displayName
            textView.text = userName
        } else {
            // Handle the case where the user is not signed in
        }
        // Inside onCreate() method



    //boton diccionario
        var buttonDiccionario:Button = findViewById(R.id.buttonDiccionario)
        buttonDiccionario.setOnClickListener{pushDiccionario()}

        var buttonPerfil = findViewById<Button>(R.id.buttonPerfil)
        buttonPerfil.setOnClickListener{pushPerfil()}

        var buttonJuegos = findViewById<Button>(R.id.buttonJuegos)
        buttonJuegos.setOnClickListener{pushJuegos()}

        var buttonNoticias = findViewById<Button>(R.id.buttonNoticias)
        buttonNoticias.setOnClickListener{pushNoticias()}


    }

    private fun pushNoticias() {
        startActivity(Intent(this,SocialActivity::class.java))
    }

    private fun pushJuegos() {
        startActivity(Intent(this,QuizzesActivity::class.java))
    }

    private fun pushPerfil() {
        startActivity(Intent(this,ProfileDisplayActivity::class.java))
    }


    //funcionalidad boton diccionario
    private fun pushDiccionario() {
        startActivity(Intent(this,DiccionarioActivity::class.java))
    }
}