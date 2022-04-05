package com.proyectoMoviles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyectoMoviles.databinding.CarroFilaBinding
import com.proyectoMoviles.model.Carro
import com.proyectoMoviles.ui.carro.CarroFragmentDirections

class CarroAdapter : RecyclerView.Adapter<CarroAdapter.CarroViewHolder>() {
    private var listaCarros = emptyList<Carro>()

    fun setData(carros: List<Carro>) {
        this.listaCarros = carros
        notifyDataSetChanged()
    }

    inner class CarroViewHolder(private val itemBinding: CarroFilaBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(carro: Carro) {
            itemBinding.tvModelo.text = carro.modelo
            itemBinding.tvAnnio.text = carro.año
            itemBinding.tvMarca.text = carro.marca
            itemBinding.tvMotor.text = carro.motor
            itemBinding.tvPrecio.text = carro.precio.toString()
            Glide.with(itemBinding.root.context)
                .load(carro.rutaImagen)
                .circleCrop()
                .into(itemBinding.imagen)
            itemBinding.vistaFila.setOnClickListener {
                val accion = CarroFragmentDirections
                    .actionNavCarroToNavUpdateCarro(carro)
                itemView.findNavController().navigate(accion)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarroViewHolder {
        val itemBinding = CarroFilaBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return CarroViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CarroViewHolder, position: Int) {
        val carro = listaCarros[position]
        holder.bind(carro)
    }

    override fun getItemCount(): Int {
        return listaCarros.size
    }

}