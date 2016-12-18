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

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * A data weather object contains various properties.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Weather implements Serializable {

    @SerializedName(ModelConstants.FIELD_ID)
    private Integer mWeatherId;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MAIN)
    private String mWeatherMain;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DESCRIPTION)
    private String mWeatherDescription;

    @Nullable
    @SerializedName(ModelConstants.FIELD_ICON)
    private String mWeatherIcon;

    /**
     * The weather condition id (required).
     *
     * @return      mWeatherId
     */
    public Integer getWeatherId() {
        return mWeatherId;
    }

    /**
     * The group of weather parameters (Rain, Snow, Extreme etc) (optional).
     *
     * @return      mWeatherMain
     */
    @Nullable
    public String getWeatherMain() {
        return mWeatherMain;
    }

    /**
     * The weather condition within the group (optional).
     *
     * @return      mWeatherDescription
     */
    @Nullable
    public String getWeatherDescription() {
        return mWeatherDescription;
    }

    /**
     * The weather icon id (optional).
     *
     * @return      mWeatherIcon
     */
    @Nullable
    public String getWeatherIcon() {
        return mWeatherIcon;
    }
}
