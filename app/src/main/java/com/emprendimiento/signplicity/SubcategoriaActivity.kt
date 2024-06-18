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
                Subcategoria("A", R.drawable.dc_a),
                Subcategoria("B", R.drawable.dc_b),
                Subcategoria("C", R.drawable.dc_c),
                Subcategoria("D", R.drawable.dc_d),
                Subcategoria("E", R.drawable.dc_e),
                Subcategoria("F", R.drawable.dc_f),
                Subcategoria("G", R.drawable.dc_g),
                Subcategoria("H", R.drawable.dc_h),
                Subcategoria("I", R.drawable.dc_i),
                Subcategoria("J", R.drawable.dc_j),
                Subcategoria("K", R.drawable.dc_k),
                Subcategoria("L", R.drawable.dc_l),
                Subcategoria("M", R.drawable.dc_m),
                Subcategoria("N", R.drawable.dc_n),
                Subcategoria("O", R.drawable.dc_o),
                Subcategoria("P", R.drawable.dc_p),
                Subcategoria("Q", R.drawable.dc_q),
                Subcategoria("R", R.drawable.dc_r),
                Subcategoria("S", R.drawable.dc_s),
                Subcategoria("T", R.drawable.dc_t),
                Subcategoria("U", R.drawable.dc_u),
                Subcategoria("V", R.drawable.dc_v),
                Subcategoria("W", R.drawable.dc_w),
                Subcategoria("X", R.drawable.dc_x),
                Subcategoria("Y", R.drawable.dc_y),
                Subcategoria("Z", R.drawable.dc_z),

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


