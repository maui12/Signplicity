package com.emprendimiento.signplicity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiccionarioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diccionario)

        val categorias = listOf(
            Categoria("Abecedario", R.drawable.signplicity_logo),
            Categoria("Colores", R.drawable.google_logo),
            Categoria("Números", R.drawable.profile_default_pic),
            Categoria("Saludos", R.drawable.ic_launcher_foreground),
            Categoria("Saludos", R.drawable.ic_launcher_foreground),
            Categoria("Saludos", R.drawable.ic_launcher_foreground),
            Categoria("Saludos", R.drawable.ic_launcher_foreground),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CategoriaAdapter(categorias)
    }
}