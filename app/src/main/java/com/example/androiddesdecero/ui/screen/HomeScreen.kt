package com.example.androiddesdecero.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddesdecero.ui.components.ButtonUi
import com.example.androiddesdecero.ui.viewmodel.InterstitialsViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


@Composable
fun HomeScreen(
    interstitialsViewModel: InterstitialsViewModel = viewModel()
) {
    val context= LocalContext.current
    LaunchedEffect(Unit) {
        interstitialsViewModel.solicitarAnuncio(context)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Como Agregar publicidad:Banner",
            fontSize = 20.sp,
            modifier = Modifier
                .background(Color.Black)
                .padding(10.dp),
            color = Color.White
        )

        /*
        AndroidView se utiliza para mostrar vistas tradicionale de android
        dentro de jetpack Compose
         */
        AndroidView(
            factory = {
                AdView(it).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId="ca-app-pub-3940256099942544/9214589741"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )

        AndroidView(
            factory = {
                AdView(it).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId="ca-app-pub-3940256099942544/9214589741"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
        ButtonUi {
            interstitialsViewModel.mostrarAnuncio(context)
        }
    }
}