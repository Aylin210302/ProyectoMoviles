package com.proyectoMoviles.ui.carro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyectoMoviles.R
import com.proyectoMoviles.adapter.CarroAdapter
import com.proyectoMoviles.databinding.FragmentCarroBinding
import com.proyectoMoviles.viewmodel.CarroViewModel

class CarroFragment : Fragment() {
    private lateinit var carroViewModel: CarroViewModel
    private var _binding: FragmentCarroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carroViewModel = ViewModelProvider(this).get(CarroViewModel::class.java)

        _binding = FragmentCarroBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_carro_to_addCarroFragment)
        }

        val carroAdapter = CarroAdapter()
        val reciclador = binding.reciclador

        reciclador.adapter = carroAdapter
        reciclador.layoutManager = LinearLayoutManager(requireContext())

        carroViewModel.getAllData.observe(viewLifecycleOwner) { carros ->
            carroAdapter.setData(carros)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}