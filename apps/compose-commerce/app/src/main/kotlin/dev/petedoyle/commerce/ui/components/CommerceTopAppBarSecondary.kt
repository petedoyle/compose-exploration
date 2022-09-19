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
package dev.petedoyle.commerce.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ui.TopAppBar
import dev.petedoyle.commerce.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun CommerceTopAppBarSecondary(
    title: String,
    toolbarShoppingCartBadgeEnabled: Boolean,
    onBackClicked: () -> Unit,
    onShoppingCartClicked: () -> Unit,
) {
    TopAppBar(
        elevation = dimensionResource(R.dimen.toolbar_elevation),
        backgroundColor = FractalTheme.colors.backgroundPrimary,
        contentPadding = WindowInsets.statusBars.asPaddingValues(),
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    painter = painterResource(id = dev.petedoyle.commerce.design.compose.R.drawable.ico_chevronleft_24),
                    contentDescription = stringResource(R.string.icon_shopping_cart_content_description),
                    tint = FractalTheme.colors.onBackground,
                )
            }
        },
        title = {
            Text(
                text = title,
                style = FractalTheme.typography.heading3,
                textAlign = TextAlign.Center,
                color = FractalTheme.colors.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = FractalTheme.spacing.m),
            )
        },
        actions = {
            IconButton(onClick = { onShoppingCartClicked() }) {
                CommerceTopAppBarShoppingCartIcon(badgeEnabled = toolbarShoppingCartBadgeEnabled)
            }
        },
    )
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    FractalTheme {
        CommerceTopAppBarSecondary(
            title = "Neck Gaiter",
            toolbarShoppingCartBadgeEnabled = true,
            onBackClicked = {},
            onShoppingCartClicked = {},
        )
    }
}
