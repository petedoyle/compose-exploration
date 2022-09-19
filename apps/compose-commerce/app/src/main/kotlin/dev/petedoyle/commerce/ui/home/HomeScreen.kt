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
package dev.petedoyle.commerce.ui.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import com.google.accompanist.insets.ui.Scaffold
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.Category
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductImageFull
import dev.petedoyle.commerce.ui.Screen
import dev.petedoyle.commerce.ui.components.CommerceTopAppBarPrimary
import dev.petedoyle.commerce.ui.home.components.FeaturedProduct
import dev.petedoyle.common.design.compose.theme.FractalTheme

private const val GRID_ROWS_COUNT = 2

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel, navController: NavHostController) {
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
                is NavigateToProductDetail -> navController.navigate(
                    Screen.ProductDetail.createRoute(effect.productId),
                )
                is NavigateToCart -> navController.navigate(Screen.Cart.route)
                NetworkError -> Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        onProductClicked = { viewModel.onAction(ProductClicked(productId = it)) },
        onShoppingCartClicked = { viewModel.onAction(ShoppingCartClicked) },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    uiState: HomeScreenState,
    onProductClicked: (productId: Int) -> Unit,
    onShoppingCartClicked: () -> Unit,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommerceTopAppBarPrimary(
                shoppingCartBadgeEnabled = uiState.cartState.items.isNotEmpty(),
                onShoppingCartClicked = { onShoppingCartClicked() },
            )
        },
        bottomBar = {
            Spacer(
                Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth(),
            )
        },
    ) { contentPadding ->
        Box(Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = GRID_ROWS_COUNT),
                contentPadding = PaddingValues(
                    start = contentPadding.calculateStartPadding(LayoutDirection.Ltr),
                    top = contentPadding.calculateTopPadding(),
                    end = contentPadding.calculateEndPadding(LayoutDirection.Ltr),
                ),
                modifier = Modifier.padding(
                    start = FractalTheme.spacing.xs,
                    top = FractalTheme.spacing.m,
                    end = FractalTheme.spacing.xs,
                ),
            ) {
                items(uiState.products.size) { index ->
                    FeaturedProduct(uiState.products[index], uiState.categories, onProductClicked)
                }

                item {
                    Spacer(Modifier.navigationBarsPadding())
                }
            }
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    FractalTheme {
        HomeScreen(
            uiState = HomeScreenState(
                products = listOf(
                    ProductFull(
                        name = "Fog Linen Chambray Towel - Beige Stripe",
                        type = ProductFull.Type.physical,
                        weight = 1.0f,
                        price = 49.00f,
                        images = listOf(
                            ProductImageFull(
                                urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/77/images/265/foglinenbeigestripetowel3b.1652641772.220.290.jpg?c=1",
                            ),
                        ),
                        categories = listOf(18, 23),
                    ),
                    ProductFull(
                        name = "Orbit Terrarium - Small",
                        type = ProductFull.Type.physical,
                        weight = 1.0f,
                        price = 89.00f,
                        images = listOf(
                            ProductImageFull(
                                urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/81/images/273/roundterrariumsmall.1652641773.220.290.jpg?c=1",
                            ),
                        ),
                        categories = listOf(19, 23),
                    ),
                    ProductFull(
                        name = "Able Brewing System",
                        type = ProductFull.Type.physical,
                        weight = 1.0f,
                        price = 225.00f,
                        images = listOf(
                            ProductImageFull(
                                urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/86/images/283/ablebrewingsystem1.1652641773.220.290.jpg?c=1",
                            ),
                        ),
                        categories = listOf(21, 23),
                    ),
                    ProductFull(
                        name = "Chemex Coffeemaker 3 Cup",
                        type = ProductFull.Type.physical,
                        weight = 1.0f,
                        price = 49.50f,
                        images = listOf(
                            ProductImageFull(
                                urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/88/images/292/3cupchemex5.1652641773.220.290.jpg?c=1",
                            ),
                        ),
                        categories = listOf(21, 23),
                    ),
                ),
                categories = listOf(
                    Category(
                        parentId = 0,
                        id = 18,
                        name = "Bath",
                    ),
                    Category(
                        parentId = 0,
                        id = 19,
                        name = "Garden",
                    ),
                    Category(
                        parentId = 0,
                        id = 21,
                        name = "Kitchen",
                    ),
                    Category(
                        parentId = 0,
                        id = 23,
                        name = "Shop All",
                    ),
                ),
            ),
            onProductClicked = {},
            onShoppingCartClicked = {},
        )
    }
}
