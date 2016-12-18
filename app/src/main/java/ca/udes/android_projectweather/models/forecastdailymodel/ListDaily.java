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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import ca.udes.android_projectweather.models.Weather;
import ca.udes.android_projectweather.models.ModelConstants;

/**
 * A data list object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class ListDaily implements Serializable {

    @SerializedName(ModelConstants.FIELD_DT)
    private Integer mListDt;

    @SerializedName(ModelConstants.FIELD_TEMP)
    private Temp mListTemp;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRESSURE)
    private Double mListPressure;

    @Nullable
    @SerializedName(ModelConstants.FIELD_COUNTRY)
    private Integer mListHumidity;

    @SerializedName(ModelConstants.FIELD_WEATHER)
    private List<Weather> mListWeather = new ArrayList<>();

    @Nullable
    @SerializedName(ModelConstants.FIELD_SPEED)
    private Double mListSpeed;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DEG)
    private Integer mListDeg;

    @Nullable
    @SerializedName(ModelConstants.FIELD_CLOUDS)
    private Integer mListClouds;

    /**
     * The time of data forecassted (required).
     *
     * @return      mListDt
     */
    public Integer getListDt() {
        return mListDt;
    }

    /**
     * An object Temp which contains the weather conditions at the requested location (required).
     *
     * @return      mListTemp
     */
    public Temp getListTemp() {
        return mListTemp;
    }

    /**
     * The atmospheric pressure on the sea level in hPa (optional).
     * @return      mListPressure
     */
    @Nullable
    public Double getListPressure() {
        return mListPressure;
    }

    /**
     * The percentage of humidity (optional).
     *
     * @return      mListHumidity
     */
    @Nullable
    public Integer getListHumidity() {
        return mListHumidity;
    }

    /**
     * A list of data weather which together describe the weather conditions at the requested
     * location over time (required).
     *
     * @return      mWeather
     */
    public List<Weather> getListWeather() {
        return mListWeather;
    }

    /**
     * The wind speed in meter/sec by default (otherwise Metric: meter/sec and Imperial: miles/hour
     * (optional).
     *
     * @return      mListSpeed
     */
    @Nullable
    public Double getListSpeed() {
        return mListSpeed;
    }

    /**
     * The wind direction in degrees (optional).
     *
     * @return      mListDeg
     */
    @Nullable
    public Integer getListDeg() {
        return mListDeg;
    }

    /**
     * The percentage of sky occluded by clouds (optional).
     *
     * @return      mListClouds
     */
    @Nullable
    public Integer getListClouds() {
        return mListClouds;
    }
}
