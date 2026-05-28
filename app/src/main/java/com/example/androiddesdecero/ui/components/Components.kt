package com.example.androiddesdecero.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ButtonUi(onClick:()->Unit){
    Button(onClick =onClick
    ) {
        Text("Mostrar Publicidad")
    }
}