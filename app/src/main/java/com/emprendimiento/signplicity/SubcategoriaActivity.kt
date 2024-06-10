package com.emprendimiento.signplicity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class SubcategoriaActivity : AppCompatActivity() {

    private lateinit var subcategoriaAdapter: SubcategoriaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategoria)

        val categoriaNombre = intent.getStringExtra("categoriaNombre")

        val subcategorias = when (categoriaNombre) {
            "Abecedario" -> listOf(
                Subcategoria("A", R.drawable.profile_icon),
                Subcategoria("B", R.drawable.dictionary_icon),
                Subcategoria("C", R.drawable.dictionary_icon),
                Subcategoria("D", R.drawable.dictionary_icon),
                Subcategoria("E", R.drawable.dictionary_icon),
                Subcategoria("F", R.drawable.dictionary_icon),
                Subcategoria("G", R.drawable.dictionary_icon),
                Subcategoria("H", R.drawable.dictionary_icon),
                Subcategoria("I", R.drawable.dictionary_icon),

                // Agrega más subcategorías según sea necesario
            )
            "Colores" -> listOf(
                Subcategoria("Rojo", R.drawable.google_logo),
                Subcategoria("Azul", R.drawable.signplicity_logo),
                Subcategoria("Amarillo", R.drawable.signplicity_logo),
                Subcategoria("Verde", R.drawable.signplicity_logo),
                Subcategoria("Naranja", R.drawable.signplicity_logo),
                Subcategoria("Rosado", R.drawable.signplicity_logo),
                Subcategoria("Morado", R.drawable.signplicity_logo),
                // Agrega más subcategorías según sea necesario
            )
            else -> listOf()
        }

        subcategoriaAdapter = SubcategoriaAdapter(subcategorias)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewSubcategorias)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = subcategoriaAdapter

        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                subcategoriaAdapter.filter.filter(newText)
                return false
            }
        })
    }
}


