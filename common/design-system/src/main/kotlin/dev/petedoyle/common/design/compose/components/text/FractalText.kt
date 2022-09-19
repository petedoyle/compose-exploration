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
package dev.petedoyle.common.design.compose.components.text

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.petedoyle.common.design.compose.theme.FractalTheme

@Composable
fun FractalText(
    text: String,
    fractalTextVariant: FractalTextVariant,
    modifier: Modifier = Modifier,
    color: Color = fractalTextVariant.defaultColor(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
) {
    BasicText(
        text = text,
        style = fractalTextVariant.textStyle().copy(color = color),
        modifier = modifier,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
    )
}

@Composable
fun FractalText(
    text: AnnotatedString,
    fractalTextVariant: FractalTextVariant,
    modifier: Modifier = Modifier,
    color: Color = fractalTextVariant.defaultColor(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
) {
    BasicText(
        text = text,
        style = fractalTextVariant.textStyle().copy(color = color),
        modifier = modifier,
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        inlineContent = inlineContent,
    )
}

@Composable
fun FractalTextGallery(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(FractalTheme.spacing.xxs),
        modifier = modifier.padding(FractalTheme.spacing.m),
    ) {
        FractalText("Display1", FractalTextVariant.Display1)
        FractalText("Display2", FractalTextVariant.Display2)
        FractalText("Display 3", FractalTextVariant.Display3)
        FractalText("Heading 1", FractalTextVariant.Heading1)
        FractalText("Heading 2", FractalTextVariant.Heading2)
        FractalText("Heading 3", FractalTextVariant.Heading3)
        FractalText("Body 1", FractalTextVariant.Body1)
        FractalText("Body 2", FractalTextVariant.Body2)
        FractalText("Body 3", FractalTextVariant.Body3)
        FractalText("Label 1", FractalTextVariant.Label1)
        FractalText("Label 2", FractalTextVariant.Label2)
        FractalText("Label 3", FractalTextVariant.Label3)
        FractalText("Label 4", FractalTextVariant.Label4)
        FractalText("Caption 1", FractalTextVariant.Caption1)
        FractalText("Caption 2", FractalTextVariant.Caption2)
        FractalText("Snippet", FractalTextVariant.Snippet)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    FractalTheme {
        FractalTextGallery(
            modifier = Modifier.background(FractalTheme.colors.backgroundPrimary),
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewParagraph() {
    FractalTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(FractalTheme.spacing.xs),
            modifier = Modifier
                .background(FractalTheme.colors.backgroundPrimary)
                .padding(FractalTheme.spacing.m),
        ) {
            FractalText(
                "Lorem Ipsum",
                FractalTextVariant.Heading1,
            )
            FractalText(
                text = buildAnnotatedString {
                    append("Lorem ipsum dolor sit amet, ")
                    withStyle(
                        style = SpanStyle(
                            color = FractalTheme.colors.onErrorPrimary,
                            background = FractalTheme.colors.errorPrimary,
                        ),
                    ) {
                        append("consectetur adipiscing elit")
                    }
                    append(", sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                },
                fractalTextVariant = FractalTextVariant.Body1,
            )
        }
    }
}
