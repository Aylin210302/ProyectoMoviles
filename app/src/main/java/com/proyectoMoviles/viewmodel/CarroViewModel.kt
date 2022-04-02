package com.proyectoMoviles.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.proyectoMoviles.data.CarroDatabase
import com.proyectoMoviles.model.Carro
import com.proyectoMoviles.repository.CarroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarroViewModel(application: Application)
    : AndroidViewModel(application) {

    val getAllData : LiveData<List<Carro>>
    private val repository: CarroRepository

    init {
        val carroDao = CarroDatabase.getDatabase(application).carroDao()
        repository = CarroRepository(carroDao)
        getAllData = repository.getAllData
    }

    fun addCarro(carro: Carro) {
        viewModelScope.launch(Dispatchers.IO) {repository.addCarro(carro)}
    }

    fun updateCarro(carro: Carro) {
        viewModelScope.launch(Dispatchers.IO) {repository.updateCarro(carro)}
    }

    fun deleteCarro(carro: Carro) {
        viewModelScope.launch(Dispatchers.IO) {repository.deleteCarro(carro)}
    }
}