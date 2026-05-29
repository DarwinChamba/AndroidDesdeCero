package com.example.androiddesdecero.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream


class ImageManager {

    fun uriToPath(context: Context, uri: Uri): String {
        /*
        abre el flujo de lectura para leer los bytes  que contiene la uri
         */
        val inputStream = context.contentResolver.openInputStream(uri)
        /*
        creamos un archivo que apunta al almacenamiento interno del dispositivo

         */
        val file = File(context.filesDir, "image_${System.currentTimeMillis()}.jpg")
         val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return  file.absolutePath

    }
}