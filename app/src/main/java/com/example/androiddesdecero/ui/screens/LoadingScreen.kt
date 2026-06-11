package com.example.androiddesdecero.ui.screens

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.androiddesdecero.R

@Composable
fun LoadingScreen() {
    val backgroundColor = Color(0xFF1A1A2E)
    val surfaceColor    = Color(0xFF23234A)
    val accentPurple    = Color(0xFF9B8FFF)
    val accentBlue      = Color(0xFF6B8FFF)
    val textPrimary     = Color(0xFFE8E8FF)
    val textSecondary   = Color(0xFF9898C8)

    // ── Animación infinite progress para la barra secundaria ──
    val infiniteTransition = rememberInfiniteTransition(label = "secondary_bar")
    val secondaryProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue  = 0.65f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Restart
        ),
        label = "secondary_progress"
    )

    // ── Lottie ──
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.mujerprogramando) // <-- tu archivo
    )
    val lottieProgress by animateLottieCompositionAsState(
        composition = composition,
        iterations   = LottieConstants.IterateForever
    )



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {

            // ── Título ──
            Text(
                text       = "Hola",
                fontSize   = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color      = textPrimary,
                textAlign  = TextAlign.Center,
                lineHeight = 32.sp
            )

            // ── Lottie Animation ──
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(surfaceColor),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    progress    = { lottieProgress },
                    modifier    = Modifier.size(130.dp)
                )
            }

            // ── Progress bars ──
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Etiqueta + porcentaje
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Progreso", fontSize = 13.sp, color = textSecondary, fontWeight = FontWeight.SemiBold)

                }

                // Barra principal
                LinearProgressIndicator(
                    modifier          = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(99.dp)),
                    color             = accentPurple,
                    trackColor        = Color(0xFF2A2A50),
                    strokeCap         = StrokeCap.Round
                )

                // Barra secundaria (indeterminada animada)
                LinearProgressIndicator(
                    progress          = { secondaryProgress },
                    modifier          = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(99.dp)),
                    color             = accentBlue,
                    trackColor        = Color(0xFF2A2A50),
                    strokeCap         = StrokeCap.Round
                )

                // Puntos pulsantes decorativos
                BouncingDots(color = accentPurple)
            }
        }
    }
}

// ── Puntos pulsantes ──────────────────────────────────────────
@Composable
private fun BouncingDots(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        listOf(0, 200, 400).forEach { delayMs ->
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.25f,
                targetValue  = 1f,
                animationSpec = infiniteRepeatable(
                    animation  = tween(600, delayMillis = delayMs),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "dot_$delayMs"
            )
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(color.copy(alpha = alpha))
            )
        }
    }
}