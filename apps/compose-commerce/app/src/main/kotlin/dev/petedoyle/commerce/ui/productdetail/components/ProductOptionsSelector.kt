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
package dev.petedoyle.commerce.ui.productdetail.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.petedoyle.commerce.BC_INVENTORY_TRACKING_NONE
import dev.petedoyle.commerce.BC_INVENTORY_TRACKING_PRODUCT
import dev.petedoyle.commerce.R
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductOptionBase
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductOptionOptionValueFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductVariantFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductVariantOptionValueFull
import dev.petedoyle.common.design.compose.theme.FractalTheme

/**
 * Allows for selecting BigCommerce "product options" from a product. For example,
 * the BigCommerce demo's "Fog Linen Chambray Towel" has two "product options":
 * 1) Size (XS/S/M/L/XL/XXL/XXXL)
 * 2) Color (Silver/Black/Purple/Blue/Green/Yellow/Orange/Red/Pink), and
 *
 * Respectively, they each have "product option values" of:
 * 1) XS/S/M/L/XL/XXL/XXXL, and
 * 2) Silver/Black/Purple/Blue/Green/Yellow/Orange/Red/Pink
 *
 * Each product has many variants, each of which represent a single permutation
 * of these product options.
 *
 * Once the user has selected all their option values, a single variant will be matched
 * and [onVariantSelected] will be called. For example, a Black XL hoodie.
 */
@Composable
fun ProductOptionsSelector(
    state: ProductOptionsSelectorState,
    onVariantSelected: (variant: ProductVariantFull) -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(state) {
        val a = snapshotFlow { state.matchingVariants.value }
        a.collect { variantsMatchingSelections ->
            if (variantsMatchingSelections.size == 1) {
                onVariantSelected(variantsMatchingSelections.first())
            }
        }
    }

    Column(modifier) {
        val sortedProductOptions = state.product?.options.orEmpty().sortedBy { it.sortOrder }
        sortedProductOptions.forEach { option ->

            var selectedIndex by remember { mutableStateOf(-1) }
            ProductOptionsSelectorRow(
                option,
                state,
                selectedIndex = selectedIndex,
                onSelectionChanged = { index ->
                    selectedIndex = index

                    // Save choice in state.choices
                    option.optionValues.orEmpty().getOrNull(selectedIndex)
                        ?.let { selectionOptionValueId ->
                            val optionSortOrder = option.sortOrder
                            val optionId = option.id
                            val optionValueId = selectionOptionValueId.id

                            if (optionSortOrder != null && optionId != null && optionValueId != null) {
                                state.choices[optionSortOrder] = OptionChoice(
                                    optionId = optionId,
                                    optionValueId = optionValueId,
                                )
                            }
                        }
                },
            )
        }
    }
}

@Composable
private fun ProductOptionsSelectorRow(
    option: ProductOptionBase,
    state: ProductOptionsSelectorState,
    selectedIndex: Int,
    onSelectionChanged: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(bottom = FractalTheme.spacing.m),
    ) {
        Text(
            text = option.displayName.orEmpty(),
            style = FractalTheme.typography.heading3,
            color = FractalTheme.colors.onBackground,
            modifier = Modifier.padding(
                start = FractalTheme.spacing.m,
                end = FractalTheme.spacing.m,
                bottom = FractalTheme.spacing.xxs,
            ),
        )

        val optionValues = option.optionValues.orEmpty()
        LazyRow {
            item {
                Spacer(modifier = Modifier.width(FractalTheme.spacing.m))
            }

            items(count = optionValues.size) { index ->
                val optionValue = optionValues[index]

                ProductOptionChoice(
                    selected = index == selectedIndex,
                    optionId = option.id ?: -1,
                    optionValue = optionValue,
                    state = state,
                    onClick = { onSelectionChanged(index) },
                )
                Spacer(Modifier.width(FractalTheme.spacing.xs))
            }

            item {
                Spacer(modifier = Modifier.width(FractalTheme.spacing.xs))
            }
        }
    }
}

@Composable
private fun ProductOptionChoice(
    selected: Boolean,
    optionId: Int,
    optionValue: ProductOptionOptionValueFull,
    state: ProductOptionsSelectorState,
    onClick: () -> Unit,
) {
    val choicesFromPreviousRows = state.choices.entries.filter {
        it.value.optionId != optionId
    }.map { it.value }

    val variantsInStock = state.variants.filter { variant ->
        variant.optionValues?.map {
            OptionChoice(it.optionId ?: -1, it.id ?: -1)
        }?.containsAll(choicesFromPreviousRows) ?: false
    }.filter { variant: ProductVariantFull ->
        variant.optionValues.orEmpty().any { it.id == optionValue.id }
    }.any { (it.inventoryLevel ?: 0) > 0 }

    val inStock = (
        state.product?.inventoryTracking?.value == BC_INVENTORY_TRACKING_NONE ||
            (
                state.product?.inventoryTracking?.value == BC_INVENTORY_TRACKING_PRODUCT &&
                    (state.product?.inventoryLevel ?: 0) > 0
                ) ||
            variantsInStock
        )

    val backgroundColor = animateColorAsState(
        targetValue = when {
            !inStock -> FractalTheme.colors.primaryDisabled
            selected -> FractalTheme.colors.primary
            else -> FractalTheme.colors.onPrimary
        },
    )

    val textColor = animateColorAsState(
        targetValue = when {
            !inStock -> FractalTheme.colors.onPrimaryDisabled
            selected -> FractalTheme.colors.onPrimary
            else -> FractalTheme.colors.primary
        },
    )

    val borderColor = animateColorAsState(
        targetValue = when {
            !inStock -> FractalTheme.colors.primaryDisabled
            selected -> FractalTheme.colors.primary
            else -> FractalTheme.colors.borderPrimary
        },
    )

    Box(
        Modifier
            .clip(FractalTheme.shapes.borderRounded)
            .clickable(enabled = inStock) { onClick() }
            .background(backgroundColor.value, FractalTheme.shapes.borderRounded)
            .border(
                1.dp,
                borderColor.value,
                FractalTheme.shapes.borderRounded,
            )
            .widthIn(min = dimensionResource(R.dimen.product_options_selector_button_min_width))
            .heightIn(min = dimensionResource(R.dimen.product_options_selector_button_min_height))
            .padding(
                horizontal = FractalTheme.spacing.m,
                vertical = FractalTheme.spacing.xs,
            ),
    ) {
        Text(
            text = optionValue.label,
            modifier = Modifier.align(Alignment.Center),
            style = FractalTheme.typography.label2,
            color = textColor.value,
        )
    }
}

data class OptionChoice(
    val optionId: Int,
    val optionValueId: Int,
)

@Stable
class ProductOptionsSelectorState {
    var product by mutableStateOf<ProductFull?>(null)
    val variants = mutableStateListOf<ProductVariantFull>()
    val choices = mutableStateMapOf<Int, OptionChoice>()

    val matchingVariants: State<List<ProductVariantFull>> = derivedStateOf {
        val sortedChoices = choices.entries.map { it.value }

        variants.filter { variant ->
            variant.optionValues?.map { optionValue ->
                OptionChoice(optionValue.optionId ?: -1, optionValue.id ?: -1)
            }?.containsAll(sortedChoices) ?: false
        }
    }
}

@Composable
fun rememberProductOptionsSelectorState(
    product: ProductFull? = null,
    variants: List<ProductVariantFull> = listOf(),
): ProductOptionsSelectorState {
    return remember {
        ProductOptionsSelectorState().also {
            it.product = product
            it.variants.clear()
            it.variants.addAll(variants)
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun Preview() {
    val productId = 77
    FractalTheme {
        val state = rememberProductOptionsSelectorState(
            product = ProductFull(
                id = productId,
                name = "Fog Linen Chambray Towel - Beige Stripe",
                type = ProductFull.Type.physical,
                weight = 1.0f,
                price = 49.00f,
                options = listOf(
                    ProductOptionBase(
                        id = 108,
                        productId = productId,
                        displayName = "Size",
                        sortOrder = 0,
                        optionValues = listOf(
                            ProductOptionOptionValueFull(
                                id = 68,
                                label = "XS",
                                sortOrder = 0,
                            ),
                            ProductOptionOptionValueFull(
                                id = 69,
                                label = "S",
                                sortOrder = 1,
                            ),
                            ProductOptionOptionValueFull(
                                id = 70,
                                label = "M",
                                sortOrder = 2,
                            ),
                            ProductOptionOptionValueFull(
                                id = 71,
                                label = "L",
                                sortOrder = 3,
                            ),
                            ProductOptionOptionValueFull(
                                id = 72,
                                label = "XL",
                                sortOrder = 4,
                            ),
                        ),
                    ),
                    ProductOptionBase(
                        id = 109,
                        productId = productId,
                        displayName = "Color",
                        optionValues = listOf(
                            ProductOptionOptionValueFull(
                                id = 7,
                                label = "Silver",
                                sortOrder = 1,
                            ),
                            ProductOptionOptionValueFull(
                                id = 8,
                                label = "Black",
                                sortOrder = 2,
                            ),
                            ProductOptionOptionValueFull(
                                id = 9,
                                label = "Purple",
                                sortOrder = 3,
                            ),
                        ),
                    ),
                ),
            ),
            variants = listOf(

                // Towel - XS - Silver
                ProductVariantFull(
                    id = 1,
                    productId = productId,
                    inventoryLevel = 517,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 68,
                            label = "XS",
                            optionId = 108,
                            optionDisplayName = "Size",
                        ),
                        ProductVariantOptionValueFull(
                            id = 7,
                            label = "Silver",
                            optionId = 109,
                            optionDisplayName = "Color",
                        ),
                    ),
                ),

                // Towel - S - Silver
                ProductVariantFull(
                    id = 2,
                    productId = productId,
                    inventoryLevel = 517,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 69,
                            label = "S",
                            optionId = 108,
                            optionDisplayName = "Size",
                        ),
                        ProductVariantOptionValueFull(
                            id = 7,
                            label = "Silver",
                            optionId = 109,
                            optionDisplayName = "Color",
                        ),
                    ),
                ),

                // Towel - Medium - Black
                ProductVariantFull(
                    id = 8,
                    productId = productId,
                    inventoryLevel = 517,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 70,
                            label = "M",
                            optionId = 108,
                            optionDisplayName = "Size",
                        ),
                        ProductVariantOptionValueFull(
                            id = 8,
                            label = "Black",
                            optionId = 109,
                            optionDisplayName = "Color",
                        ),
                    ),
                ),

                // Towel - Large - Black
                ProductVariantFull(
                    id = 9,
                    productId = productId,
                    inventoryLevel = 517,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 71,
                            label = "Black",
                            optionId = 108,
                            optionDisplayName = "Color",
                        ),
                        ProductVariantOptionValueFull(
                            id = 8,
                            label = "L",
                            optionId = 109,
                            optionDisplayName = "Size",
                        ),
                    ),
                ),

                // Towel - Purple - Small
                ProductVariantFull(
                    id = 12,
                    productId = productId,
                    inventoryLevel = 332,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 69,
                            label = "S",
                            optionId = 108,
                            optionDisplayName = "Size",
                        ),
                        ProductVariantOptionValueFull(
                            id = 9,
                            label = "Purple",
                            optionId = 109,
                            optionDisplayName = "Color",
                        ),
                    ),
                ),

                // Towel - Purple - Medium
                ProductVariantFull(
                    id = 13,
                    productId = productId,
                    inventoryLevel = 62,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 70,
                            label = "M",
                            optionId = 108,
                            optionDisplayName = "Size",
                        ),
                        ProductVariantOptionValueFull(
                            id = 9,
                            label = "Purple",
                            optionId = 109,
                            optionDisplayName = "Color",
                        ),
                    ),
                ),

                // Towel - Purple - Large - Out of stock
                ProductVariantFull(
                    id = 14,
                    productId = productId,
                    inventoryLevel = 0,
                    optionValues = listOf(
                        ProductVariantOptionValueFull(
                            id = 71,
                            label = "L",
                            optionId = 108,
                            optionDisplayName = "Size",
                        ),
                        ProductVariantOptionValueFull(
                            id = 9,
                            label = "Purple",
                            optionId = 109,
                            optionDisplayName = "Color",
                        ),
                    ),
                ),
            ),
        )
        ProductOptionsSelector(state, {})
    }
}
