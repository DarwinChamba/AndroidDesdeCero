package com.example.androiddesdecero.ui.screen.product

import android.R.attr.contentDescription
import android.R.string.no
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androiddesdecero.R
import com.example.androiddesdecero.ui.components.CardProduct
import com.example.androiddesdecero.ui.components.SeleccionarImage
import com.example.androiddesdecero.ui.components.TextFieldUi
import com.example.androiddesdecero.ui.components.TitleProduct
import com.example.androiddesdecero.ui.viewmodel.ProductViewModel
import com.example.androiddesdecero.util.ProductState

@Composable
fun SaveProduct(
    productViewModel: ProductViewModel

) {
    val imageUri by productViewModel.uriImage.collectAsState()
    val product by productViewModel.product.collectAsState()
    val context = LocalContext.current
    val result by productViewModel.result.collectAsState()
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.mujerprogramando)
    )
    val isEditing  by productViewModel.isEditing.collectAsState()


    val launch = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->

        //let trabaja sobre objetos no nulos

        uri?.let {
            productViewModel.setImage(uri)
        }

    }
    when (val state = result) {
        is ProductState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }

        ProductState.Idle -> {}
        ProductState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ProductState.Success -> {
            Toast.makeText(context, state.data, Toast.LENGTH_SHORT).show()
            productViewModel.reset()
        }
    }

        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 40.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleProduct( if(isEditing)"Editar Producto" else "Registrar Productos")
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(200.dp)
        )


            TextFieldUi(product.name) {
                productViewModel.setName(it)
            }

            SeleccionarImage{
                launch.launch("image/*")
            }


            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                onClick = {
                    if(isEditing){
                        productViewModel.updateProduct(context)
                    }else{
                        productViewModel.insertProduct(context)
                    }

                },
                enabled = product.name.isNotEmpty()
            ) {
                Text( if(isEditing) "Editar Producto" else "Guardar Producto")
            }

            if (imageUri != null || product.image.isNotEmpty()) {
                AsyncImage(
                    model = if (imageUri !=null) imageUri else product.image,
                    contentDescription = "",
                    modifier = Modifier.size(150.dp)
                )
            }


        }
    }
