package com.example.gamelist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game (
    val title: String,
    val description: String,
    val photo: Int,
    val price: String,
    val release: String
):Parcelable