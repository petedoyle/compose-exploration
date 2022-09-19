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

import androidx.lifecycle.viewModelScope
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.UpdateAvailability
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.petedoyle.commerce.cart.CartStore
import dev.petedoyle.commerce.cart.ClearCart
import dev.petedoyle.commerce.ui.MainActivityActions.AppStarting
import dev.petedoyle.commerce.ui.MainActivityActions.CheckoutComplete
import dev.petedoyle.commerce.ui.MainViewModelEffects.AppUpdateAvailableEffect
import dev.petedoyle.commerce.ui.MainViewModelEffects.AppUpdateCheckFailedEffect
import dev.petedoyle.commerce.ui.MainViewModelEffects.AppUpdateNotAvailableEffect
import dev.petedoyle.common.coroutines.DispatcherProvider
import dev.petedoyle.common.mvi.ViewModelBase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appUpdateManager: AppUpdateManager,
    private val cartStore: CartStore,
    dispatcherProvider: DispatcherProvider,
) : ViewModelBase<MainViewModelState, MainActivityActions, MainViewModelEffects>(
    MainViewModelState,
    dispatcherProvider,
) {

    override fun onAction(action: MainActivityActions) {
        when (action) {
            AppStarting -> handleAppStarting()
            is CheckoutComplete -> handleCheckoutComplete()
        }
    }

    private fun handleAppStarting() {
        checkForUpdates()
    }

    private fun handleCheckoutComplete() {
        viewModelScope.launch(dispatcherProvider.io()) {
            cartStore.dispatch(ClearCart)
        }
    }

    private fun checkForUpdates() {
        appUpdateManager.appUpdateInfo.apply {
            addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val appUpdateInfo: AppUpdateInfo = task.result
                    when (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                        true -> emitEffect(AppUpdateAvailableEffect(appUpdateInfo))
                        else -> emitEffect(AppUpdateNotAvailableEffect)
                    }
                } else {
                    emitEffect(AppUpdateCheckFailedEffect(task.exception))
                }
            }
        }
    }
}
