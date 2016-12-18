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

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ca.udes.android_projectweather.models.Coord;
import ca.udes.android_projectweather.models.ModelConstants;

/**
 * A data city object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class City implements Serializable {

    @SerializedName(ModelConstants.FIELD_ID)
    private Integer mCityId;

    @SerializedName(ModelConstants.FIELD_NAME)
    private String mCityName;

    @SerializedName(ModelConstants.FIELD_COORD)
    private Coord mCityCoord;

    @SerializedName(ModelConstants.FIELD_COUNTRY)
    private String mCityCountry;

    /**
     * The city ID (required).
     *
     * @return      mCityId
     */
    public Integer getCityId() {
        return mCityId;
    }

    /**
     * The city name (required).
     *
     * @return      mCityName
     */
    public String getCityName() {
        return mCityName;
    }

    /**
     * An object Coord with the requested latitude and longitude (required).
     *
     * @return      mCityCoord
     */
    public Coord getCityCoord() {
        return mCityCoord;
    }

    /**
     * The country code (required).
     *
     * @return      mCityCountry
     */
    public String getCityCountry() {
        return mCityCountry;
    }
}
