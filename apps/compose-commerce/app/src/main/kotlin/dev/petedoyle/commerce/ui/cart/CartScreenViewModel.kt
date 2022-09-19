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
package dev.petedoyle.commerce.ui.cart

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.petedoyle.commerce.cart.CartState
import dev.petedoyle.commerce.cart.CartStore
import dev.petedoyle.commerce.cart.RemoveLineItem
import dev.petedoyle.commerce.cart.UpdateLineItemQuantity
import dev.petedoyle.common.coroutines.DispatcherProvider
import dev.petedoyle.common.mvi.ViewModelBase
import dev.petedoyle.common.mvi.ViewModelState
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
data class CartScreenState(
    val cartState: CartState = CartState(),
    val recalculatingCartTotals: Boolean = false,
) : ViewModelState

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val cartStore: CartStore,
    dispatcherProvider: DispatcherProvider,
) : ViewModelBase<CartScreenState, CartScreenActions, CartScreenEffects>(
    CartScreenState(),
    dispatcherProvider,
) {

    init {
        load()
    }

    private fun load() {
        observeCartState()
    }

    private fun observeCartState() {
        viewModelScope.launch(dispatcherProvider.io()) {
            cartStore.stateFlow.collect { cartState ->
                setState { copy(cartState = cartState) }
            }
        }
    }

    override fun onAction(action: CartScreenActions) {
        when (action) {
            BackClicked -> emitEffect(NavigateBackEffect)
            QuickCheckoutClicked -> handleQuickCheckoutClicked()
            is RemoveCartLineItemClicked -> handleRemoveCartLineItemClicked(action)
            ShoppingCartClicked -> {
                // Do nothing- we're already on the cart screen
            }
            is UpdateLineItemQuantityClicked -> handleUpdateLineItemQuantity(action)
        }
    }

    private fun handleQuickCheckoutClicked() = emitEffect(LaunchCheckoutEffect(cartStore.stateFlow.value))

    private fun handleRemoveCartLineItemClicked(action: RemoveCartLineItemClicked) {
        viewModelScope.launch(dispatcherProvider.io()) {
            setState { copy(recalculatingCartTotals = true) }
            runCatching {
                cartStore.dispatch(RemoveLineItem(action.item))
            }.also {
                setState { copy(recalculatingCartTotals = false) }
            }
        }
    }

    private fun handleUpdateLineItemQuantity(action: UpdateLineItemQuantityClicked) {
        viewModelScope.launch(dispatcherProvider.io()) {
            setState { copy(recalculatingCartTotals = true) }
            runCatching {
                cartStore.dispatch(UpdateLineItemQuantity(action.item, action.newQuantity))
            }.also {
                setState { copy(recalculatingCartTotals = false) }
            }
        }
    }
}
