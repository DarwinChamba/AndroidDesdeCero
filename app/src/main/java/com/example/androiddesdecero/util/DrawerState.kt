package com.example.androiddesdecero.util

import androidx.compose.ui.graphics.Color
import com.example.androiddesdecero.R

sealed class DrawerState (
    val label:String,
    val icon: Int,
    val color: Color
){
    object Google: DrawerState("Autenticación con Google",
        R.drawable.ic_google,
        Color.Red
    )
    object Privacy: DrawerState(
        "Politicas y Privacidad",
        R.drawable.ic_privacy,
        Color.Gray
    )

    object Version: DrawerState(
        "Politicas y Privacidad",
        R.drawable.ic_privacy,
        Color.Gray
    )
}