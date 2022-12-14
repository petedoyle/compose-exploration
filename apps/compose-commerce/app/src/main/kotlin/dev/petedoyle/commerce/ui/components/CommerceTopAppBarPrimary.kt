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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ui.TopAppBar
import dev.petedoyle.commerce.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun CommerceTopAppBarPrimary(
    shoppingCartBadgeEnabled: Boolean,
    onShoppingCartClicked: () -> Unit,
) {
    TopAppBar(
        contentPadding = WindowInsets.statusBars.asPaddingValues(),
        title = {
            Image(
                painter = painterResource(id = R.drawable.logo_top_bar),
                contentDescription = stringResource(R.string.logo_content_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 36.dp), // hack to center the logo
            )
        },
        navigationIcon = null,
        actions = {
            IconButton(
                onClick = { onShoppingCartClicked() },
            ) {
                CommerceTopAppBarShoppingCartIcon(badgeEnabled = shoppingCartBadgeEnabled)
            }
        },
        elevation = dimensionResource(R.dimen.toolbar_elevation),
        backgroundColor = FractalTheme.colors.backgroundPrimary,
    )
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    FractalTheme {
        CommerceTopAppBarPrimary(
            shoppingCartBadgeEnabled = true,
            onShoppingCartClicked = {},
        )
    }
}
