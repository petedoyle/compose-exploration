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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.petedoyle.commerce.design.compose.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun FractalCheckoutButton(
    modifier: Modifier = Modifier,
    sizeVariant: FractalButtonSizeVariant = FractalButtonSizeVariant.Default(),
    styleVariant: FractalButtonStyleVariant = FractalButtonStyleVariant.Primary(),
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    loading: Boolean = false,
) {
    FractalButton(
        text = stringResource(id = R.string.checkout_now),
        modifier = modifier,
        sizeVariant = sizeVariant,
        styleVariant = styleVariant,
        iconRes = R.drawable.ico_lock_24,
        onClick = onClick,
        enabled = enabled,
        loading = loading,
    )
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF000000,
)
@Composable
private fun Preview() {
    FractalTheme {
        FractalCheckoutButton(onClick = {})
    }
}
