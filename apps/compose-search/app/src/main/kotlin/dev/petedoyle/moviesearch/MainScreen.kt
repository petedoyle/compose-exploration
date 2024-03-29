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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val uiState = viewModel.stateFlow.collectAsState().value
    MainScreenContent(uiState, viewModel::onAction)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainScreenContent(
    uiState: MainScreenState,
    actionHandler: (MainScreenActions) -> Unit,
) {
    val query = uiState.query
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = uiState.focusedMovie) {
        if (uiState.focusedMovie != null) {
            snackbarHostState.showSnackbar(
                "${uiState.focusedMovie.title} was released in ${uiState.focusedMovie.year}",
            )
            actionHandler(MainScreenActions.MovieBlurred)
        }
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.main_title)) },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Header()
            SearchField(query, actionHandler)
            SearchResults(uiState.searchResults, actionHandler, Modifier.weight(1f))
        }
    }
}

@Composable
private fun Header() {
    Text(
        stringResource(R.string.main_screen_header),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(FractalTheme.spacing.l),
    )
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchField(
    query: String,
    actionHandler: (MainScreenActions) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box {
        TextField(
            value = query,
            onValueChange = { newValue ->
                actionHandler(MainScreenActions.QuerySubmitted(newValue))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() },
            ),
            label = {
                Text(stringResource(id = R.string.query_hint))
            },
            modifier = Modifier
                .padding(horizontal = FractalTheme.spacing.l)
                .fillMaxWidth(),
        )

        AnimatedVisibility(
            visible = query.isNotEmpty(),
            modifier = Modifier.align(Alignment.CenterEnd),
        ) {
            Icon(
                Icons.Filled.Close,
                contentDescription = stringResource(R.string.clear),
                modifier = Modifier
                    .padding(horizontal = FractalTheme.spacing.xl)
                    .clickable { actionHandler(MainScreenActions.QueryCleared) },
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchResults(
    results: List<Movie>,
    actionHandler: (MainScreenActions) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(results.size) { index ->
            val movie = results[index]
            SearchResultRow(movie, actionHandler)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchResultRow(
    movie: Movie,
    actionHandler: (MainScreenActions) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable {
                actionHandler(MainScreenActions.MovieFocused(movie))
                keyboardController?.hide()
            }
            .padding(horizontal = FractalTheme.spacing.l),
    ) {
        Text(text = movie.title)
    }
    Divider()
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    FractalTheme {
        MainScreenContent(
            MainScreenState(
                query = "Star",
                searchResults = Movie.MOVIE_TEST_DATA.filter {
                    it.title.contains("Star", ignoreCase = true)
                },
            ),
            actionHandler = { },
        )
    }
}
