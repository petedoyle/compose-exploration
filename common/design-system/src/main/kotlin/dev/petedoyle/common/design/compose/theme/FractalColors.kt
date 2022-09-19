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

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalFractalColors = staticCompositionLocalOf<FractalColors> { FractalLightColors(FractalLightPrimitiveColors()) }

@Stable
interface FractalColors {
    val accent: Color
    val accentActive: Color
    val accentDisabled: Color
    val accentHover: Color
    val backgroundPrimary: Color
    val backgroundSecondary: Color
    val badgeBlue: Color
    val onBadgeBlue: Color
    val badgeGray: Color
    val onBadgeGray: Color
    val badgeGreen: Color
    val onBadgeGreen: Color
    val badgeRed: Color
    val onBadgeRed: Color
    val badgeYellow: Color
    val onBadgeYellow: Color
    val borderError: Color
    val borderErrorActive: Color
    val borderErrorDisabled: Color
    val borderErrorHover: Color
    val borderPrimary: Color
    val borderPrimaryActive: Color
    val borderPrimaryDisabled: Color
    val borderPrimaryHover: Color
    val borderSecondary: Color
    val borderSuccess: Color
    val borderWarning: Color
    val errorPrimary: Color
    val errorPrimaryActive: Color
    val errorPrimaryDisabled: Color
    val errorPrimaryHover: Color
    val errorSecondary: Color
    val errorSecondaryActive: Color
    val errorSecondaryDisabled: Color
    val errorSecondaryHover: Color
    val onErrorDisabled: Color
    val onErrorPrimary: Color
    val onErrorSecondary: Color
    val onBackground: Color
    val onBackgroundError: Color
    val onBackgroundPrimaryDisabled: Color
    val onBackgroundSecondaryDisabled: Color
    val onBackgroundSuccess: Color
    val onBackgroundVariant1: Color
    val onBackgroundVariant2: Color
    val onBackgroundWarning: Color
    val onPrimary: Color
    val onPrimaryDisabled: Color
    val onSecondary: Color
    val onSecondaryDisabled: Color
    val onSurface: Color
    val onSurfaceDisabled: Color
    val onSurfaceError: Color
    val onSurfaceSuccess: Color
    val onSurfaceVariant1: Color
    val onSurfaceVariant2: Color
    val onSurfaceWarning: Color
    val outlinePrimary: Color
    val outlineQuaternary: Color
    val outlineSecondary: Color
    val outlineTertiary: Color
    val primary: Color
    val primaryActive: Color
    val primaryDisabled: Color
    val primaryHover: Color
    val secondary: Color
    val secondaryActive: Color
    val secondaryDisabled: Color
    val secondaryHover: Color
    val onSuccessPrimary: Color
    val successPrimary: Color
    val surface: Color
    val surfaceActive: Color
    val surfaceHover: Color
    val onWarningPrimary: Color
    val warningPrimary: Color
}

@Stable
class FractalLightColors(p: FractalPrimitiveColors) : FractalColors {
    override val accent = p.blue400
    override val accentActive = p.blue500
    override val accentDisabled = p.blue200
    override val accentHover = p.blue600
    override val backgroundPrimary = p.gray0
    override val backgroundSecondary = p.gray050
    override val badgeBlue = p.blue100
    override val onBadgeBlue = p.blue800
    override val badgeGray = p.gray100
    override val onBadgeGray = p.gray1000
    override val badgeGreen = p.green100
    override val onBadgeGreen = p.green800
    override val badgeRed = p.red100
    override val onBadgeRed = p.red800
    override val badgeYellow = p.yellow100
    override val onBadgeYellow = p.yellow800
    override val borderError = p.red500
    override val borderErrorActive = p.red600
    override val borderErrorDisabled = p.red100
    override val borderErrorHover = p.red700
    override val borderPrimary = p.gray150
    override val borderPrimaryActive = p.blue500
    override val borderPrimaryDisabled = p.gray100
    override val borderPrimaryHover = p.gray500
    override val borderSecondary = p.gray1000
    override val borderSuccess = p.green500
    override val borderWarning = p.yellow500
    override val errorPrimary = p.red500
    override val errorPrimaryActive = p.red600
    override val errorPrimaryDisabled = p.red050
    override val errorPrimaryHover = p.red400
    override val errorSecondary by mutableStateOf(Color.Transparent)
    override val errorSecondaryActive = p.red100
    override val errorSecondaryDisabled by mutableStateOf(Color.Transparent)
    override val errorSecondaryHover = p.red050
    override val onErrorDisabled = p.red100
    override val onErrorPrimary = p.gray0
    override val onErrorSecondary = p.red500
    override val onBackground = p.gray1000
    override val onBackgroundError = p.red600
    override val onBackgroundPrimaryDisabled = p.gray150
    override val onBackgroundSecondaryDisabled = p.gray300
    override val onBackgroundSuccess = p.green600
    override val onBackgroundVariant1 = p.gray700
    override val onBackgroundVariant2 = p.gray500
    override val onBackgroundWarning = p.yellow800
    override val onPrimary = p.gray0
    override val onPrimaryDisabled = p.gray300
    override val onSecondary = p.gray1000
    override val onSecondaryDisabled = p.gray200
    override val onSurface = p.gray1000
    override val onSurfaceDisabled = p.gray150
    override val onSurfaceError = p.red600
    override val onSurfaceSuccess = p.green600
    override val onSurfaceVariant1 = p.gray700
    override val onSurfaceVariant2 = p.gray500
    override val onSurfaceWarning = p.yellow800
    override val outlinePrimary = p.gray1000
    override val outlineQuaternary = p.red100
    override val outlineSecondary = p.gray0
    override val outlineTertiary = p.blue100
    override val primary = p.gray1000
    override val primaryActive = p.gray900
    override val primaryDisabled = p.gray100
    override val primaryHover = p.gray800
    override val secondary by mutableStateOf(Color.Transparent)
    override val secondaryActive = p.gray150
    override val secondaryDisabled by mutableStateOf(Color.Transparent)
    override val secondaryHover = p.gray100
    override val onSuccessPrimary = p.gray0
    override val successPrimary = p.green500
    override val surface = p.gray0
    override val surfaceActive = p.gray100
    override val surfaceHover = p.gray050
    override val onWarningPrimary by mutableStateOf(Color.Black) // TODO staticBlack
    override val warningPrimary = p.yellow500
}

@Stable
class FractalDarkColors(p: FractalPrimitiveColors) : FractalColors {
    override val accent = p.blue400
    override val accentActive = p.blue500
    override val accentDisabled = p.blue200
    override val accentHover = p.blue600
    override val backgroundPrimary = p.gray0
    override val backgroundSecondary = p.gray050
    override val badgeBlue = p.blue100
    override val onBadgeBlue = p.blue800
    override val badgeGray = p.gray100
    override val onBadgeGray = p.gray1000
    override val badgeGreen = p.green100
    override val onBadgeGreen = p.green800
    override val badgeRed = p.red100
    override val onBadgeRed = p.red800
    override val badgeYellow = p.yellow100
    override val onBadgeYellow = p.yellow800
    override val borderError = p.red500
    override val borderErrorActive = p.red600
    override val borderErrorDisabled = p.red100
    override val borderErrorHover = p.red700
    override val borderPrimary = p.gray150
    override val borderPrimaryActive = p.blue500
    override val borderPrimaryDisabled = p.gray100
    override val borderPrimaryHover = p.gray500
    override val borderSecondary = p.gray1000
    override val borderSuccess = p.green500
    override val borderWarning = p.yellow500
    override val errorPrimary = p.red500
    override val errorPrimaryActive = p.red600
    override val errorPrimaryDisabled = p.red050
    override val errorPrimaryHover = p.red400
    override val errorSecondary by mutableStateOf(Color.Transparent)
    override val errorSecondaryActive = p.red100
    override val errorSecondaryDisabled by mutableStateOf(Color.Transparent)
    override val errorSecondaryHover = p.red050
    override val onErrorDisabled = p.red100
    override val onErrorPrimary = p.gray0
    override val onErrorSecondary = p.red500
    override val onBackground = p.gray1000
    override val onBackgroundError = p.red600
    override val onBackgroundPrimaryDisabled = p.gray150
    override val onBackgroundSecondaryDisabled = p.gray300
    override val onBackgroundSuccess = p.green600
    override val onBackgroundVariant1 = p.gray700
    override val onBackgroundVariant2 = p.gray500
    override val onBackgroundWarning = p.yellow800
    override val onPrimary = p.gray0
    override val onPrimaryDisabled = p.gray300
    override val onSecondary = p.gray1000
    override val onSecondaryDisabled = p.gray200
    override val onSurface = p.gray1000
    override val onSurfaceDisabled = p.gray300
    override val onSurfaceError = p.red600
    override val onSurfaceSuccess = p.green600
    override val onSurfaceVariant1 = p.gray700
    override val onSurfaceVariant2 = p.gray500
    override val onSurfaceWarning = p.yellow800
    override val outlinePrimary = p.gray1000
    override val outlineQuaternary = p.red100
    override val outlineSecondary = p.gray0
    override val outlineTertiary = p.blue100
    override val primary = p.gray1000
    override val primaryActive = p.gray900
    override val primaryDisabled = p.gray100
    override val primaryHover = p.gray800
    override val secondary by mutableStateOf(Color.Transparent)
    override val secondaryActive = p.gray150
    override val secondaryDisabled by mutableStateOf(Color.Transparent)
    override val secondaryHover = p.gray100
    override val onSuccessPrimary = p.gray0
    override val successPrimary = p.green500
    override val surface = p.gray0
    override val surfaceActive = p.gray100
    override val surfaceHover = p.gray050
    override val onWarningPrimary by mutableStateOf(Color.Black) // TODO staticBlack
    override val warningPrimary = p.yellow500
}
