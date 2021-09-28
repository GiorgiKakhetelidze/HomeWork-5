package com.example.homework_5

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val age: Int
) : Parcelable
