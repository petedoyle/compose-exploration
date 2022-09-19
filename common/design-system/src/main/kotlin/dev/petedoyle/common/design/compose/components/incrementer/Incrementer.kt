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
package dev.petedoyle.common.design.compose.components.incrementer

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.petedoyle.commerce.design.compose.R
import dev.petedoyle.common.design.compose.theme.FractalTheme

private val INCREMENTER_BUTTON_SIZE = 32.dp

@Composable
fun Incrementer(
    quantity: Int,
    onDecrement: (newValue: Int) -> Unit,
    onIncrement: (newValue: Int) -> Unit,
) {
    val buttonEnabledColor = FractalTheme.colors.primary
    val buttonDisabledColor = FractalTheme.colors.primaryDisabled
    val iconEnabledColor = FractalTheme.colors.onPrimary
    val iconDisabledColor = FractalTheme.colors.onPrimaryDisabled
    val buttonRadius: Dp = FractalTheme.spacing.m
    var plusEnabled = true
    var minusEnabled = quantity > 1

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.wrapContentSize(),
    ) {
        IconButton(
            onClick = {
                if (minusEnabled) {
                    onDecrement(quantity - 1)
                    minusEnabled = false
                    plusEnabled = false
                }
            },
            enabled = minusEnabled,
            modifier = Modifier.size(INCREMENTER_BUTTON_SIZE),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ico_minus_16),
                contentDescription = stringResource(R.string.decrement, quantity - 1),
                tint = animateColorAsState(if (minusEnabled) iconEnabledColor else iconDisabledColor).value,
                modifier = Modifier
                    .drawBehind {
                        val color = if (minusEnabled) buttonEnabledColor else buttonDisabledColor
                        drawCircle(color, radius = buttonRadius.toPx())
                    },
            )
        }
        Text(
            text = quantity.toString(),
            modifier = Modifier.width(40.dp),
            style = FractalTheme.typography.label2,
            color = FractalTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
        IconButton(
            onClick = {
                if (plusEnabled) {
                    onIncrement(quantity + 1)
                    plusEnabled = false
                    minusEnabled = false
                }
            },
            enabled = plusEnabled,
            modifier = Modifier
                .size(INCREMENTER_BUTTON_SIZE),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ico_plus_16),
                contentDescription = stringResource(R.string.increment, quantity + 1),
                tint = animateColorAsState(if (plusEnabled) iconEnabledColor else iconDisabledColor).value,
                modifier = Modifier.drawBehind {
                    val color = if (plusEnabled) buttonEnabledColor else buttonDisabledColor
                    drawCircle(color, radius = buttonRadius.toPx())
                },
            )
        }
    }
}

class QuantityProvider : PreviewParameterProvider<Int> {
    override val values = sequenceOf(1, 2, 99, 100, 1000)
    override val count: Int = values.count()
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xff000000,
)
@Composable
private fun Preview(@PreviewParameter(QuantityProvider::class) qty: Int) {
    FractalTheme {
        Incrementer(
            quantity = qty,
            onDecrement = {},
            onIncrement = {},
        )
    }
}
