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
import androidx.compose.ui.graphics.Color
import dev.petedoyle.common.design.compose.theme.FractalTheme

sealed class FractalButtonStyleVariant {
    @get:Composable
    abstract val primaryColor: Color

    @get:Composable
    abstract val primaryColorDisabled: Color

    @get:Composable
    abstract val contentColor: Color

    @get:Composable
    abstract val contentColorDisabled: Color

    @get:Composable
    abstract val outlineColor: Color?

    @get:Composable
    abstract val outlineColorDisabled: Color?

    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = System.identityHashCode(this)

    class Primary : FractalButtonStyleVariant() {
        override val primaryColor: Color
            @Composable
            get() = FractalTheme.colors.primary

        override val primaryColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.primaryDisabled

        override val contentColor: Color
            @Composable
            get() = FractalTheme.colors.onPrimary

        override val contentColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.onPrimaryDisabled

        override val outlineColor: Color?
            @Composable
            get() = null

        override val outlineColorDisabled: Color?
            @Composable
            get() = null
    }

    class Secondary : FractalButtonStyleVariant() {
        override val primaryColor: Color
            @Composable
            get() = FractalTheme.colors.secondary

        override val primaryColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.secondaryDisabled

        override val contentColor: Color
            @Composable
            get() = FractalTheme.colors.onSecondary

        override val contentColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.onSecondaryDisabled

        override val outlineColor: Color?
            @Composable
            get() = FractalTheme.colors.borderSecondary

        override val outlineColorDisabled: Color?
            @Composable
            get() = FractalTheme.colors.borderPrimaryDisabled
    }

    class DestructivePrimary : FractalButtonStyleVariant() {
        override val primaryColor: Color
            @Composable
            get() = FractalTheme.colors.errorPrimary

        override val primaryColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.errorPrimaryDisabled

        override val contentColor: Color
            @Composable
            get() = FractalTheme.colors.onErrorPrimary

        override val contentColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.onErrorDisabled

        override val outlineColor: Color?
            @Composable
            get() = null

        override val outlineColorDisabled: Color?
            @Composable
            get() = null
    }

    class DestructiveSecondary : FractalButtonStyleVariant() {
        override val primaryColor: Color
            @Composable
            get() = FractalTheme.colors.errorSecondary

        override val primaryColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.primaryDisabled

        override val contentColor: Color
            @Composable
            get() = FractalTheme.colors.onErrorSecondary

        override val contentColorDisabled: Color
            @Composable
            get() = FractalTheme.colors.onErrorSecondary

        override val outlineColor: Color?
            @Composable
            get() = FractalTheme.colors.borderError

        override val outlineColorDisabled: Color?
            @Composable
            get() = FractalTheme.colors.borderErrorDisabled
    }
}
