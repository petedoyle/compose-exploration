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
package dev.petedoyle.commerce.cart

import dev.petedoyle.common.flux.BaseStore
import kotlinx.coroutines.delay
import kotlin.random.Random

class LocalCartStore : CartStore, BaseStore<CartState, CartStoreActions>(CartState()) {

    override suspend fun reduce(state: CartState, action: CartStoreActions): CartState {
        injectFakeApiDelay()

        return when (action) {
            is AddLineItem -> addLineItem(state, action)
            is UpdateLineItemQuantity -> updateLineItemQuantity(state, action)
            is RemoveLineItem -> removeLineItem(state, action)
            ClearCart -> clearCart()
        }
    }

    private fun addLineItem(cartState: CartState, action: AddLineItem): CartState {
        // Check for an existing item with this exact product and variant
        val index = cartState.items.indexOfFirst { lineItem ->
            lineItem.product.id == action.item.product.id && lineItem.variant.id == action.item.variant.id
        }

        // If a line item for this exact product and variant exists, increase its quantity by one
        return if (index >= 0) {
            val existingItem = cartState.items[index]
            val newQuantity = existingItem.quantity + 1
            val newItem = existingItem.copy(quantity = newQuantity)
            cartState.copy(
                items = cartState.items.toMutableList().apply { set(index, newItem) }.toList(),
            )
        } else { // else add a new line item
            cartState.copy(
                items = cartState.items.toMutableList().apply { add(action.item) }.toList(),
            )
        }
    }

    private fun updateLineItemQuantity(
        cartState: CartState,
        action: UpdateLineItemQuantity,
    ): CartState {
        val index = cartState.items.indexOfFirst {
            it.product.id == action.item.product.id && it.variant.id == action.item.variant.id
        }

        return when (index >= 0) {
            true -> cartState.copy(
                items = cartState.items.toMutableList().apply {
                    set(index, action.item.copy(quantity = action.newQuantity))
                }.toList(),
            )
            else -> cartState
        }
    }

    private fun removeLineItem(cartState: CartState, action: RemoveLineItem): CartState {
        return cartState.copy(
            items = cartState.items.filterNot {
                it.product.id == action.item.product.id && it.variant.id == action.item.variant.id
            },
        )
    }

    private fun clearCart(): CartState {
        return CartState()
    }

    /**
     * Delays for a random amount to pretend like there's some API latency.
     * Useful for seeing loading states, etc. in the UI.
     */
    private suspend fun injectFakeApiDelay() =
        delay(Random.nextLong(FAKE_API_DELAY_MIN, FAKE_API_DELAY_MAX))

    companion object {
        private const val FAKE_API_DELAY_MIN: Long = 500
        private const val FAKE_API_DELAY_MAX: Long = 1000
    }
}
