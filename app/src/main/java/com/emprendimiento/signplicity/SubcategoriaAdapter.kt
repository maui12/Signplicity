package com.emprendimiento.signplicity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable
import java.util.*

class SubcategoriaAdapter(private val subcategorias: List<Subcategoria>) : RecyclerView.Adapter<SubcategoriaAdapter.SubcategoriaViewHolder>(), Filterable {

    private var subcategoriasFiltradas: List<Subcategoria> = subcategorias

    class SubcategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.textViewSubcategoria)
        val imagenImageView: ImageView = view.findViewById(R.id.imageViewSubcategoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subcategoria, parent, false)
        return SubcategoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoriaViewHolder, position: Int) {
        val subcategoria = subcategoriasFiltradas[position]
        holder.nombreTextView.text = subcategoria.nombre
        holder.imagenImageView.setImageResource(subcategoria.imagen)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SubcategoriaDetalleActivity::class.java).apply {
                putExtra("nombre", subcategoria.nombre)
                putExtra("imagen", subcategoria.imagen)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = subcategoriasFiltradas.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault()) ?: ""
                val filteredList = if (query.isEmpty()) {
                    subcategorias
                } else {
                    subcategorias.filter {
                        it.nombre.lowercase(Locale.getDefault()).contains(query)
                    }
                }
                return FilterResults().apply {
                    values = filteredList
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                subcategoriasFiltradas = results?.values as List<Subcategoria>
                notifyDataSetChanged()
            }
        }
    }
}