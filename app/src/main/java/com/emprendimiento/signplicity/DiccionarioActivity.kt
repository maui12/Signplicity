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
            Categoria("Abecedario", R.drawable.dc_abecedario_logo),
            Categoria("Colores", R.drawable.dc_colores_logo),
            Categoria("Números", R.drawable.dc_numeros_logo),
            Categoria("Saludos", R.drawable.dc_saludos_logo),
            Categoria("Acciones", R.drawable.dc_acciones_logo),
            Categoria("Días", R.drawable.dc_calendario_logo),
            Categoria("Animales", R.drawable.dc_animales_logo),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CategoriaAdapter(categorias)
    }
}