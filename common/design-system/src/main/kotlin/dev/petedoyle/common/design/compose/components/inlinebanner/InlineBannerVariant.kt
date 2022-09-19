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
package dev.petedoyle.common.design.compose.components.inlinebanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.petedoyle.commerce.design.compose.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

sealed class InlineBannerVariant {
    @Composable
    abstract fun iconTint(): Color

    @Composable
    abstract fun iconContentDescription(): Int

    object Info : InlineBannerVariant() {
        @Composable
        override fun iconTint() = FractalTheme.colors.accent

        @Composable
        override fun iconContentDescription() = R.string.inline_banner_info_content_description
    }

    object Error : InlineBannerVariant() {
        @Composable
        override fun iconTint() = FractalTheme.colors.onBackgroundError

        @Composable
        override fun iconContentDescription() = R.string.inline_banner_error_content_description
    }

    object Success : InlineBannerVariant() {
        @Composable
        override fun iconTint() = FractalTheme.colors.onBackgroundSuccess

        @Composable
        override fun iconContentDescription() = R.string.inline_banner_success_content_description
    }
}

internal class InlineBannerVariantProvider : PreviewParameterProvider<InlineBannerVariant> {
    override val values = sequenceOf(
        InlineBannerVariant.Info,
        InlineBannerVariant.Error,
        InlineBannerVariant.Success,
    )
    override val count: Int = values.count()
}
