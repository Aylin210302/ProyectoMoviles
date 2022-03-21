package com.proyectoMoviles.ui.carro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyectoMoviles.R
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

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}