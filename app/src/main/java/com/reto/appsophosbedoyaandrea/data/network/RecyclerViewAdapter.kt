package com.reto.appsophosbedoyaandrea.data.network

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reto.appsophosbedoyaandrea.R
import com.reto.appsophosbedoyaandrea.data.model.RecyclerModel


class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var documentos : MutableList<RecyclerModel> = ArrayList()
    lateinit var context: Context
    fun RecyclerViewAdapter(documento: MutableList<RecyclerModel>, context: Context){
        this.documentos = documentos
        this.context = context
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val date: TextView
        val typeAdjun: TextView
        val name: TextView
        val lastName: TextView
        init {
            date = view.findViewById(R.id.textViewDate)
            typeAdjun = view.findViewById(R.id.textViewTypeAdjunt)
            name = view.findViewById(R.id.textViewName)
            lastName = view.findViewById(R.id.textViewLastName)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_documents,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date.text = documentos[position].fecha
        holder.typeAdjun.text = documentos[position].tipoAdjunto
        holder.name.text = documentos[position].nombre
        holder.lastName.text = documentos[position].apellido


    }

    override fun getItemCount() = documentos.size
}