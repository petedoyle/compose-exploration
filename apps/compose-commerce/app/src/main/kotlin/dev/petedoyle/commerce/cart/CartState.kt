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

data class CartState(
    val items: List<CartLineItem> = listOf(),
)

fun CartState.isCartEmpty(): Boolean = items.isEmpty()

fun CartState.subtotal(): Double = items.mapNotNull {
    (it.variant.calculatedPrice ?: 0.0) * it.quantity.toDouble()
}.sumOf { it }

fun CartState.taxes(): Double = 0.0

fun CartState.shipping(): Double = 0.0

fun CartState.discounts(): Double = 0.0

fun CartState.total() = subtotal() + taxes() + shipping() - discounts()
