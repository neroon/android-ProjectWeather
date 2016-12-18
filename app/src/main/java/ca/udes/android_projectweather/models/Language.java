/*
 * Copyright (C) 2016 University of Sherbrooke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ca.udes.android_projectweather.models;

import com.google.gson.annotations.SerializedName;

/**
 * The several types of languages that the OpenWeatherMapAPI uses.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public enum Language {

    @SerializedName(ModelConstants.LANGUAGE_ROMANIAN)
    ROMANIAN(ModelConstants.LANGUAGE_ROMANIAN),
    @SerializedName(ModelConstants.LANGUAGE_POLISH)
    POLISH(ModelConstants.LANGUAGE_POLISH),
    @SerializedName(ModelConstants.LANGUAGE_FINNISH)
    FINNISH(ModelConstants.LANGUAGE_FINNISH),
    @SerializedName(ModelConstants.LANGUAGE_GERMAN)
    GERMAN(ModelConstants.LANGUAGE_GERMAN),
    @SerializedName(ModelConstants.LANGUAGE_BULGARIAN)
    BULGARIAN(ModelConstants.LANGUAGE_BULGARIAN),
    @SerializedName(ModelConstants.LANGUAGE_CATALAN)
    CATALAN(ModelConstants.LANGUAGE_CATALAN),
    @SerializedName(ModelConstants.LANGUAGE_ENGLISH)
    ENGLISH(ModelConstants.LANGUAGE_ENGLISH),
    @SerializedName(ModelConstants.LANGUAGE_SPANISH)
    SPANISH(ModelConstants.LANGUAGE_SPANISH),
    @SerializedName(ModelConstants.LANGUAGE_FRENCH)
    FRENCH(ModelConstants.LANGUAGE_FRENCH),
    @SerializedName(ModelConstants.LANGUAGE_CROATIAN)
    CROATIAN(ModelConstants.LANGUAGE_CROATIAN),
    @SerializedName(ModelConstants.LANGUAGE_ITALIAN)
    ITALIAN(ModelConstants.LANGUAGE_ITALIAN),
    @SerializedName(ModelConstants.LANGUAGE_DUTCH)
    DUTCH(ModelConstants.LANGUAGE_DUTCH),
    @SerializedName(ModelConstants.LANGUAGE_PORTUGUESE)
    PORTUGUESE(ModelConstants.LANGUAGE_PORTUGUESE),
    @SerializedName(ModelConstants.LANGUAGE_RUSSIAN)
    RUSSIAN(ModelConstants.LANGUAGE_RUSSIAN),
    @SerializedName(ModelConstants.LANGUAGE_SWEDISH)
    SWEDISH(ModelConstants.LANGUAGE_SWEDISH),
    @SerializedName(ModelConstants.LANGUAGE_TURKISH)
    TURKISH(ModelConstants.LANGUAGE_TURKISH),
    @SerializedName(ModelConstants.LANGUAGE_UKRAINIAN)
    UKRAINIAN(ModelConstants.LANGUAGE_UKRAINIAN),
    @SerializedName(ModelConstants.LANGUAGE_SIMPLIFIED_CHINESE)
    SIMPLIFIED_CHINESE(ModelConstants.LANGUAGE_SIMPLIFIED_CHINESE),
    @SerializedName(ModelConstants.LANGUAGE_TRADITIONAL_CHINESE)
    TRADITIONAL_CHINESE(ModelConstants.LANGUAGE_TRADITIONAL_CHINESE);

    private final String mText;

    /**
     * The value of a language.
     *
     * @return      mText
     */
    Language(String text) {
        mText = text;
    }

    /**
     * Accessor
     *
     * @return      mText
     */
    public String getText() {
        return mText;
    }

    /**
     * The language corresponding to the value.
     *
     * @return      language
     * @param       text
     */
    @SuppressWarnings("unused")
    public static Language languageFromString(String text) {
        if (text != null) {
            for (Language language : Language.values()) {
                if (text.equalsIgnoreCase(language.mText)) {
                    return language;
                }
            }
        }
        return null;
    }
}