package com.proyectoMoviles.ui.carro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.proyectoMoviles.databinding.FragmentCarroBinding
import com.proyectoMoviles.viewmodel.CarroViewModel

class CarroFragment : Fragment() {

    private var _binding: FragmentCarroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val carroViewModel =
            ViewModelProvider(this).get(CarroViewModel::class.java)

        _binding = FragmentCarroBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}