package com.proyectoMoviles.ui.carro

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
        binding.etCorreo.setText(args.carro.correo)
        binding.etTelefono.setText(args.carro.telefono)
        binding.etWeb.setText(args.carro.web)

        binding.btUpdateCarro.setOnClickListener { actualizarCarro() }
        binding.btEmail.setOnClickListener { enviarCorreo() }
        binding.btPhone.setOnClickListener { hacerLlamada() }
        binding.btWhatsapp.setOnClickListener { enviarWhatsap() }
        binding.btWeb.setOnClickListener { verWeb() }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun enviarCorreo() {
        val correo = binding.etCorreo.text.toString()
        if(correo.isNotEmpty()){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))

            intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.msg_saludos))

            intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.msg_mensaje_correo))

            startActivity(intent)

        } else {
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }

    private fun hacerLlamada() {
        val telefono = binding.etTelefono.text.toString()
        if(telefono.isNotEmpty()){
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$telefono")

            if(requireActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
                requireActivity().requestPermissions(arrayOf(Manifest.permission.CALL_PHONE),105)
            } else {
                requireActivity().startActivity(intent)
            }

        } else {
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }

    private fun enviarWhatsap() {
        val telefono = binding.etTelefono.text.toString()
        if(telefono.isNotEmpty()){
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = "whatsapp://send?phone=506$telefono&text="+
                    getString(R.string.msg_saludos)

            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(uri)
            startActivity(intent)

        } else {
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
    }

    private fun verWeb() {
        val sitio = binding.etWeb.text.toString()
        if(sitio.isNotEmpty()){
            val web = Uri.parse("http://$sitio")
            val intent = Intent(Intent.ACTION_VIEW,web)

            startActivity(intent)

        } else {
            Toast.makeText(requireContext(),getString(R.string.msg_datos),Toast.LENGTH_LONG).show()
        }
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
            val correo = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()
            val web = binding.etWeb.text.toString()
            val carro = Carro(args.carro.id,modelo,año,marca,motor,precio,cantidadAsientos,cajaCambio,"",correo,telefono,web)
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