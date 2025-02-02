package com.raji.movies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    secondary = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    error = Color(0xFFD32F2F),
    onError = Color.Blue
)

private val DarkColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    inversePrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    surface = Color.Black,
    error = Color(0xFFD32F2F),
    onError = Color.White

)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}