package com.example.androiddesdecero.util

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/*
sealed class -> me permite definir  un conjunto cerrado de tipos es
decir que nosotros vamos a deifnir que subclases o clase hijas van a
a heredar de la sealed class
 */
sealed class IconState(
    val icon: ImageVector,
    val  unselectedIcon: ImageVector,
    val label:String,
    val route:String,
    val color: Color
) {
    object Home: IconState(Icons.Filled.Home,
        Icons.Outlined.Home,
        "Home","home",
        Color(0xFF1DB2C9)
    )
    object Notification: IconState(Icons.Filled.Notifications,
        Icons.Outlined.Notifications,
        "Notification","notification",
        Color(0xFFE31471)
    )

    object  Setting: IconState(
        Icons.Filled.Settings,
        Icons.Outlined.Settings,
        "Settings",
        "settings",
        Color(0xFFF1CD0F)

    )
}

