package com.proyectoMoviles.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "carro")
data class Carro(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "modelo")
    val modelo: String,
    @ColumnInfo(name = "año")
    val año: String?,
    @ColumnInfo(name = "marca")
    val marca: String?,
    @ColumnInfo(name = "motor")
    val motor: String?,
    @ColumnInfo(name = "precio")
    val precio: Double?,
    @ColumnInfo(name = "cantidadAsientos")
    val cantidadAsientos: Int?,
    @ColumnInfo(name = "tipoCaja")
    val tipoCaja: String?,
    @ColumnInfo(name = "rutaImagen")
    val rutaImagen: String?,
    @ColumnInfo(name = "correo")
    val correo: String?,
    @ColumnInfo(name = "telefono")
    val telefono: String?,
    @ColumnInfo(name = "web")
    val web: String?
) : Parcelable

