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
package dev.petedoyle.commerce.common.api.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object OffsetDateTimeAdapter : JsonAdapter<OffsetDateTime>() {
    override fun fromJson(reader: JsonReader): OffsetDateTime? {
        if (reader.peek() == JsonReader.Token.NULL) {
            return reader.nextNull()
        }
        return OffsetDateTime.parse(reader.nextString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }

    override fun toJson(writer: JsonWriter, value: OffsetDateTime?) {
        if (value == null) {
            writer.nullValue()
        } else {
            val string: String = value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            writer.value(string)
        }
    }
}
