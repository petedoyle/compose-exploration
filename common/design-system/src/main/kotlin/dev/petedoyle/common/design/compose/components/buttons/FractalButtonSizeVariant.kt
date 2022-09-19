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
package dev.petedoyle.common.design.compose.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.petedoyle.common.design.compose.theme.FractalTheme

sealed class FractalButtonSizeVariant(
    val minHeight: Dp,
    val loaderSize: Dp,
) {
    @get:Composable
    abstract val iconSpacing: Dp

    @get:Composable
    abstract val textStyle: TextStyle

    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = System.identityHashCode(this)

    class Default : FractalButtonSizeVariant(
        minHeight = 48.dp,
        loaderSize = 20.dp,
    ) {
        override val iconSpacing: Dp
            @Composable
            get() = FractalTheme.spacing.s

        override val textStyle: TextStyle
            @Composable
            get() = FractalTheme.typography.label2
    }

    class Compact : FractalButtonSizeVariant(
        minHeight = 36.dp,
        loaderSize = 14.dp,
    ) {
        override val iconSpacing: Dp
            @Composable
            get() = FractalTheme.spacing.xs

        override val textStyle: TextStyle
            @Composable
            get() = FractalTheme.typography.label3
    }

    class Tiny : FractalButtonSizeVariant(
        minHeight = 28.dp,
        loaderSize = 14.dp,
    ) {
        override val iconSpacing: Dp
            @Composable
            get() = FractalTheme.spacing.xs

        override val textStyle: TextStyle
            @Composable
            get() = FractalTheme.typography.label4
    }
}
