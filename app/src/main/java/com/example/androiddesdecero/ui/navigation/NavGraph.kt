package com.example.androiddesdecero.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androiddesdecero.IconState
import com.example.androiddesdecero.ui.screens.LoadingScreen
import com.example.androiddesdecero.ui.screens.LoginScreen
import com.example.androiddesdecero.ui.screens.RegisterScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            NavigationBarUi(navController)
        }
    ) {
        NavHost(
            navController,
            startDestination = "splash",
            modifier = Modifier.padding(it)
        ) {
            composable("splash") {
                LoadingScreen()
            }
            composable("login") {
                LoginScreen()
            }
            composable("register") {
                RegisterScreen()
            }
        }
    }

}

@Composable
fun NavigationBarUi(navController: NavController) {
    val list = listOf(
        IconState("splash","Home",Icons.Default.Home),
        IconState("login","Login",Icons.Default.Person),
        IconState("register","Register",Icons.Default.Settings),

    )
    val ruta = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
    NavigationBar {
        list.forEach {
            NavigationBarItem(
                selected = it.route == ruta ,
                onClick = {
                    navController.navigate(it.route){
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, label = {
                    Text(it.label)
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = ""
                    )
                }
            )
        }


    }
}