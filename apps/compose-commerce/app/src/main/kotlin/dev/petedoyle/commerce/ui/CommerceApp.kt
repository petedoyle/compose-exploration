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
package dev.petedoyle.commerce.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.petedoyle.commerce.ui.cart.CartScreen
import dev.petedoyle.commerce.ui.home.HomeScreen
import dev.petedoyle.commerce.ui.productdetail.ProductDetailScreen
import dev.petedoyle.commerce.ui.productdetail.ProductDetailScreenViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ProductDetail : Screen("product/{productId}") {
        fun createRoute(productId: Int) = "product/$productId"
    }

    object Cart : Screen("cart")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CommerceApp(appState: CommerceAppState = rememberAppState()) {
    AnimatedNavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(400))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(400))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, tween(400))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(400))
        },
    ) {
        composable(Screen.Home.route) {
            HomeScreen(hiltViewModel(), appState.navController)
        }
        composable(Screen.ProductDetail.route) {
            val viewModel: ProductDetailScreenViewModel = hiltViewModel()
            viewModel.init(productId = it.arguments?.getString("productId")?.toInt() ?: 0)
            ProductDetailScreen(viewModel, appState.navController)
        }
        composable(Screen.Cart.route) {
            CartScreen(hiltViewModel(), appState.navController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberAnimatedNavController(),
) = remember(navController) {
    CommerceAppState(navController)
}

class CommerceAppState(
    val navController: NavHostController,
)
