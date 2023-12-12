package com.example.pfinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pfinal.R
import com.example.pfinal.model.Empleado
import java.text.SimpleDateFormat
import java.util.Locale

class EmpleadoAdapter(private val empleado: List<Empleado>) : RecyclerView.Adapter<EmpleadoAdapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre : TextView = itemView.findViewById(R.id.txtN)
        val pais : TextView = itemView.findViewById(R.id.txtPais)
        val fechaI : TextView = itemView.findViewById(R.id.txtFechIn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val empleado = empleado[position]
        holder.nombre.text = empleado.nombre
        holder.pais.text = empleado.pais
        holder.fechaI.text = empleado.fechaI



    }

    override fun getItemCount(): Int {
        return empleado.size
    }

}