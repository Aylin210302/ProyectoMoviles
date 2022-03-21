package com.proyectoMoviles.repository

import androidx.lifecycle.LiveData
import com.proyectoMoviles.data.CarroDao
import com.proyectoMoviles.model.Carro

class CarroRepository(private val carroDao: CarroDao) {
    val getAllData : LiveData<List<Carro>> = carroDao.getAllData()

    suspend fun addCarro(carro: Carro) {
        carroDao.addCarro(carro)
    }

    suspend fun updateCarro(carro: Carro) {
        carroDao.updateCarro(carro)
    }

    suspend fun deleteCarro(carro: Carro) {
        carroDao.deleteCarro(carro)
    }
}