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

import app.cash.turbine.test
import com.nhaarman.expect.expect
import com.slack.eithernet.ApiResult
import com.slack.eithernet.ExperimentalEitherNetApi
import com.slack.eithernet.test.enqueue
import com.slack.eithernet.test.newEitherNetController
import dev.petedoyle.commerce.cart.CartStore
import dev.petedoyle.commerce.cart.LocalCartStore
import dev.petedoyle.commerce.common.api.bigcommerce.BigCommerceManagementApi
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.Category
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.MetaCollectionFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductImageFull
import dev.petedoyle.commerce.common.api.bigcommerce.models.CategoryCollectionResponse
import dev.petedoyle.commerce.common.api.bigcommerce.models.ProductCollectionResponse
import dev.petedoyle.common.test.TestBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@ExperimentalEitherNetApi
class HomeScreenViewModelTest : TestBase() {
    private lateinit var viewModel: HomeScreenViewModel

    private val cartStore: CartStore = LocalCartStore()
    private val fakeApiController = newEitherNetController<BigCommerceManagementApi>()

    @Before
    fun setup() {
        // For loading from init {}
        fakeApiController.enqueue(
            BigCommerceManagementApi::listFeaturedProducts,
            ApiResult.success(
                ProductCollectionResponse(
                    data = listOf(
                        ProductFull(
                            id = 86,
                            name = "Able Brewing System",
                            type = ProductFull.Type.physical,
                            weight = 1.0f,
                            price = 225.00f,
                            images = listOf(
                                ProductImageFull(
                                    urlThumbnail = "https://cdn11.bigcommerce.com/s-c22nuunnpp/products/86/images/283/ablebrewingsystem1.1652641773.220.290.jpg?c=1",
                                ),
                            ),
                        ),
                    ),
                ),
            ),
        )

        fakeApiController.enqueue(
            BigCommerceManagementApi::listCategories,
            ApiResult.success(
                CategoryCollectionResponse(
                    data = listOf(
                        Category(
                            parentId = 0,
                            name = "Garden",
                        ),
                    ),
                    meta = MetaCollectionFull(),
                ),
            ),
        )

        viewModel = HomeScreenViewModel(
            managementApi = fakeApiController.api,
            cartStore = cartStore,
            dispatcherProvider = coroutinesTestRule.testDispatcherProvider,
        )
    }

    @Test
    fun `Clicking a product should navigate to the product detail screen`() = runTest {
        viewModel.effectFlow.test {
            // When
            viewModel.onAction(ProductClicked(productId = 111))

            // Then
            expect(awaitItem()).toBe(NavigateToProductDetail(111))
        }
    }

    @Test
    fun `Clicking the shopping cart icon should navigate to the cart screen`() = runTest {
        viewModel.effectFlow.test {
            // When
            viewModel.onAction(ShoppingCartClicked)

            // Then
            expect(awaitItem()).toBe(NavigateToCart)
        }
    }
}
