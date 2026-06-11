package com.example.androiddesdecero.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androiddesdecero.R

@Preview(showBackground = true)
@Composable
fun LoginScreen() {

    var image by remember { mutableStateOf<Uri?>(null) }

    val lancher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        image = it
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { //llave de inicio del layout column
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "",
                tint = Color(0xFF23B7CE)
            )
            Text(
                "Login Screen",
                fontSize = 30.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        } //llave de cierre del layout row

        Row {

            Button(
                onClick = {
                    lancher.launch("image/*")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_image),
                    contentDescription = "",
                    tint = Color(0xFF23B7CE)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text("Buscar Imagen")
            }
        }

        Text(
            "Hola",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        if(image != null){
            AsyncImage(
                model = image,
                contentDescription = ""
            )
        }

    }//llave de cierre del layout Column
}