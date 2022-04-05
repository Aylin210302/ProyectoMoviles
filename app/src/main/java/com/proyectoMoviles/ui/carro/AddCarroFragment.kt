package com.proyectoMoviles.ui.carro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.lugares.utiles.ImagenUtiles
import com.proyectoMoviles.R
import com.proyectoMoviles.databinding.FragmentAddCarroBinding
import com.proyectoMoviles.model.Carro
import com.proyectoMoviles.viewmodel.CarroViewModel

class AddCarroFragment : Fragment() {
    private lateinit var carroViewModel: CarroViewModel
    private var _binding: FragmentAddCarroBinding? = null
    private val binding get() = _binding!!

    private lateinit var imagenUtiles: ImagenUtiles

    private lateinit var tomarFotoActivity : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        carroViewModel = ViewModelProvider(this).get(CarroViewModel::class.java)

        _binding = FragmentAddCarroBinding.inflate(inflater, container, false)

        binding.btAddCarro.setOnClickListener {
            binding.progressBar.visibility = ProgressBar.VISIBLE
            binding.msgMensaje.text = getString(R.string.msg_subiendo_imagen)
            binding.msgMensaje.visibility = TextView.VISIBLE
            subeImagenNube()
        }

        tomarFotoActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                imagenUtiles.actualizaFoto()
            }

        }

        imagenUtiles = ImagenUtiles(
            requireContext(),
            binding.btPhoto,
            binding.btRotaL,
            binding.btRotaR,
            binding.imagen,
            tomarFotoActivity
        )

        return binding.root
    }

    private fun subeImagenNube() {
        val imagenFile = imagenUtiles.imagenFile
        if(imagenFile.exists() && imagenFile.isFile && imagenFile.canRead()){
            val ruta = Uri.fromFile(imagenFile)
            val rutaNube = "CarrosApp/${Firebase.auth.currentUser?.email}/imagenes/${imagenFile.name}"

            val referencia : StorageReference = Firebase.storage.reference.child(rutaNube)
            referencia.putFile(ruta).
            addOnSuccessListener {
                referencia.downloadUrl
                    .addOnSuccessListener {
                        val rutaImagen = it.toString()
                        agregarCarro(rutaImagen)
                    }
            }
                .addOnFailureListener{
                    agregarCarro("")
                }
        }else {
            agregarCarro("")
        }
    }

    private fun agregarCarro(rutaImagen: String) {
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
            val carro = Carro(0,modelo,año,marca,motor,precio,cantidadAsientos,cajaCambio,rutaImagen,correo,telefono,web)
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