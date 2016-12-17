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
 * A data block object represents the various weather phenomena occurring over a period of time.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class DataBlock implements Serializable {

    @SerializedName(ModelConstants.FIELD_DATA)
    private ArrayList<DataPoint> mDataPoints;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SUMMARY)
    private String mSummary;

    @Nullable
    @SerializedName(ModelConstants.FIELD_ICON)
    private String mIcon;

    /**
     * An array of data points, ordered by time, which together describe the weather conditions at
     * the requested location over time (required).
     *
     * @return      mDataPoints
     */
    public ArrayList<DataPoint> getDataPoints() {
        return mDataPoints;
    }

    /**
     * A human-readable summary of this data block (optional).
     *
     * @return      mSummary
     */
    @Nullable
    public String getSummary() {
        return mSummary;
    }

    /**
     * A machine-readable text summary of this data block (optional).
     *
     * @return      mIcon
     */
    @Nullable
    public String getIcon() {
        return mIcon;
    }
}
