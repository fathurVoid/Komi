package com.kopidannyemil

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

val Brown = Color(0xFF333333)
val BrownMid = Color(0xFF4A4A4A)
val Beige = Color(0xFFF4F4F6)
val BeigeLight = Color(0xFFFFFFFF)
val Cream = Color(0xFFFFFFFF)
val Tan = Color(0xFFE4E4E7)
val Muted = Color(0xFF71717A)
val ErrorRed = Color(0xFFDC2626)
private val BeanColor = Color(0xFFE4E4E7)
fun Modifier.coffeeBackground(): Modifier = this
    .background(Beige)
    .drawBehind {
        val beans = listOf(
            Triple(0.08f, 0.10f, -30f), Triple(0.88f, 0.18f, 25f), Triple(0.18f, 0.42f, 50f),
            Triple(0.92f, 0.58f, -40f), Triple(0.12f, 0.78f, 20f), Triple(0.72f, 0.92f, -20f)
        )
        val w = 64.dp.toPx()
        val h = 96.dp.toPx()
        beans.forEach { (fx, fy, angle) ->
            val c = Offset(size.width * fx, size.height * fy)
            rotate(angle, c) {
                drawOval(BeanColor, Offset(c.x - w / 2, c.y - h / 2), Size(w, h))
                drawLine(Beige, Offset(c.x, c.y - h / 2 + 8.dp.toPx()), Offset(c.x, c.y + h / 2 - 8.dp.toPx()), 4.dp.toPx())
            }
        }
    }

private val Scheme = lightColorScheme(
    primary = Brown, onPrimary = Cream,
    secondary = BrownMid, onSecondary = Cream,
    background = Beige, onBackground = Brown,
    surface = BeigeLight, onSurface = Brown,
    error = ErrorRed
)

@Composable
fun KopiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Scheme,
        shapes = Shapes(
            small = RoundedCornerShape(12.dp),
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(24.dp)
        )
    ) {
        Surface(color = Beige, contentColor = Brown, content = content)
    }
}
