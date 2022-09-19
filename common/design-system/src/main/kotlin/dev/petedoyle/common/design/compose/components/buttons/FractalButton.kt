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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun FractalButton(
    text: String,
    modifier: Modifier = Modifier,
    sizeVariant: FractalButtonSizeVariant = FractalButtonSizeVariant.Default(),
    styleVariant: FractalButtonStyleVariant = FractalButtonStyleVariant.Primary(),
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    loading: Boolean = false,
    @DrawableRes iconRes: Int = 0,
) {
    var buttonModifier = modifier.defaultMinSize(minHeight = sizeVariant.minHeight)

    val outlineColor = styleVariant.outlineColor
    if (outlineColor != null) {
        buttonModifier = buttonModifier.border(1.dp, outlineColor, FractalTheme.shapes.borderRounded)
    }

    val buttonColors = when (styleVariant.outlineColor) {
        null -> ButtonDefaults.buttonColors(
            backgroundColor = styleVariant.primaryColor,
            contentColor = styleVariant.contentColor,
            disabledBackgroundColor = styleVariant.primaryColorDisabled,
            disabledContentColor = styleVariant.contentColorDisabled,
        )
        else -> ButtonDefaults.outlinedButtonColors(
            backgroundColor = styleVariant.primaryColor,
            contentColor = styleVariant.contentColor,
            disabledContentColor = styleVariant.contentColorDisabled,
        )
    }

    Button(
        modifier = buttonModifier,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
        onClick = { onClick() },
        enabled = enabled,
        colors = buttonColors,
        shape = FractalTheme.shapes.borderRounded,
    ) {
        Box {
            if (loading) {
                CircularProgressIndicator(
                    color = styleVariant.contentColor,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .size(sizeVariant.loaderSize)
                        .align(Alignment.Center),
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.alpha(if (loading) 0f else 1f),
            ) {
                if (iconRes > 0) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        tint = styleVariant.contentColor,
                        modifier = Modifier.padding(end = sizeVariant.iconSpacing),
                    )
                }
                Text(
                    text = text,
                    color = styleVariant.contentColor,
                    style = sizeVariant.textStyle,
                )
            }
        }
    }
}
