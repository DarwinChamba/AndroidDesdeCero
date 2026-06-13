package com.example.androiddesdecero.ui.screen.product

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androiddesdecero.R
import com.example.androiddesdecero.ui.components.CardProduct
import com.example.androiddesdecero.ui.components.TextFieldUi
import com.example.androiddesdecero.ui.components.TitleProduct
import com.example.androiddesdecero.ui.viewmodel.ProductViewModel
import com.example.androiddesdecero.util.ProductState

@Composable
fun ListProduct(
    productViewModel: ProductViewModel ,
    navigate: () -> Unit
) {
    val context = LocalContext.current
    val allProduct by productViewModel.allProduct.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = navigate) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleProduct("Lista de Productos")
            when (val data = allProduct) {
                is ProductState.Error -> {
                    println(data.message)
                }

                ProductState.Idle -> {}
                ProductState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is ProductState.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        items(data.data) { product ->
                            CardProduct(product) {
                                navigate()
                                productViewModel.setProduct(product)
                            }
                        }
                    }


                }
            }
        }
    }
}