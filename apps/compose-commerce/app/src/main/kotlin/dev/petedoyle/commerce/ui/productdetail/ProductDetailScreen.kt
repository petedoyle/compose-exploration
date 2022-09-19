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
package dev.petedoyle.commerce.ui.productdetail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.petedoyle.commerce.BC_INVENTORY_TRACKING_NONE
import dev.petedoyle.commerce.BC_INVENTORY_TRACKING_PRODUCT
import dev.petedoyle.commerce.R
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductImageFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductVariantFull
import dev.petedoyle.commerce.ui.Screen
import dev.petedoyle.commerce.ui.components.CommerceTopAppBarSecondary
import dev.petedoyle.commerce.ui.productdetail.components.ProductOptionsSelector
import dev.petedoyle.commerce.ui.productdetail.components.rememberProductOptionsSelectorState
import dev.petedoyle.common.design.compose.components.buttons.FractalButton
import dev.petedoyle.common.design.compose.components.buttons.FractalButtonStyleVariant
import dev.petedoyle.common.design.compose.components.inlinebanner.InlineBanner
import dev.petedoyle.common.design.compose.components.inlinebanner.InlineBannerVariant
import dev.petedoyle.common.design.compose.theme.ASPECT_RATIO_SQUARE
import dev.petedoyle.common.design.compose.theme.FractalTheme
import dev.petedoyle.common.design.compose.theme.IMAGE_CROSSFADE_MS

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailScreenViewModel,
    navController: NavHostController,
) {
    val uiState = viewModel.stateFlow.collectAsState().value

    // Apply edge-to-edge
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
        )
    }

    // Handle ViewModelEffects
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                NavigateBack -> navController.popBackStack()
                NavigateToCart -> {
                    navController.popBackStack()
                    navController.navigate(Screen.Cart.route)
                }
                is LaunchCheckoutEffect -> {
                }
                NetworkError -> Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    ProductDetailScreen(
        uiState = uiState,
        onBackClicked = { viewModel.onAction(BackClicked) },
        onShoppingCartClicked = { viewModel.onAction(ShoppingCartClicked) },
        onProductVariantSelected = { viewModel.onAction(ProductVariantSelected(it)) },
        onAddToCartClicked = { viewModel.onAction(AddToCartClicked) },
    )
}

@Composable
fun ProductDetailScreen(
    uiState: ProductDetailScreenState,
    onBackClicked: () -> Unit,
    onShoppingCartClicked: () -> Unit,
    onProductVariantSelected: (variant: ProductVariantFull) -> Unit,
    onAddToCartClicked: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    val product = uiState.product
    val variants = uiState.variants
    val categories = uiState.categories
    val productImageUrl =
        when (uiState.selectedVariant == null || uiState.selectedVariant.imageUrl.isNullOrBlank()) {
            true -> product?.images?.getOrNull(0)?.urlZoom
            else -> uiState.selectedVariant.imageUrl
        }

    val productPrice: Float = when (uiState.selectedVariant != null) {
        true -> uiState.selectedVariant.calculatedPrice?.toFloat() ?: 0.0f
        else -> product?.calculatedPrice ?: 0.0f
    }

    Scaffold(
        topBar = {
            CommerceTopAppBarSecondary(
                title = product?.name.orEmpty(),
                toolbarShoppingCartBadgeEnabled = uiState.cartState.items.isNotEmpty(),
                onBackClicked = { onBackClicked() },
                onShoppingCartClicked = { onShoppingCartClicked() },
            )
        },
        scaffoldState = scaffoldState,
        backgroundColor = FractalTheme.colors.backgroundPrimary,
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
                    .background(FractalTheme.colors.backgroundPrimary)
                    .padding(bottom = FractalTheme.spacing.xs),
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(productImageUrl)
                        .scale(Scale.FILL)
                        .crossfade(IMAGE_CROSSFADE_MS)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(ASPECT_RATIO_SQUARE),
                )
                Text(
                    text = categories
                        .filter { product?.categories.orEmpty().contains(it.id) }
                        .joinToString { it.name },
                    color = FractalTheme.colors.onBackgroundVariant2,
                    style = FractalTheme.typography.label3,
                    modifier = Modifier
                        .padding(
                            top = FractalTheme.spacing.m,
                            start = FractalTheme.spacing.m,
                            end = FractalTheme.spacing.m,
                        )
                        .placeholder(
                            visible = uiState.loading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    text = product?.name.orEmpty(),
                    color = FractalTheme.colors.onBackground,
                    style = FractalTheme.typography.display3,
                    modifier = Modifier
                        .padding(horizontal = FractalTheme.spacing.m)
                        .placeholder(
                            visible = uiState.loading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
                )

                Text(
                    text = stringResource(R.string.price_format, productPrice),
                    color = FractalTheme.colors.onBackground,
                    style = FractalTheme.typography.heading3,
                    modifier = Modifier
                        .padding(horizontal = FractalTheme.spacing.m)
                        .placeholder(
                            visible = uiState.loading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
                )

                Text(
                    text = HtmlCompat.fromHtml(
                        product?.description.orEmpty(),
                        HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM,
                    ).toString().trim(),
                    color = FractalTheme.colors.onBackgroundVariant1,
                    style = FractalTheme.typography.body3,
                    modifier = Modifier
                        .padding(
                            top = FractalTheme.spacing.m,
                            start = FractalTheme.spacing.m,
                            end = FractalTheme.spacing.m,
                        )
                        .placeholder(
                            visible = uiState.loading,
                            highlight = PlaceholderHighlight.shimmer(),
                        ),
                )

                val inStock = product?.inventoryTracking?.value == BC_INVENTORY_TRACKING_NONE ||
                    (product?.inventoryTracking?.value == BC_INVENTORY_TRACKING_PRODUCT && product.inventoryLevel ?: 0 > 0) ||
                    variants.any { it.inventoryLevel ?: 0 > 0 }

                if (!inStock) {
                    OutOfStockBanner()
                }

                if (product != null && uiState.variants.isNotEmpty() && inStock) {
                    val state = rememberProductOptionsSelectorState(
                        product = uiState.product,
                        variants = uiState.variants,
                    )

                    ProductOptionsSelector(
                        state,
                        onVariantSelected = { onProductVariantSelected(it) },
                        Modifier.padding(top = FractalTheme.spacing.m),
                    )
                }
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(FractalTheme.spacing.m)
                    .background(FractalTheme.colors.backgroundSecondary),
            )

            FractalButton(
                text = stringResource(R.string.add_to_cart),
                styleVariant = FractalButtonStyleVariant.Primary(),
                iconRes = dev.petedoyle.commerce.design.compose.R.drawable.ico_cart_24,
                onClick = onAddToCartClicked,
                enabled = uiState.selectedVariant != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(FractalTheme.spacing.m),
            )

            Spacer(Modifier.navigationBarsPadding())
        }
    }
}

@Composable
private fun OutOfStockBanner() {
    InlineBanner(
        text = stringResource(R.string.out_of_stock),
        variant = InlineBannerVariant.Error,
        modifier = Modifier.padding(
            horizontal = FractalTheme.spacing.m,
            vertical = FractalTheme.spacing.l,
        ),
    )
}

@Preview
@Composable
fun PreviewProductDetailScreen() {
    FractalTheme {
        ProductDetailScreen(
            uiState = ProductDetailScreenState(
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
            ),
            onBackClicked = {},
            onShoppingCartClicked = {},
            onProductVariantSelected = {},
            onAddToCartClicked = {},
        )
    }
}
