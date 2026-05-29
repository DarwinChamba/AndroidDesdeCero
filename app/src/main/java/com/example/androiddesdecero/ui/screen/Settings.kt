package com.example.androiddesdecero.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androiddesdecero.R
import com.example.androiddesdecero.ui.components.TextFieldUi
import org.jetbrains.annotations.Async

/*
modifier = Modifier.fillMaxSize() -> ocupa toda el ancho y alto del
dispositivo
 */
@Composable
fun Settings() {
    var title by remember {
        mutableStateOf("")
    }

    var image by remember { mutableStateOf<Uri?>(null) }
    val request = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        image = it
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.mujerprogramando)
        )
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )
        TextFieldUi(title){
            title = it
        }

        Button(
            onClick = {
                request.launch("image/*")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta
            )
        ) {
            Text(
                "Seleccionar Imagen",
                fontWeight = FontWeight.Bold
            )
        }

        if (image != null) {
            AsyncImage(
                model = image,
                contentDescription = ""
            )
        }
    }
}