package com.example.androiddesdecero

import android.Manifest
import android.R.id.message
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.androiddesdecero.ui.navigation.NavGraph
import com.example.androiddesdecero.ui.theme.AndroidDesdeCeroTheme

import com.google.android.gms.ads.MobileAds
import com.example.androiddesdecero.ui.screens.LoadingScreen

class MainActivity : ComponentActivity() {


    val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MobileAds.initialize(this)
        solicitarPermisos()
        //NotificationHelper.createChannel(this)

        // Intent cuando la app estaba cerrada
        val intentActual = intent.getStringExtra("notification")

        setContent {

            NavGraph()
        }
    }



    private fun solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}



