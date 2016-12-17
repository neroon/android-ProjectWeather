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
import java.util.ArrayList;

/**
 * API responses consist of a UTF-8-encoded.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class ForecastResponse implements Serializable {

    @SerializedName(ModelConstants.FIELD_LATITUDE)
    private double mLatitude;

    @SerializedName(ModelConstants.FIELD_LONGITUDE)
    private double mLongitude;

    @SerializedName(ModelConstants.FIELD_TIMEZONE)
    private String mTimezone;

    @Deprecated
    @SerializedName(ModelConstants.FIELD_OFFSET)
    private double mOffset;

    @Nullable
    @SerializedName(ModelConstants.FIELD_CURRENTLY)
    private DataPoint mCurrently;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MINUTELY)
    private DataBlock mMinutely;

    @Nullable
    @SerializedName(ModelConstants.FIELD_HOURLY)
    private DataBlock mHourly;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DAILY)
    private DataBlock mDaily;

    @Nullable
    @SerializedName(ModelConstants.FIELD_ALERTS)
    private ArrayList<Alerts> mAlerts;

    @Nullable
    @SerializedName(ModelConstants.FIELD_FLAGS)
    private Flags mFlags;

    /**
     * The requested latitude (required).
     *
     * @return      mLatitude
     */
    public double getLatitude() {
        return mLatitude;
    }

    /**
     * The requested longitude (required).
     *
     * @return      mLongitude
     */
    public double getLongitude() {
        return mLongitude;
    }

    /**
     * The IANA timezone name for the requested location. This is used for text summaries and for
     * determining when hourly and daily data block objetcs begind (required).
     *
     * @return      mTimezone
     */
    public String getTimezone() {
        return mTimezone;
    }

    /**
     * The current timezone offset in hours. (Use of this property will almost certainly result in
     * Daylight Saving Time bugs. Please use timezone, instead.) (deprecated).
     *
     * @return      mOffset
     */
    public double getOffset() {
        return mOffset;
    }

    /**
     * A data point containing the current weather conditions at the requested location (optional).
     *
     * @return      mCurrently
     */
    @Nullable
    public DataPoint getCurrently() {
        return mCurrently;
    }

    /**
     * A data block containing the weather conditions minute-by-minute for the next hour (optional).
     *
     * @return      mMinutely
     */
    @Nullable
    public DataBlock getMinutely() {
        return mMinutely;
    }

    /**
     * A data block containing the weather conditions hour-by-hour for the two days (optional).
     *
     * @return      mHourly
     */
    @Nullable
    public DataBlock getHourly() {
        return mHourly;
    }

    /**
     * A data block containing the weather conditions day-by-day for the next week (optional).
     *
     * @return      mDaily
     */
    @Nullable
    public DataBlock getDaily() {
        return mDaily;
    }

    /**
     * An alerts array, which, if present, contains any severe weather alerts pertinent to the
     * request location (optional).
     *
     * @return      mAlerts
     */
    @Nullable
    public ArrayList<Alerts> getAlerts() {
        return mAlerts;
    }

    /**
     * A flags object containing miscellaneous metadata about the request (optional).
     *
     * @return      mFlags
     */
    @Nullable
    public Flags getFlags() {
        return mFlags;
    }
}
