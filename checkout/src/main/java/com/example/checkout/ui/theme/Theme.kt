package com.example.checkoutlibrary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
//)
//
//private val LightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200
//
//    /* Other default colors to override
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,
//    */
//)

//@Composable
//fun CheckoutLibraryTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
//
//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        shapes = Shapes,
//        content = content
//    )
//}

private val LightColorPalette = CheckColors(
    colorPrimary = TealPrimary,
    colorOnPrimary = Color.White,
    colorSecondary = DarkBlue,
    colorOnSecondary = Color.White,
    uiBackground = GreyShade100,
    uiBackground2 = Color.White,
    uiBackground3 = Teal100,
    cardBackground = Color.White,
    textPrimary = Color.Black,
    textSecondary = Grey,
    outline = GreyShade300,
    outlineDarker = Grey,
    buttonLight = GreyShade100,
    buttonDisabled = GreyShade200,
    textDisabled = GreyShade700,
    textHint = GreyShade700,
    inputBackground = GreyShade300,
    error = Red,
    isDark = false
)

private val DarkColorPalette = CheckColors(
    colorPrimary = TealPrimary,
    colorOnPrimary = Color.White,
    colorSecondary = DarkBlue,
    colorOnSecondary = Color.White,
    uiBackground = GreyShade100,
    uiBackground2 = Color.White,
    uiBackground3 = Teal100,
    cardBackground = Color.White,
    textPrimary = Color.Black,
    textSecondary = Grey,
    outline = GreyShade300,
    outlineDarker = Grey,
    buttonLight = GreyShade100,
    buttonDisabled = GreyShade200,
    textDisabled = GreyShade700,
    textHint = GreyShade700,
    inputBackground = GreyShade300,
    error = Red,
    isDark = true
)

@Composable
fun CheckTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideHubtelColors(colors = colors) {
        MaterialTheme(
            colors = debugColor(darkTheme),
            typography = AppTypography,
            shapes = Shapes,
            content = content
        )
    }
}

class CheckColors(
    colorPrimary: Color,
    colorOnPrimary: Color,
    colorSecondary: Color,
    colorOnSecondary: Color,
    uiBackground: Color,
    uiBackground2: Color,
    uiBackground3: Color,
    cardBackground: Color,
    textPrimary: Color,
    textSecondary: Color,
    outline: Color,
    outlineDarker: Color,
    buttonLight: Color,
    buttonDisabled: Color,
    textDisabled: Color,
    textHint: Color,
    inputBackground: Color,
    error: Color,
    isDark: Boolean,
) {

    var colorPrimary by mutableStateOf(colorPrimary)
        private set

    var colorOnPrimary by mutableStateOf(colorOnPrimary)
        private set

    var colorSecondary by mutableStateOf(colorSecondary)
        private set

    var colorOnSecondary by mutableStateOf(colorOnSecondary)
        private set

    var uiBackground by mutableStateOf(uiBackground)
        private set

    var uiBackground2 by mutableStateOf(uiBackground2)
        private set

    var uiBackground3 by mutableStateOf(uiBackground3)
        private set

    var cardBackground by mutableStateOf(cardBackground)
        private set

    var textPrimary by mutableStateOf(textPrimary)
        private set

    var textSecondary by mutableStateOf(textSecondary)
        private set

    var outline by mutableStateOf(outline)
        private set

    var outlineDarker by mutableStateOf(outlineDarker)
        private set

    var buttonLight by mutableStateOf(buttonLight)
        private set

    var buttonDisabled by mutableStateOf(buttonDisabled)
        private set

    var textDisabled by mutableStateOf(textDisabled)
        private set

    var textHint by mutableStateOf(textHint)
        private set

    var inputBackground by mutableStateOf(inputBackground)
        private set

    var error by mutableStateOf(error)
        private set

    var isDark by mutableStateOf(isDark)
        private set

    fun update(newColors: CheckColors) {
        colorPrimary = newColors.colorPrimary
        colorOnPrimary = newColors.colorOnPrimary

        colorSecondary = newColors.colorSecondary
        colorOnSecondary = newColors.colorOnSecondary

        uiBackground = newColors.uiBackground
        uiBackground2 = newColors.uiBackground2
        uiBackground3 = newColors.uiBackground3

        cardBackground = newColors.cardBackground
        textPrimary = newColors.textPrimary
        textSecondary = newColors.textSecondary

        outline = newColors.outline
        outlineDarker = newColors.outlineDarker

        buttonLight = newColors.buttonLight
        buttonDisabled = newColors.buttonDisabled
        textDisabled = newColors.textDisabled
        textHint = newColors.textHint
        inputBackground = newColors.inputBackground
        error = newColors.error

        isDark = newColors.isDark
    }
}

object CheckTheme {
    val colors: CheckColors
        @Composable
        get() = LocalHubtelColors.current

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography
}

@Composable
internal fun ProvideHubtelColors(
    colors: CheckColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalHubtelColors provides colorPalette, content = content)
}

private val LocalHubtelColors = staticCompositionLocalOf<CheckColors> {
    error("No HubtelColorPalette provided")
}

private fun debugColor(
    darkTheme: Boolean,
    debugColor: Color = TealPrimary
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)