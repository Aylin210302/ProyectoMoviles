package com.proyectoMoviles.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.proyectoMoviles.model.Carro

@Dao
interface CarroDao {
    @Query("select * from CARRO")
    fun getAllData() : LiveData<List<Carro>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCarro(carro: Carro)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCarro(carro: Carro)

    @Delete
    suspend fun deleteCarro(carro: Carro)
}