package com.emprendimiento.signplicity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubcategoriaDetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategoria_detalle)

        val nombre = intent.getStringExtra("nombre")
        val imagenResId = intent.getIntExtra("imagen", 0)

        val nombreTextView: TextView = findViewById(R.id.textViewNombreDetalle)
        val imagenImageView: ImageView = findViewById(R.id.imageViewImagenDetalle)

        nombreTextView.text = nombre
        imagenImageView.setImageResource(imagenResId)
    }
}
