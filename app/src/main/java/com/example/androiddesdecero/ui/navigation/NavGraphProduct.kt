package com.example.androiddesdecero.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddesdecero.ui.screen.product.ListProduct
import com.example.androiddesdecero.ui.screen.product.SaveProduct
import com.example.androiddesdecero.ui.viewmodel.ProductViewModel

@Composable
fun NavGraphProduct(
    productViewModel: ProductViewModel = viewModel()
) {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "product"
    ) {
        composable("product") {
            ListProduct(productViewModel){
                navController.navigate("save")
            }
        }
        composable("save") {
            SaveProduct(productViewModel)
        }
    }
}