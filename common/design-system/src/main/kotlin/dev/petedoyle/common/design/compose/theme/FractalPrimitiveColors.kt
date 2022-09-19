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

// /////////////////////////////////////////////////////////////////////////
// GENERATED. Do not edit by hand.
// /////////////////////////////////////////////////////////////////////////

val LocalFractalPrimitiveColors = staticCompositionLocalOf<FractalPrimitiveColors> { FractalLightPrimitiveColors() }

@Stable
interface FractalPrimitiveColors {
    val blue050: Color
    val blue100: Color
    val blue200: Color
    val blue300: Color
    val blue400: Color
    val blue500: Color
    val blue600: Color
    val blue700: Color
    val blue800: Color
    val gray0: Color
    val gray050: Color
    val gray100: Color
    val gray1000: Color
    val gray150: Color
    val gray200: Color
    val gray300: Color
    val gray400: Color
    val gray500: Color
    val gray600: Color
    val gray700: Color
    val gray800: Color
    val gray900: Color
    val green050: Color
    val green100: Color
    val green200: Color
    val green300: Color
    val green400: Color
    val green500: Color
    val green600: Color
    val green700: Color
    val green800: Color
    val pink050: Color
    val pink100: Color
    val pink200: Color
    val pink300: Color
    val pink400: Color
    val pink500: Color
    val pink600: Color
    val pink700: Color
    val pink800: Color
    val red050: Color
    val red100: Color
    val red200: Color
    val red300: Color
    val red400: Color
    val red500: Color
    val red600: Color
    val red700: Color
    val red800: Color
    val yellow050: Color
    val yellow100: Color
    val yellow200: Color
    val yellow300: Color
    val yellow400: Color
    val yellow500: Color
    val yellow600: Color
    val yellow700: Color
    val yellow800: Color
}

@Stable
class FractalLightPrimitiveColors : FractalPrimitiveColors {
    override val blue050 by mutableStateOf(Color(0xffe8f2fe))
    override val blue100 by mutableStateOf(Color(0xffb7d6fa))
    override val blue200 by mutableStateOf(Color(0xff9ec2f8))
    override val blue300 by mutableStateOf(Color(0xff64a6f5))
    override val blue400 by mutableStateOf(Color(0xff4695f3))
    override val blue500 by mutableStateOf(Color(0xff187af0))
    override val blue600 by mutableStateOf(Color(0xff166fda))
    override val blue700 by mutableStateOf(Color(0xff1157aa))
    override val blue800 by mutableStateOf(Color(0xff0d4384))
    override val gray0 by mutableStateOf(Color(0xffffffff))
    override val gray050 by mutableStateOf(Color(0xfff6f6f6))
    override val gray100 by mutableStateOf(Color(0xffe2e2e2))
    override val gray1000 by mutableStateOf(Color(0xff000000))
    override val gray150 by mutableStateOf(Color(0xffd4d4d4))
    override val gray200 by mutableStateOf(Color(0xffc6c6c6))
    override val gray300 by mutableStateOf(Color(0xffa9a9a9))
    override val gray400 by mutableStateOf(Color(0xff8d8d8d))
    override val gray500 by mutableStateOf(Color(0xff707070))
    override val gray600 by mutableStateOf(Color(0xff5a5a5a))
    override val gray700 by mutableStateOf(Color(0xff444444))
    override val gray800 by mutableStateOf(Color(0xff2d2d2d))
    override val gray900 by mutableStateOf(Color(0xff161616))
    override val green050 by mutableStateOf(Color(0xffe7f6f1))
    override val green100 by mutableStateOf(Color(0xffb5e2d3))
    override val green200 by mutableStateOf(Color(0xff91d4be))
    override val green300 by mutableStateOf(Color(0xff5fc0a0))
    override val green400 by mutableStateOf(Color(0xff40b48d))
    override val green500 by mutableStateOf(Color(0xff10a171))
    override val green600 by mutableStateOf(Color(0xff0f9367))
    override val green700 by mutableStateOf(Color(0xff0b7250))
    override val green800 by mutableStateOf(Color(0xff09593e))
    override val pink050 by mutableStateOf(Color(0xfffff6f8))
    override val pink100 by mutableStateOf(Color(0xfffee2e8))
    override val pink200 by mutableStateOf(Color(0xfffdd4dd))
    override val pink300 by mutableStateOf(Color(0xfffcc1cd))
    override val pink400 by mutableStateOf(Color(0xfffcb5c3))
    override val pink500 by mutableStateOf(Color(0xfffba2b4))
    override val pink600 by mutableStateOf(Color(0xffe493a4))
    override val pink700 by mutableStateOf(Color(0xffb27380))
    override val pink800 by mutableStateOf(Color(0xff8a5963))
    override val red050 by mutableStateOf(Color(0xfffbeae9))
    override val red100 by mutableStateOf(Color(0xfff3bfbc))
    override val red200 by mutableStateOf(Color(0xffeea09c))
    override val red300 by mutableStateOf(Color(0xffe6756e))
    override val red400 by mutableStateOf(Color(0xffe15a52))
    override val red500 by mutableStateOf(Color(0xffd93127))
    override val red600 by mutableStateOf(Color(0xffc52d23))
    override val red700 by mutableStateOf(Color(0xff9a231c))
    override val red800 by mutableStateOf(Color(0xff771b15))
    override val yellow050 by mutableStateOf(Color(0xfffff9e8))
    override val yellow100 by mutableStateOf(Color(0xffffebb7))
    override val yellow200 by mutableStateOf(Color(0xffffe194))
    override val yellow300 by mutableStateOf(Color(0xffffd364))
    override val yellow400 by mutableStateOf(Color(0xffffcb45))
    override val yellow500 by mutableStateOf(Color(0xffffbe17))
    override val yellow600 by mutableStateOf(Color(0xffe8ad15))
    override val yellow700 by mutableStateOf(Color(0xffb58710))
    override val yellow800 by mutableStateOf(Color(0xff8c690d))
}

@Stable
class FractalDarkPrimitiveColors : FractalPrimitiveColors {
    override val blue050 by mutableStateOf(Color(0xff275286))
    override val blue100 by mutableStateOf(Color(0xff326aad))
    override val blue200 by mutableStateOf(Color(0xff4088dd))
    override val blue300 by mutableStateOf(Color(0xff4695f3))
    override val blue400 by mutableStateOf(Color(0xff6baaf5))
    override val blue500 by mutableStateOf(Color(0xff83b8f7))
    override val blue600 by mutableStateOf(Color(0xffaacef9))
    override val blue700 by mutableStateOf(Color(0xffc6defb))
    override val blue800 by mutableStateOf(Color(0xffedf4fe))
    override val gray0 by mutableStateOf(Color(0xff161616))
    override val gray050 by mutableStateOf(Color(0xff212121))
    override val gray100 by mutableStateOf(Color(0xff404040))
    override val gray1000 by mutableStateOf(Color(0xffffffff))
    override val gray150 by mutableStateOf(Color(0xff525252))
    override val gray200 by mutableStateOf(Color(0xff616161))
    override val gray300 by mutableStateOf(Color(0xff787878))
    override val gray400 by mutableStateOf(Color(0xff949494))
    override val gray500 by mutableStateOf(Color(0xffadadad))
    override val gray600 by mutableStateOf(Color(0xffcccccc))
    override val gray700 by mutableStateOf(Color(0xffd9d9d9))
    override val gray800 by mutableStateOf(Color(0xffe8e8e8))
    override val gray900 by mutableStateOf(Color(0xfff7f7f7))
    override val green050 by mutableStateOf(Color(0xff23634e))
    override val green100 by mutableStateOf(Color(0xff2d8064))
    override val green200 by mutableStateOf(Color(0xff3aa480))
    override val green300 by mutableStateOf(Color(0xff40b48d))
    override val green400 by mutableStateOf(Color(0xff66c3a4))
    override val green500 by mutableStateOf(Color(0xff7fcdb3))
    override val green600 by mutableStateOf(Color(0xffa7ddcb))
    override val green700 by mutableStateOf(Color(0xffc4e8dc))
    override val green800 by mutableStateOf(Color(0xffecf8f4))
    override val pink050 by mutableStateOf(Color(0xff8b646b))
    override val pink100 by mutableStateOf(Color(0xffb3818a))
    override val pink200 by mutableStateOf(Color(0xffe5a5b1))
    override val pink300 by mutableStateOf(Color(0xfffcb5c3))
    override val pink400 by mutableStateOf(Color(0xfffdc4cf))
    override val pink500 by mutableStateOf(Color(0xfffdcdd7))
    override val pink600 by mutableStateOf(Color(0xfffedde3))
    override val pink700 by mutableStateOf(Color(0xfffee8ec))
    override val pink800 by mutableStateOf(Color(0xfffff8f9))
    override val red050 by mutableStateOf(Color(0xff7c322d))
    override val red100 by mutableStateOf(Color(0xffa0403a))
    override val red200 by mutableStateOf(Color(0xffcd524b))
    override val red300 by mutableStateOf(Color(0xffe15a52))
    override val red400 by mutableStateOf(Color(0xffe77b75))
    override val red500 by mutableStateOf(Color(0xffeb908b))
    override val red600 by mutableStateOf(Color(0xfff1b3af))
    override val red700 by mutableStateOf(Color(0xfff6ccc9))
    override val red800 by mutableStateOf(Color(0xfffcefee))
    override val yellow050 by mutableStateOf(Color(0xff8c7026))
    override val yellow100 by mutableStateOf(Color(0xffb59031))
    override val yellow200 by mutableStateOf(Color(0xffe8b93f))
    override val yellow300 by mutableStateOf(Color(0xffffcb45))
    override val yellow400 by mutableStateOf(Color(0xffffd56a))
    override val yellow500 by mutableStateOf(Color(0xffffdc82))
    override val yellow600 by mutableStateOf(Color(0xffffe7a9))
    override val yellow700 by mutableStateOf(Color(0xffffefc5))
    override val yellow800 by mutableStateOf(Color(0xfffffaec))
}
