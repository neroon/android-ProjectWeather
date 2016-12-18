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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import ca.udes.android_projectweather.models.ModelConstants;

/**
 * A data weather object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class ForecastDailyData implements Serializable {

    @SerializedName(ModelConstants.FIELD_CITY)
    private City mCity;

    @SerializedName(ModelConstants.FIELD_COD)
    private String mCod;

    @SerializedName(ModelConstants.FIELD_MESSAGE)
    private Double mMessage;

    @SerializedName(ModelConstants.FIELD_CNT)
    private Integer mCnt;

    @SerializedName(ModelConstants.FIELD_LIST)
    private List<ListDaily> mList = new ArrayList<>();

    /**
     * An object City which contains the weather conditions at the requested location (required).
     *
     * @return      mCity
     */
    public City getCity() {
        return mCity;
    }

    /**
     * Internal parameter (required).
     *
     * @return      mCod
     */
    public String getCod() {
        return mCod;
    }

    /**
     * Internal parameter (required).
     *
     * @return      mMessage
     */
    public Double getMessage() {
        return mMessage;
    }

    /**
     * The number of lines returned by this API call (required).
     *
     * @return      mCnt
     */
    public Integer getCnt() {
        return mCnt;
    }

    /**
     * An object List which contains the weather conditions at the requested location (required).
     *
     * @return      mList
     */
    public List<ListDaily> getList() {
        return mList;
    }

}
