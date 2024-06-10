package com.emprendimiento.signplicity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


data class Categoria(val nombre: String, val imagen: Int)

class CategoriaAdapter(private val categorias: List<Categoria>) : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView = view.findViewById(R.id.textView)
        val imagenImageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categoria, parent, false)
        return CategoriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.nombreTextView.text = categoria.nombre
        holder.imagenImageView.setImageResource(categoria.imagen)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SubcategoriaActivity::class.java)
            // Puedes pasar datos adicionales sobre la categoría seleccionada si es necesario
            intent.putExtra("categoriaNombre", categoria.nombre)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = categorias.size
}