package com.proyectoMoviles.ui.carro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyectoMoviles.R
import com.proyectoMoviles.databinding.FragmentAddCarroBinding
import com.proyectoMoviles.model.Carro
import com.proyectoMoviles.viewmodel.CarroViewModel

class AddCarroFragment : Fragment() {
    private lateinit var carroViewModel: CarroViewModel
    private var _binding: FragmentAddCarroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carroViewModel = ViewModelProvider(this).get(CarroViewModel::class.java)

        _binding = FragmentAddCarroBinding.inflate(inflater, container, false)

        binding.btAddCarro.setOnClickListener {
            agregarCarro()
        }

        return binding.root
    }

    private fun agregarCarro() {
        val modelo = binding.etModelo.text.toString()
        if (modelo.isNotEmpty()) {
            val año = binding.etAnnio.text.toString()
            val marca = binding.etMarca.text.toString()
            val motor = binding.etMotor.text.toString()
            val precio = binding.etPrecio.text.toString().toDouble()
            val cantidadAsientos = binding.etCantidadAsientos.text.toString().toInt()
            val cajaCambio = binding.etCaja.text.toString()
            val correo = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val web = binding.etWeb.text.toString()
            val carro = Carro(0,modelo,año,marca,motor,precio,cantidadAsientos,cajaCambio,"",correo,telefono,web)
            carroViewModel.addCarro(carro)
            Toast.makeText(requireContext(),
                getString(R.string.msg_carro_add),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addCarroFragment_to_nav_carro)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}