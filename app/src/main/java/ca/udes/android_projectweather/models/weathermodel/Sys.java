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
public class Sys implements Serializable {

    @SerializedName(ModelConstants.FIELD_TYPE)
    private Integer mSysType;

    @SerializedName(ModelConstants.FIELD_ID)
    private Integer mSysId;

    @SerializedName(ModelConstants.FIELD_MESSAGE)
    private Double mSysMessage;

    @SerializedName(ModelConstants.FIELD_COUNTRY)
    private String mSysCountry;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SUNRISE)
    private Integer mSysSunrise;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SUNSET)
    private Integer mSysSunset;

    /**
     * Internal parameter (required).
     *
     * @return      mSysType
     */
    @Nullable
    public Integer getSysType() {
        return mSysType;
    }

    /**
     * Internal parameter (required).
     *
     * @return      mSysId
     */
    @Nullable
    public Integer getSysId() {
        return mSysId;
    }

    /**
     * Internal parameter (required).
     *
     * @return      mSysMessage
     */
    @Nullable
    public Double getSysMessage() {
        return mSysMessage;
    }

    /**
     * The country code (required).
     *
     * @return      mSysCountry
     */
    @Nullable
    public String getSysCountry() {
        return mSysCountry;
    }

    /**
     * The UNIX time of when the sun will rise during a given day (optional).
     *
     * @return      mSysSunrise
     */
    @Nullable
    public Integer getSysSunrise() {
        return mSysSunrise;
    }

    /**
     * The UNIX time of when the sun will rise during a given day (optional).
     *
     * @return      mSysSunset
     */
    @Nullable
    public Integer getSysSunset() {
        return mSysSunset;
    }
}
