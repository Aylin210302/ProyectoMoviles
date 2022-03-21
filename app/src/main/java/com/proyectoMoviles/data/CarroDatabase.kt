package com.proyectoMoviles.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyectoMoviles.model.Carro

@Database(entities = [Carro::class], version = 1, exportSchema = false)
abstract class CarroDatabase : RoomDatabase() {
    abstract fun carroDao() : CarroDao

    companion object {
        @Volatile
        private var INSTANCE: CarroDatabase? = null

        fun getDatabase(context: android.content.Context) : CarroDatabase {
            var instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                val basedatos = Room.databaseBuilder(
                    context.applicationContext,
                    CarroDatabase::class.java,
                    "carro_database"
                ).build()
                INSTANCE = basedatos
                return basedatos
            }
        }
    }
}
