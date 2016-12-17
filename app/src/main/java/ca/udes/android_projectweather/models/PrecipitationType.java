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
 * The several types of precipitation that the Dark Sky API uses.
 *
 * @version 1.0
 */
public enum PrecipitationType {

    @SerializedName(ModelConstants.PRECIPITATION_RAIN)
    RAIN(ModelConstants.PRECIPITATION_RAIN),
    @SerializedName(ModelConstants.PRECIPITATION_SNOW)
    SNOW(ModelConstants.PRECIPITATION_SNOW),
    @SerializedName(ModelConstants.PRECIPITATION_SLEET)
    SLEET(ModelConstants.PRECIPITATION_SLEET);

    private final String mText;

    /**
     * The value of a precipitation.
     *
     * @return      mText
     */
    PrecipitationType(String text) {
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
     * The precipitation corresponding to the value.
     *
     * @return      language
     * @param       text
     */
    @SuppressWarnings("unused")
    public static PrecipitationType precipitationTypeFromString(String text) {
        if (text != null) {
            for (PrecipitationType precipitationType : PrecipitationType.values()) {
                if (text.equalsIgnoreCase(precipitationType.mText)) {
                    return precipitationType;
                }
            }
        }
        return null;
    }
}
