package com.example.androiddesdecero.model

import android.content.Context
import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileOutputStream
import java.net.URI

@Serializable
data class MyNotification (
    val title: String="",
    val description: String="",
    val requestCode:Int= System.currentTimeMillis().toInt(),
    val time: Long=0L,
    val route: String="settings"
)

