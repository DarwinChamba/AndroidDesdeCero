package com.example.androiddesdecero.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddesdecero.R
import com.example.androiddesdecero.ui.components.AdsTitleHeader
import com.example.androiddesdecero.ui.components.ButtonUi
import com.example.androiddesdecero.ui.components.DrawerUi
import com.example.androiddesdecero.ui.components.SpacerUi
import com.example.androiddesdecero.ui.components.VidasUsuario
import com.example.androiddesdecero.ui.viewmodel.InterstitialsViewModel
import com.example.androiddesdecero.util.DrawerState
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(
    interstitialsViewModel: InterstitialsViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var vidas by rememberSaveable { mutableStateOf(3) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope  ()
    LaunchedEffect(Unit) {
        interstitialsViewModel.solicitarAnuncio(context)
    }
    LaunchedEffect(vidas) {
        if (vidas == 0) {
            interstitialsViewModel.mostrarAnuncio(context){
                vidas =3
            }
        }
    }
    ModalNavigationDrawer(
        drawerContent = {
            DrawerUi()
        },
        drawerState = drawerState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
            AdsTitleHeader()

            SpacerUi(20)
            VidasUsuario(vidas)

            /*
        AndroidView se utiliza para mostrar vistas tradicionale de android
        dentro de jetpack Compose
         */
            AndroidView(
                factory = {
                    AdView(it).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = "ca-app-pub-3940256099942544/9214589741"
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )

            AndroidView(
                factory = {
                    AdView(it).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = "ca-app-pub-3940256099942544/9214589741"
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
            ButtonUi(vidas != 0) {
                vidas--
            }
            Spacer(modifier = Modifier.weight(1f))
        }//llave de cierre del layoutColum
    }//llave de cierre del ModalNavigationDrawer

}

