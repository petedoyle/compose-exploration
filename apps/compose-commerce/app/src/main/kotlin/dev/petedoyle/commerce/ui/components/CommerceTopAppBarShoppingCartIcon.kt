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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.petedoyle.commerce.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun CommerceTopAppBarShoppingCartIcon(
    badgeEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Icon(
            painter = painterResource(id = dev.petedoyle.commerce.design.compose.R.drawable.ico_cart_24),
            contentDescription = stringResource(R.string.icon_shopping_cart_content_description),
            tint = FractalTheme.colors.onBackground,
        )

        AnimatedVisibility(
            visible = badgeEnabled,
            modifier = Modifier.align(Alignment.TopEnd),
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(FractalTheme.primitiveColors.pink500, CircleShape)
                    .border(1.dp, FractalTheme.colors.backgroundPrimary, CircleShape),
            )
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    FractalTheme {
        CommerceTopAppBarShoppingCartIcon(badgeEnabled = true)
    }
}
