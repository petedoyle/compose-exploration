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
package dev.petedoyle.commerce.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import dev.petedoyle.commerce.R
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.Category
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductImageFull
import dev.petedoyle.common.design.compose.theme.ASPECT_RATIO_3_BY_4
import dev.petedoyle.common.design.compose.theme.FractalTheme
import dev.petedoyle.common.design.compose.theme.IMAGE_CROSSFADE_MS

private val IMAGE_CORNER_RADIUS = 8.dp

@Composable
fun FeaturedProduct(
    product: ProductFull,
    categories: List<Category>,
    onProductClicked: (productId: Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                bottom = FractalTheme.spacing.xl,
                start = FractalTheme.spacing.xs,
                end = FractalTheme.spacing.xs,
            )
            .clickable { product.id?.let { onProductClicked(it) } },
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images?.getOrNull(0)?.urlStandard)
                .scale(Scale.FILL)
                .crossfade(IMAGE_CROSSFADE_MS)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(ASPECT_RATIO_3_BY_4)
                .clip(RoundedCornerShape(IMAGE_CORNER_RADIUS))
                .background(FractalTheme.primitiveColors.gray050),
        )
        Text(
            text = categories
                .filter { product.categories.orEmpty().contains(it.id) }
                .joinToString { it.name },
            color = FractalTheme.colors.onBackgroundVariant2,
            style = FractalTheme.typography.caption2,
            modifier = Modifier.padding(top = FractalTheme.spacing.xs),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = product.name,
            color = FractalTheme.colors.onBackground,
            style = FractalTheme.typography.heading3,
        )
        Text(
            text = stringResource(R.string.price_format, product.calculatedPrice ?: 0f),
            color = FractalTheme.colors.onBackground,
            style = FractalTheme.typography.label2,
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    FractalTheme {
        FeaturedProduct(
            product = ProductFull(
                id = 86,
                name = "Able Brewing System",
                type = ProductFull.Type.physical,
                weight = 1.0f,
                calculatedPrice = 225.00f,
                price = 225.00f,
                images = listOf(
                    ProductImageFull(
                        urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/86/images/283/ablebrewingsystem1.1652641773.220.290.jpg?c=1",
                    ),
                ),
                categories = listOf(0, 1, 2),
            ),
            categories = listOf(
                Category(
                    parentId = 0,
                    id = 0,
                    name = "Shop All",
                ),
                Category(
                    parentId = 0,
                    id = 1,
                    name = "Kitchen",
                ),
            ),
            onProductClicked = {},
        )
    }
}
