package com.example.androiddesdecero.ui.components

import android.R.attr.contentDescription
import android.R.attr.onClick
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.net.http.SslCertificate.restoreState
import android.net.http.SslCertificate.saveState
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddesdecero.R
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androiddesdecero.util.DrawerState
import com.example.androiddesdecero.util.IconState

@Preview(showBackground = true)
@Composable
fun TextFieldU() {
    OutlinedTextField(
        value = "",
        onValueChange = {
            println(it)
        },
        placeholder = {
            Text("Ingrese el nombre del producto")
        },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription="",
                tint = Color.Blue
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription="",
                tint = Color.Red
            )
        }
    )
}


@Composable
fun TextFieldUi(value:String,
                onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value =value ,
        onValueChange =onValueChange,
        placeholder = {
            Text("Ingrese el nombre del producto")
        },
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription="",
                tint = Color.Blue
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription="",
                tint = Color.Red
            )
        }
    )
}


@Composable
fun DrawerUi() {
    val context = LocalContext.current
    val list = listOf(
        DrawerState.Google,
        DrawerState.Privacy
    )

    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        // ── Header con imagen de fondo ──────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            // Imagen de fondo (reemplaza con tu recurso)
            Image(
                painter = painterResource(R.drawable.ic_google), // tu imagen aquí
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Overlay oscuro sobre la imagen
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
            )
            // Avatar + texto encima del overlay
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                        .border(2.dp, Color.White.copy(alpha = 0.4f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Hola 👋",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(
                        text = "Bienvenido de vuelta",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.75f)
                    )
                }
            }
        }

        // ── Sección CUENTA ──────────────────────────────────────
        Text(
            text = "CUENTA",
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 6.dp)
        )

        NavigationDrawerItem(
            label = {
                Column {
                    Text("Google", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "Vincular cuenta",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            selected = false,
            onClick = {
                Toast.makeText(context, "Autenticación con Google", Toast.LENGTH_SHORT).show()
            },
            icon = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE6F1FB)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = null,
                        tint = Color(0xFF185FA5),
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            badge = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
            },
            modifier = Modifier.padding(horizontal = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )

        // ── Sección INFORMACIÓN ─────────────────────────────────
        Text(
            text = "INFORMACIÓN",
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 16.dp, top = 14.dp, bottom = 6.dp)
        )

        list.forEach { state ->
            NavigationDrawerItem(
                label = { Text(state.label, style = MaterialTheme.typography.bodyMedium) },
                selected = false,
                onClick = {
                    Toast.makeText(context, state.label, Toast.LENGTH_SHORT).show()
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(state.color), // añade bgColor a DrawerState
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(state.icon),
                            contentDescription = null,
                            tint = state.color,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                },
                badge = {
                    if (state == DrawerState.Version) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {
                            Text(
                                "1.0.0",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                },
                modifier = Modifier.padding(horizontal = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun TextFieldUi(text: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TextTest() {
    Text(
        "Hola",
        fontSize = 30.sp,
        modifier = Modifier.background(Color.Blue),
        color = Color.White
    )

}

@Composable
fun TextNotification() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1A237E), // azul índigo oscuro
                        Color(0xFF5C6BC0)  // índigo vibrante
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                tint = Color(0xFFE8EAF6),
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Notificaciones con\nAlarmManager & BroadcastReceiver",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp
                ),
                color = Color.White
            )
        }
    }
}

@Composable
fun DialogHora(time: (Long) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hora, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hora)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)
            time(calendar.timeInMillis)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false

    )


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text("Seleccionar Hora")
        IconButton(onClick = { timePickerDialog.show() }) {
            Icon(
                painter = painterResource(R.drawable.clock), contentDescription = "",
                tint = Color.Blue
            )
        }
    }
}

@Composable
fun NavigationUi(navController: NavController) {
    val list = listOf(IconState.Home, IconState.Notification, IconState.Setting)
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        list.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.route) {
                            item.icon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.label,
                        tint = item.color
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
fun SpacerUi(heigh: Int) {
    Spacer(modifier = Modifier.height(heigh.dp))
}

@Composable
fun AdsTitleHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0D0D0F))
            .padding(horizontal = 20.dp, vertical = 35.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {

            // Chip superior
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .background(
                        color = Color(0xFF7C5CFC).copy(alpha = 0.15f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF7C5CFC).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(Color(0xFF00D296), CircleShape)
                )
                Text(
                    text = "COMPOSE-ADMOB",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFA78BFA),
                    letterSpacing = 1.sp
                )
            }

            // Título principal
            Text(
                text = "Cómo agregar\npublicidad a tu App 💸",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                lineHeight = 28.sp
            )

            // Subtítulo con badges
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AdBadge(label = "Banner", color = Color(0xFF7C5CFC))
                Text(text = "·", color = Color(0xFF444455), fontSize = 12.sp)
                AdBadge(label = "Interstitial", color = Color(0xFF00D296))
            }
        }
    }
}

@Composable
private fun AdBadge(label: String, color: Color) {
    Text(
        text = label,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = color,
        modifier = Modifier
            .background(
                color = color.copy(alpha = 0.12f),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 8.dp, vertical = 3.dp)
    )
}

@Composable
fun ButtonNotification(enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick, enabled = enabled
    ) {
        Text("Crear  Notificación")
    }
}

@Composable
fun ButtonUi(
    enabled: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Animación de escala al presionar
    val scale by animateFloatAsState(
        targetValue = if (isPressed && enabled) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "scale"
    )

    // Animación de opacidad cuando está deshabilitado
    val contentAlpha by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.38f,
        animationSpec = tween(300),
        label = "alpha"
    )

    val shape = RoundedCornerShape(14.dp)

    // Gradiente principal del botón
    val enabledGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFB22222), // Crimson oscuro
            Color(0xFF8B0000), // Deep red
            Color(0xFF5C0000)  // Almost black red
        ),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    )

    val disabledGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3A3A3A),
            Color(0xFF2A2A2A)
        )
    )

    val gradient = if (enabled) enabledGradient else disabledGradient

    Box(
        modifier = Modifier
            .scale(scale)
            .clip(shape)
            .background(gradient)
            .border(
                width = 1.dp,
                brush = if (enabled) Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFF6B6B).copy(alpha = 0.6f),
                        Color.Transparent,
                        Color(0xFFFF6B6B).copy(alpha = 0.2f)
                    )
                ) else Brush.linearGradient(
                    colors = listOf(Color(0xFF555555), Color.Transparent)
                ),
                shape = shape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
            .padding(horizontal = 32.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Brillo superior sutil (efecto glass)
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = if (enabled) 0.08f else 0.03f),
                            Color.Transparent
                        ),
                        endY = 40f
                    )
                )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Ícono de corazón/vida
            Text(
                text = "♥",
                color = Color(0xFFFF4444).copy(alpha = contentAlpha),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "PERDER VIDAS",
                color = Color(0xFFF5F5F5).copy(alpha = contentAlpha),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.5.sp
            )
        }
    }
}


@Composable
fun VidasUsuario(vidas: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            repeat(3) { index -> //index = 2  vidas = 2
                Icon(
                    painter = painterResource(
                        if (index < vidas) {
                            R.drawable.heart_fill
                        } else {
                            R.drawable.heart_outline
                        }
                    ), contentDescription = "",
                    tint = Color.Red
                )

            }
        }

    }
}