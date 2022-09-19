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
package dev.petedoyle.commerce.ui.cart.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import dev.petedoyle.commerce.R
import dev.petedoyle.commerce.cart.CartState
import dev.petedoyle.commerce.cart.shipping
import dev.petedoyle.commerce.cart.subtotal
import dev.petedoyle.commerce.cart.taxes
import dev.petedoyle.commerce.cart.total
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun CartSummary(
    state: CartState,
    loading: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CartSummaryRow(
            loading = loading,
            label = "Subtotal",
            value = stringResource(R.string.price_format, state.subtotal()),
            style = FractalTheme.typography.body3,
        )

        CartSummaryRow(
            loading = loading,
            label = "Shipping",
            value = stringResource(R.string.price_format, state.shipping()),
            style = FractalTheme.typography.body3,
        )

        CartSummaryRow(
            loading = loading,
            label = "Taxes",
            value = stringResource(R.string.price_format, state.taxes()),
            style = FractalTheme.typography.body3,
        )

        CartSummaryRow(
            loading = loading,
            label = "Total",
            value = stringResource(R.string.price_format, state.total()),
            style = FractalTheme.typography.label2,
        )
    }
}

@Composable
private fun CartSummaryRow(
    loading: Boolean,
    label: String,
    value: String,
    style: TextStyle,
) {
    Row(Modifier.padding(top = FractalTheme.spacing.m)) {
        Text(
            text = label,
            color = FractalTheme.colors.onBackground,
            style = style,
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = value,
            color = FractalTheme.colors.onBackground,
            style = style,
            modifier = Modifier.placeholder(
                visible = loading,
                highlight = PlaceholderHighlight.shimmer(),
            ),
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    FractalTheme {
        CartSummary(
            state = CartState(),
            loading = false,
        )
    }
}
