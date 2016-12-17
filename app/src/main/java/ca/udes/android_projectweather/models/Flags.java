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
 * The flags object contains various metadata information related to the request.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Flags implements Serializable {

    @Nullable
    @SerializedName(ModelConstants.FIELD_DARKSKY_UNAVAILABLE)
    private String mDarkSkyUnavailable;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DARKSKY_STATIONS)
    private ArrayList<String> mDarkSkyStations;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DATAPOINT_STATIONS)
    private ArrayList<String> mDataPointStations;

    @Nullable
    @SerializedName(ModelConstants.FIELD_ISD_STATIONS)
    private ArrayList<String> mISDStations;

    @Nullable
    @SerializedName(ModelConstants.FIELD_LAMP_STATIONS)
    private ArrayList<String> mLampStations;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MADIS_STATIONS)
    private ArrayList<String> mMadisStations;

    @Nullable
    @SerializedName(ModelConstants.FIELD_METAR_STATIONS)
    private ArrayList<String> mMetarStations;

    @Nullable
    @SerializedName(ModelConstants.FIELD_METNO_LICENSE)
    private String mMetnoLicense;

    @SerializedName(ModelConstants.FIELD_SOURCES)
    private ArrayList<String> mSources;

    @SerializedName(ModelConstants.FIELD_UNITS)
    private Unit mUnit;

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public String getDarkSkyUnavailable() {
        return mDarkSkyUnavailable;
    }

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public ArrayList<String> getDarkSkyStations() {
        return mDarkSkyStations;
    }

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public ArrayList<String> getDataPointStations() {
        return mDataPointStations;
    }

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public ArrayList<String> getISDStations() {
        return mISDStations;
    }

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public ArrayList<String> getLampStations() {
        return mLampStations;
    }

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public ArrayList<String> getMadisStations() {
        return mMadisStations;
    }

    /**
     * The presence of this property indicates that the Dark Sky data source supports the given
     * location, but a temporary error (such as a radar station being down for maintenance) has made
     * the data unavailable (optional).
     *
     * @return      mDarkSkyUnavailable
     */
    @Nullable
    public ArrayList<String> getMetarStations() {
        return mMetarStations;
    }

    /**
     * The presence of this property indicates that data from api.met.no was utilized in order to
     * facilitate this request (as per their license agreement) (optional).
     *
     * @return      mMetnoLicense
     */
    @Nullable
    public String getMetnoLicense() {
        return mMetnoLicense;
    }

    /**
     * This property contains an array of IDs for each data source utilized in servicing this request
     * (required).
     *
     * @return      mSources
     */
    public ArrayList<String> getSources() {
        return mSources;
    }

    /**
     * Indicates the units which were used for the data in this request (required).
     *
     * @return      mUnit
     */
    public Unit getUnit() {
        return mUnit;
    }
}
