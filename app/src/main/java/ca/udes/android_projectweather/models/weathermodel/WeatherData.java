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

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import ca.udes.android_projectweather.models.Coord;
import ca.udes.android_projectweather.models.Weather;
import ca.udes.android_projectweather.models.ModelConstants;

/**
 * A data weather object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class WeatherData implements Serializable {

    @SerializedName(ModelConstants.FIELD_COORD)
    private Coord mCoord;

    @SerializedName(ModelConstants.FIELD_WEATHER)
    private List<Weather> mWeather = new ArrayList<>();

    @SerializedName(ModelConstants.FIELD_BASE)
    private String mBase;

    @SerializedName(ModelConstants.FIELD_MAIN)
    private Main mMain = new Main();

    @SerializedName(ModelConstants.FIELD_WIND)
    private Wind mWind = new Wind();

    @SerializedName(ModelConstants.FIELD_CLOUDS)
    private Clouds mClouds = new Clouds();

    @SerializedName(ModelConstants.FIELD_RAIN)
    private Rain mRain = new Rain();

    @SerializedName(ModelConstants.FIELD_SNOW)
    private Snow mSnow = new Snow();

    @SerializedName(ModelConstants.FIELD_DT)
    private Integer mDt;

    @SerializedName(ModelConstants.FIELD_SYS)
    private Sys mSys = new Sys();

    @SerializedName(ModelConstants.FIELD_ID)
    private Integer mId;

    @SerializedName(ModelConstants.FIELD_NAME)
    private String mName;

    @SerializedName(ModelConstants.FIELD_COD)
    private Integer mCod;

    /**
     * An object Coord with the requested latitude and longitude (required).
     *
     * @return      mCoord
     */
    public Coord getCoord() {
        return mCoord;
    }

    /**
     * A list of data weather which together describe the weather conditions at the requested
     * location over time (required).
     *
     * @return      mWeather
     */
    public List<Weather> getWeather() {
        return mWeather;
    }

    /**
     * Internal parameter (required).
     *
     * @return      mBase
     */
    public String getBase() {
        return mBase;
    }

    /**
     * An object Main which contains the weather conditions at the requested location (required).
     *
     * @return      mMain
     */
    public Main getMain() {
        return mMain;
    }

    /**
     * An object Wind which contains the weather conditions at the requested location (required).
     *
     * @return      mWind
     */
    public Wind getWind() {
        return mWind;
    }

    /**
     * An object Clouds which contains the weather conditions at the requested location (required).
     *
     * @return      mClouds
     */
    public Clouds getClouds() {
        return mClouds;
    }

    /**
     * An object Rain which contains the weather conditions at the requested location (required).
     *
     * @return      mRain
     */
    public Rain getRain() {
        return mRain;
    }

    /**
     * An object Snow which contains the weather conditions at the requested location (required).
     *
     * @return      mSnow
     */
    public Snow getSnow() {
        return mSnow;
    }

    /**
     * The time of data calculation, UNIX, UTC (required).
     *
     * @return      mDt
     */
    public Integer getDt() {
        return mDt;
    }

    /**
     * An object Sys which contains information at the requested location (required).
     *
     * @return      mSys
     */
    public Sys getSys() {
        return mSys;
    }

    /**
     * The city ID (required).
     *
     * @return      mId
     */
    public Integer getId() {
        return mId;
    }

    /**
     * The city name (required).
     *
     * @return      mName
     */
    public String getName() {
        return mName;
    }

    /**
     * Internal parameter (required).
     *
     * @return      mCod
     */
    public Integer getCod() {
        return mCod;
    }
}
