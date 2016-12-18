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
 * A data wind object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Wind implements Serializable {

    @Nullable
    @SerializedName(ModelConstants.FIELD_SPEED)
    private Double mWindSpeed;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DEG)
    private Double mWindDeg;

    /**
     * The wind speed in meter/sec by default (otherwise Metric: meter/sec and Imperial: miles/hour
     * (optional).
     *
     * @return      mWindSpeed
     */
    @Nullable
    public Double getWindSpeed() {
        return mWindSpeed;
    }

    /**
     * The wind direction in degrees (optional).
     *
     * @return      mWindDeg
     */
    @Nullable
    public Double getWindDeg() {
        return mWindDeg;
    }
}
