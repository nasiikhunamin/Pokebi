package com.nasikhunamin.pokebi

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val photo: String,
    val tinggi: String,
    val berat: String,
    val kelemahan: String,
    val kategori: String,
    val tipe: String,
    val kemampuan: String
) : Parcelable