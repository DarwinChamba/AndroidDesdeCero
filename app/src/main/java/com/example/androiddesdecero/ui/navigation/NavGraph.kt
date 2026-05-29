package com.example.androiddesdecero.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.navigation.compose.rememberNavController
import com.example.androiddesdecero.model.MyNotification
import com.example.androiddesdecero.ui.components.NavigationUi
import com.example.androiddesdecero.ui.screen.HomeScreen
import com.example.androiddesdecero.ui.screen.Notification
import com.example.androiddesdecero.ui.screen.Settings
import com.example.androiddesdecero.util.IconState
import kotlinx.serialization.json.Json

@Composable
fun NavGraph(intent: String?) {

    /*
     Scaffold es con contenedor que me permite organizar los
     componentes prinicpales de una aplicación como el topBar , bottomBar,
     floatingActionButton y el content que es el contenido principal de la aplicación
     ==========
    el objeto navController me permite navegar entre pantallas
    gestionar el backstack(historial de navegación)  y pasar argumentos
    entre pantallas
     */
    val navControler = rememberNavController()
    /*
    NavHost es un contenedor de pantallas de navegación reibe dos parametros
    el primero es un navController y el segundo es la ruta inicial de navegación
    PaddingValues -> evita que el contenido principal quede por debajo de topBar y bottomBar
     */
    val estaIncializado = navControler.currentBackStackEntryAsState().value != null

        LaunchedEffect(intent, estaIncializado) {
            if (intent != null && estaIncializado ) {
                val notification = Json.decodeFromString<MyNotification>(intent)
                when (notification.route) {
                    "settings" -> {
                        navControler.navigate(notification.route) {
                            popUpTo(navControler.graph.startDestinationId) {
                                saveState = true
                                inclusive = false
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }


    Scaffold(
        bottomBar = {
            NavigationUi(navControler)
        }
    ) {
        NavHost(
            navControler,
            startDestination = "home",
            modifier = Modifier.padding(it)
        ) {
            /*
            composable -> definide las pantallas de navegacion recibe un parametro
            que es la ruta
             */
            composable("home") {
                HomeScreen()
            }
            composable("notification") {
                Notification()
            }

            composable("settings") {
                Settings()
            }
        }
    }

}


