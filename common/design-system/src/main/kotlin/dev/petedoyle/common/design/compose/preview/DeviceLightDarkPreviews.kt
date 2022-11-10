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
package dev.petedoyle.common.design.compose.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "1 - Portrait / Light",
    group = "Light",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "2 - Portrait / Dark",
    group = "Dark",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "3 - Landscape / Light",
    group = "Light",
    device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "4 - Landscape / Dark",
    group = "Dark",
    device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "5 - Foldable / Light",
    group = "Light",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "6 - Foldable / Dark",
    group = "Dark",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "7 - Tablet / Light",
    group = "Light",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "8 - Tablet / Dark",
    group = "Dark",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class DeviceLightDarkPreviews
