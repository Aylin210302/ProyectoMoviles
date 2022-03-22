package com.proyectoMoviles.ui.carro

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.proyectoMoviles.R
import com.proyectoMoviles.databinding.FragmentUpdateCarroBinding
import com.proyectoMoviles.model.Carro
import com.proyectoMoviles.viewmodel.CarroViewModel

class UpdateCarroFragment : Fragment() {
    private lateinit var carroViewModel: CarroViewModel
    private var _binding: FragmentUpdateCarroBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateCarroFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carroViewModel = ViewModelProvider(this).get(CarroViewModel::class.java)

        _binding = FragmentUpdateCarroBinding.inflate(inflater, container, false)

        binding.etModelo.setText(args.carro.modelo)
        binding.etAnnio.setText(args.carro.año)
        binding.etMotor.setText(args.carro.motor)
        binding.etPrecio.setText(args.carro.precio.toString())
        binding.etCantidadAsientos.setText(args.carro.cantidadAsientos.toString())
        binding.etCaja.setText(args.carro.tipoCaja)

        binding.btUpdateCarro.setOnClickListener {
            actualizarCarro()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //si es delete ...
        if(item.itemId==R.id.delete_menu) {
            deleteCarro()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actualizarCarro() {
        val modelo = binding.etModelo.text.toString()
        if (modelo.isNotEmpty()) {
            val año = binding.etAnnio.text.toString()
            val marca = binding.etMarca.text.toString()
            val motor = binding.etMotor.text.toString()
            val precio = binding.etPrecio.text.toString().toDouble()
            val cantidadAsientos = binding.etCantidadAsientos.text.toString().toInt()
            val cajaCambio = binding.etCaja.text.toString()
            val carro = Carro(args.carro.id,modelo,año,marca,motor,precio,cantidadAsientos,cajaCambio,"")
            carroViewModel.updateCarro(carro)
            Toast.makeText(requireContext(),
                getString(R.string.msg_carro_update),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_nav_updateCarro_to_nav_carro)
        }
    }

    private fun deleteCarro() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.menu_delete)
        builder.setMessage(getString(R.string.msg_seguroBorrar) + "${args.carro.modelo}?")
        builder.setNegativeButton(getString(R.string.no)) {_,_ ->}
        builder.setPositiveButton(getString((R.string.si))) {_,_ ->
            carroViewModel.deleteCarro(args.carro)
            findNavController().navigate(R.id.action_nav_updateCarro_to_nav_carro)
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}