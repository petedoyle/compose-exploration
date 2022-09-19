/*
 * Copyright (C) 2022 Pete Doyle
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.petedoyle.common.design.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun FractalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val primitiveColors: FractalPrimitiveColors = if (darkTheme) {
        FractalDarkPrimitiveColors()
    } else {
        FractalLightPrimitiveColors()
    }

    val colors: FractalColors = if (darkTheme) {
        FractalDarkColors(primitiveColors)
    } else {
        FractalLightColors(primitiveColors)
    }

    val fractalTypography = defaultFractalTypography()
    CompositionLocalProvider(
        LocalFractalPrimitiveColors provides primitiveColors,
        LocalFractalColors provides colors,
        LocalFractalTypography provides fractalTypography,
        LocalFractalShapes provides FractalShapes(),
        LocalFractalSpacing provides FractalSpacing(),
    ) {
        MaterialTheme(
            colors = Colors(
                primary = colors.primary,
                primaryVariant = colors.primaryActive,
                secondary = colors.secondary,
                secondaryVariant = colors.secondaryActive,
                background = colors.backgroundPrimary,
                surface = colors.surface,
                error = colors.errorPrimary,
                onPrimary = colors.onPrimary,
                onSecondary = colors.onSecondary,
                onBackground = colors.onBackground,
                onSurface = colors.onSurface,
                onError = colors.onErrorPrimary,
                isLight = !darkTheme,
            ),
            typography = Typography(defaultFontFamily = fractalTypography.defaultFontFamily),
            content = content,
        )
    }
}

/**
 * Helper that allows for easy access of theme values. Examples:
 *
 * - `FractalTheme.colors.border_primary_borderprimary`
 * - `FractalTheme.typography.display1`
 * - `FractalTheme.shapes.borderRounded`
 * - `FractalTheme.spacing.xxl`
 */
object FractalTheme {
    val primitiveColors: FractalPrimitiveColors
        @Composable
        get() = LocalFractalPrimitiveColors.current

    val colors: FractalColors
        @Composable
        get() = LocalFractalColors.current

    val typography: FractalTypography
        @Composable
        get() = LocalFractalTypography.current

    val shapes: FractalShapes
        @Composable
        get() = LocalFractalShapes.current

    val spacing: FractalSpacing
        @Composable
        get() = LocalFractalSpacing.current
}
