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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import dev.petedoyle.commerce.R
import dev.petedoyle.commerce.cart.CartLineItem
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductImageFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductVariantFull
import dev.petedoyle.common.design.compose.components.incrementer.Incrementer
import dev.petedoyle.common.design.compose.theme.FractalTheme

private val IMAGE_WIDTH = 40.dp
private const val IMAGE_ASPECT_RATIO = 3 / 4f
private val IMAGE_CORNER_RADIUS = 4.dp

@Composable
fun CartLineItemRow(
    item: CartLineItem,
    onRemoveLineItemClicked: (item: CartLineItem) -> Unit,
    onChangeLineItemQuantityClicked: (item: CartLineItem, newQuantity: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = FractalTheme.spacing.xl),
    ) {
        val thumbnailUrl = if (!item.variant.imageUrl.isNullOrBlank()) {
            item.variant.imageUrl
        } else {
            item.product.images?.firstOrNull()?.urlThumbnail
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(thumbnailUrl)
                .scale(Scale.FILL)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(
                R.string.cart_line_item_photo_content_description,
                item.product.name,
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(IMAGE_WIDTH)
                .aspectRatio(IMAGE_ASPECT_RATIO)
                .clip(RoundedCornerShape(IMAGE_CORNER_RADIUS))
                .background(FractalTheme.primitiveColors.gray050),
        )

        Column(
            Modifier
                .padding(start = FractalTheme.spacing.s)
                .weight(1f),
        ) {
            Text(
                text = item.product.name,
                color = FractalTheme.colors.onBackground,
                style = FractalTheme.typography.label2,
            )

            Row(modifier = Modifier.padding(top = FractalTheme.spacing.xxs)) {
                Text(
                    text = stringResource(
                        R.string.price_format,
                        item.variant.calculatedPrice ?: 0f,
                    ),
                    color = FractalTheme.colors.onBackgroundVariant1,
                    style = FractalTheme.typography.label4,
                )

                Text(
                    text = "•",
                    color = FractalTheme.colors.onBackgroundVariant1,
                    style = FractalTheme.typography.label4,
                    modifier = Modifier.padding(horizontal = FractalTheme.spacing.xxs),
                )

                Text(
                    text = stringResource(R.string.remove),
                    color = FractalTheme.colors.accent,
                    style = FractalTheme.typography.label4,
                    modifier = Modifier.clickable { onRemoveLineItemClicked(item) },
                )
            }

            item.variant.optionValues.orEmpty().forEach { optionValue ->
                Text(
                    text = "${optionValue.optionDisplayName}: ${optionValue.label}",
                    color = FractalTheme.colors.onBackgroundVariant1,
                    style = FractalTheme.typography.label4,
                    modifier = Modifier.padding(top = FractalTheme.spacing.xs),
                )
            }
        }

        Incrementer(
            quantity = item.quantity,
            onDecrement = { onChangeLineItemQuantityClicked(item, it) },
            onIncrement = { onChangeLineItemQuantityClicked(item, it) },
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    FractalTheme {
        CartLineItemRow(
            item = CartLineItem(
                quantity = 1,
                product = ProductFull(
                    name = "Orbit Terrarium",
                    type = ProductFull.Type.physical,
                    weight = 1.0f,
                    price = 89.00f,
                    images = listOf(
                        ProductImageFull(
                            urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/81/images/274/roundterrariumsmall2.1652641773.220.290.jpg?c=1",
                        ),
                    ),
                ),
                variant = ProductVariantFull(
                    price = 9.99,
                ),
            ),
            onRemoveLineItemClicked = {},
            onChangeLineItemQuantityClicked = { _, _ -> },
        )
    }
}
