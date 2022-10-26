/*
 * Copyright (C) 2021 Pete Doyle
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
package dev.petedoyle.moviesearch

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import dev.petedoyle.common.coroutines.DefaultDispatcherProvider
import dev.petedoyle.common.mvi.ViewAction
import dev.petedoyle.common.mvi.ViewModelBase
import dev.petedoyle.common.mvi.ViewModelEffect
import dev.petedoyle.common.mvi.ViewModelState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * [ViewModelState] for [MainScreenViewModel].
 */
@Immutable
data class MainScreenState(
    val query: String = "",
    val searchResults: List<Movie> = listOf(),
    val focusedMovie: Movie? = null,
) : ViewModelState

/**
 * The [ViewModelBase] for [MainActivity] / [MainScreen].
 */
@OptIn(FlowPreview::class)
class MainScreenViewModel : ViewModelBase<MainScreenState, MainScreenActions, MainScreenEffects>(
    MainScreenState(),
    DefaultDispatcherProvider(), // TODO inject
) {

    init {
        // Update search results as the query changes
        viewModelScope.launch {
            stateFlow
                .debounce(QUERY_DEBOUNCE_MILLIS)
                .map { state ->
                    Movie.MOVIE_TEST_DATA.filter { movie ->
                        movie.title.contains(state.query.trim(), ignoreCase = true)
                    }
                }
                .distinctUntilChanged()
                .collect { results ->
                    setState { copy(searchResults = results) }
                }
        }
    }

    override fun onAction(action: MainScreenActions) {
        when (action) {
            is MainScreenActions.QuerySubmitted -> setState { copy(query = action.query) }
            MainScreenActions.QueryCleared -> setState { copy(query = "") }
            is MainScreenActions.MovieFocused -> setState { copy(focusedMovie = action.movie) }
            MainScreenActions.MovieBlurred -> setState { copy(focusedMovie = null) }
        }
    }

    companion object {
        @VisibleForTesting
        internal const val QUERY_DEBOUNCE_MILLIS = 100L
    }
}

sealed class MainScreenActions : ViewAction {
    data class QuerySubmitted(val query: String) : MainScreenActions()
    object QueryCleared : MainScreenActions()
    data class MovieFocused(val movie: Movie) : MainScreenActions()
    object MovieBlurred : MainScreenActions()
}

sealed class MainScreenEffects : ViewModelEffect
