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
package dev.petedoyle.common.mvi.test

import dev.petedoyle.common.mvi.ViewModelState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.drop

/**
 * Drops the first emission from a `StateFlow<ViewModelState>`,
 * which is the initial / default state from [ViewModelBase].
 *
 * Useful for tests that need to verify ViewModelState after
 * taking an action.
 */
inline fun <reified S : ViewModelState> StateFlow<S>.dropInitialState(count: Int = 1) = drop(count)
