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

package ca.udes.android_projectweather.models.weathermodel;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ca.udes.android_projectweather.models.ModelConstants;

/**
 * A data sys object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Main implements Serializable {

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMP)
    private Double mMainTemp;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRESSURE)
    private Double mMainPressure;

    @Nullable
    @SerializedName(ModelConstants.FIELD_HUMIDITY)
    private Integer mMainHumidity;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMP_MIN)
    private Double mMainTempMin;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMP_MAX)
    private Double mMainTempMax;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SEA_LEVEL)
    private Double mMainSeaLevel;

    @Nullable
    @SerializedName(ModelConstants.FIELD_GRND_LEVEL)
    private Double mMainGrndLevel;

    /**
     * The air temperature in Kelvin by default (otherwise Metric : Cemsius, Imperial: Fahrenheit)
     * (optional).
     *
     * @return      mMainTemp
     */
    @Nullable
    public Double getMainTemp() {
        return mMainTemp;
    }

    /**
     * The atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data),
     * in hPa (optional).
     *
     * @return      mMainPressure
     */
    @Nullable
    public Double getMainPressure() {
        return mMainPressure;
    }

    /**
     * The percentage of humidity (optional).
     *
     * @return      mMainHumidity
     */
    @Nullable
    public Integer getMainHumidity() {
        return mMainHumidity;
    }

    /**
     * The minimum value of temperature during a given day (optional).
     *
     * @return      mMainTempMin
     */
    @Nullable
    public Double getMainTempMin() {
        return mMainTempMin;
    }

    /**
     * The maximum value of temperature during a given day (optional).
     *
     * @return      mMainTempMax
     */
    @Nullable
    public Double getMainTempMax() {
        return mMainTempMax;
    }

    /**
     * The atmospheric pressure on the sea level in hPa (optional).
     *
     * @return      mMainSeaLevel
     */
    @Nullable
    public Double getMainSeaLevel() {
        return mMainSeaLevel;
    }

    /**
     * The atmospheric pressure on the ground level in hPa (optional).
     *
     * @return      mMainGrndLevel
     */
    @Nullable
    public Double getMainGrndLevel() {
        return mMainGrndLevel;
    }
}
