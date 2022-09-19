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

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.slack.eithernet.ApiResult.Failure
import com.slack.eithernet.ApiResult.Failure.ApiFailure
import com.slack.eithernet.ApiResult.Failure.HttpFailure
import com.slack.eithernet.ApiResult.Failure.NetworkFailure
import com.slack.eithernet.ApiResult.Failure.UnknownFailure
import com.slack.eithernet.ApiResult.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.petedoyle.commerce.cart.CartState
import dev.petedoyle.commerce.cart.CartStore
import dev.petedoyle.commerce.common.api.bigcommerce.BigCommerceManagementApi
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.Category
import dev.petedoyle.commerce.common.api.bigcommerce.catalog.v3.model.ProductFull
import dev.petedoyle.common.coroutines.DispatcherProvider
import dev.petedoyle.common.mvi.ViewModelBase
import dev.petedoyle.common.mvi.ViewModelState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class HomeScreenState(
    val cartState: CartState = CartState(),
    val products: List<ProductFull> = listOf(),
    val categories: List<Category> = listOf(),
    val error: String? = null,
) : ViewModelState

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val managementApi: BigCommerceManagementApi,
    private val cartStore: CartStore,
    dispatcherProvider: DispatcherProvider,
) : ViewModelBase<HomeScreenState, HomeScreenActions, HomeScreenEffects>(
    HomeScreenState(),
    dispatcherProvider,
) {

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch(dispatcherProvider.io()) {
            awaitAll(
                async { loadFeaturedProducts() },
                async { loadCategories() },
            )
        }

        observeCartState()
    }

    private suspend fun loadFeaturedProducts() {
        when (val result = managementApi.listFeaturedProducts()) {
            is Success -> setState { copy(products = result.value.data.orEmpty()) }
            is Failure -> when (result) {
                is NetworkFailure -> emitEffect(NetworkError)
                is UnknownFailure -> emitEffect(NetworkError)
                is HttpFailure -> emitEffect(NetworkError)
                is ApiFailure -> emitEffect(NetworkError)
            }
        }
    }

    private suspend fun loadCategories() {
        when (val result = managementApi.listCategories()) {
            is Success -> setState { copy(categories = result.value.data) }
            is Failure -> when (result) {
                is NetworkFailure -> emitEffect(NetworkError)
                is UnknownFailure -> emitEffect(NetworkError)
                is HttpFailure -> emitEffect(NetworkError)
                is ApiFailure -> emitEffect(NetworkError)
            }
        }
    }

    private fun observeCartState() {
        viewModelScope.launch(dispatcherProvider.io()) {
            cartStore.stateFlow.collect { cartState ->
                setState { copy(cartState = cartState) }
            }
        }
    }

    override fun onAction(action: HomeScreenActions) {
        when (action) {
            is ProductClicked -> emitEffect(NavigateToProductDetail(action.productId))
            ShoppingCartClicked -> emitEffect(NavigateToCart)
        }
    }
}
