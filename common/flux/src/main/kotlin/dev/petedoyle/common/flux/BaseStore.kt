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
package dev.petedoyle.common.flux

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseStore<S, A : StoreActions>(initialState: S) : Store<S, A> {
    private val _stateFlow = MutableStateFlow(initialState)
    override val stateFlow: StateFlow<S>
        get() = _stateFlow

    private val dispatchMutex = Mutex()

    override suspend fun dispatch(action: A) {
        dispatchMutex.withLock {
            val newState = reduce(_stateFlow.value, action)
            setState { newState }
        }
    }

    abstract suspend fun reduce(state: S, action: A): S

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    override fun setState(lambda: S.() -> S) {
        _stateFlow.value = lambda(_stateFlow.value)
    }
}
