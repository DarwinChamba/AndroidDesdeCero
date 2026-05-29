package com.example.androiddesdecero.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddesdecero.ui.components.ButtonNotification
import com.example.androiddesdecero.ui.components.DialogHora
import com.example.androiddesdecero.ui.components.TextFieldUi
import com.example.androiddesdecero.ui.components.TextNotification
import com.example.androiddesdecero.ui.viewmodel.NotificationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Notification(
    notificationViewModel: NotificationViewModel = viewModel()
) {
    val context = LocalContext.current
    val notification by notificationViewModel.notification.collectAsState()
    var hora by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextNotification()
        TextFieldUi("Ingrese el título", notification.title) {
            notificationViewModel.setTitle(it)
        }
        TextFieldUi("Ingrese la descripcion", notification.description) {
            notificationViewModel.setDescription(it)
        }
        DialogHora {
            notificationViewModel.setTime(it)
            val simpleDateFormat = SimpleDateFormat("HH:mm",Locale.getDefault())
            hora = simpleDateFormat.format(Date(it))
        }
        if(hora.isNotEmpty()){
            Text("Hora seleccionda $hora")
        }
        ButtonNotification(notificationViewModel.enabledButton()) {
            notificationViewModel.createNotification(context)
            Toast.makeText(context, "Notificación creada", Toast.LENGTH_SHORT).show()
        }
    }
}