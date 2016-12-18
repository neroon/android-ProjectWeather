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

package ca.udes.android_projectweather.models.forecastdailymodel;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ca.udes.android_projectweather.models.ModelConstants;

/**
 * A data temp object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Temp implements Serializable {

    @Nullable
    @SerializedName(ModelConstants.FIELD_DAY)
    private Double mListTempDay;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MIN)
    private Double mListTempMin;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MAX)
    private Double mListTempMax;

    @Nullable
    @SerializedName(ModelConstants.FIELD_NIGHT)
    private Double mListTempNight;

    @Nullable
    @SerializedName(ModelConstants.FIELD_EVE)
    private Double mListTempEve;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MORN)
    private Double mListTempMorn;

    /**
     * The day value of temperature during a given day (optional).
     *
     * @return      mListTempDay
     */
    @Nullable
    public Double getListTempDay() {
        return mListTempDay;
    }

    /**
     * The minimum value of temperature during a given day (optional).
     *
     * @return      mListTempMin
     */
    @Nullable
    public Double getListTempMin() {
        return mListTempMin;
    }

    /**
     * The maximum value of temperature during a given day (optional).
     *
     * @return      mListTempMax
     */
    @Nullable
    public Double getListTempMax() {
        return mListTempMax;
    }

    /**
     * The night value of temperature during a given day (optional).
     *
     * @return      mListTempNight
     */
    @Nullable
    public Double getListTempNight() {
        return mListTempNight;
    }

    /**
     * The evening value of temperature during a given day (optional).
     *
     * @return      mListTempEve
     */
    @Nullable
    public Double getListTempEve() {
        return mListTempEve;
    }

    /**
     * The morning value of temperature during a given day (optional).
     *
     * @return      mListTempMorn
     */
    @Nullable
    public Double getListTempMorn() {
        return mListTempMorn;
    }
}
