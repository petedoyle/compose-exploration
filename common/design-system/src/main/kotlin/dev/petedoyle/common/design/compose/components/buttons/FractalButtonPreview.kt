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

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.petedoyle.commerce.design.compose.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xff000000,
)
@Composable
private fun Preview(
    @PreviewParameter(FractalButtonStyleVariantProvider::class) styleVariant: FractalButtonStyleVariant,
) {
    FractalTheme {
        FractalButtonGallery(styleVariant)
    }
}

@Composable
private fun FractalButtonGallery(styleVariant: FractalButtonStyleVariant) {
    val sizeVariants = listOf(
        FractalButtonSizeVariant.Default(),
        FractalButtonSizeVariant.Compact(),
        FractalButtonSizeVariant.Tiny(),
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(FractalTheme.spacing.xxl),
        modifier = Modifier.padding(FractalTheme.spacing.m),
    ) {
        sizeVariants.forEach { sizeVariant ->
            Column(
                verticalArrangement = Arrangement.spacedBy(FractalTheme.spacing.xs),
                modifier = Modifier.padding(FractalTheme.spacing.m),
            ) {
                Text(
                    text = "${styleVariant.javaClass.simpleName} / ${sizeVariant.javaClass.simpleName}:",
                    color = FractalTheme.colors.onBackground,
                    style = FractalTheme.typography.heading3,
                )

                // Enabled, no icon
                FractalButton(
                    text = stringResource(id = R.string.checkout_now),
                    sizeVariant = sizeVariant,
                    styleVariant = styleVariant,
                )

                // Loading, no icon
                FractalButton(
                    text = stringResource(id = R.string.checkout_now),
                    sizeVariant = sizeVariant,
                    styleVariant = styleVariant,
                    loading = true,
                )

                // Disabled
                FractalButton(
                    text = stringResource(id = R.string.checkout_now),
                    sizeVariant = sizeVariant,
                    styleVariant = styleVariant,
                    enabled = false,
                )

                // Enabled, with lock icon
                FractalButton(
                    text = stringResource(id = R.string.checkout_now),
                    sizeVariant = sizeVariant,
                    styleVariant = styleVariant,
                    iconRes = R.drawable.ico_lock_24,
                )

                // Loading, with lock icon
                FractalButton(
                    text = stringResource(id = R.string.checkout_now),
                    sizeVariant = sizeVariant,
                    styleVariant = styleVariant,
                    loading = true,
                    iconRes = R.drawable.ico_lock_24,
                )

                // Disabled, with lock icon
                FractalButton(
                    text = stringResource(id = R.string.checkout_now),
                    sizeVariant = sizeVariant,
                    styleVariant = styleVariant,
                    enabled = false,
                    iconRes = R.drawable.ico_lock_24,
                )
            }
        }
    }
}

// Must not be private, or Compose tooling will cease to render anything
class FractalButtonStyleVariantProvider : PreviewParameterProvider<FractalButtonStyleVariant> {
    override val values = sequenceOf(
        FractalButtonStyleVariant.Primary(),
        FractalButtonStyleVariant.Secondary(),
        FractalButtonStyleVariant.DestructivePrimary(),
        FractalButtonStyleVariant.DestructiveSecondary(),
    )
    override val count: Int = values.count()
}
