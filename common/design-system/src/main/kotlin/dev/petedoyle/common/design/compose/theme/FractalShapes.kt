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

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

// /////////////////////////////////////////////////////////////////////////
// GENERATED. Do not edit by hand.
// /////////////////////////////////////////////////////////////////////////

val LocalFractalShapes = staticCompositionLocalOf { FractalShapes() }

@Immutable
data class FractalShapes(
    val borderRounded: RoundedCornerShape = RoundedCornerShape(
        topStart = 6.dp,
        topEnd = 6.dp,
        bottomStart = 6.dp,
        bottomEnd = 6.dp,
    ),
    val borderCircle: RoundedCornerShape = RoundedCornerShape(
        topStart = 100.dp,
        topEnd = 100.dp,
        bottomStart = 100.dp,
        bottomEnd = 100.dp,
    ),
)
