package com.example.androiddesdecero.ui.screen

import android.R.attr.data
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androiddesdecero.R
import com.example.androiddesdecero.ui.components.TextFieldUi
import com.example.androiddesdecero.ui.viewmodel.ProductViewModel
import com.example.androiddesdecero.util.ProductState
import org.jetbrains.annotations.Async

/*
modifier = Modifier.fillMaxSize() -> ocupa toda el ancho y alto del
dispositivo
 */
@Composable
fun Settings(
    productViewModel: ProductViewModel = viewModel()
) {
    val imageUri by productViewModel.uriImage.collectAsState()
    val product by productViewModel.product.collectAsState()
    val context = LocalContext.current
    val result by productViewModel.result.collectAsState()

    val request = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        /*
        let trabaja sobre objetos no nulos
         */
        uri?.let {
            productViewModel.setImage(uri)
        }

    }
    when(val state =result){
        is ProductState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }
        ProductState.Idle -> {}
        ProductState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is ProductState.Success->{
            Toast.makeText(context,state.data , Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.mujerprogramando)
        )
        /*
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )

         */
        TextFieldUi(product.name) {
            productViewModel.setName(it)
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

        Button(
            onClick = {
                productViewModel.insertProduct(context)
            },
            enabled = product.name.isNotEmpty()
        ) {
            Text("Guardar Producto")
        }

        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = ""
            )
        }
    }
}