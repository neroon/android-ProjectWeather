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

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * The alerts array contains objects representing the severe weather warnings issued for the requested
 * location by a governmental authority.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class Alerts implements Serializable {

    @SerializedName(ModelConstants.FIELD_DESCRIPTION)
    private String mDescription;

    @SerializedName(ModelConstants.FIELD_EXPIRES)
    private Date mExpires;

    @SerializedName(ModelConstants.FIELD_TITLE)
    private String mTitle;

    @SerializedName(ModelConstants.FIELD_URI)
    private String mUri;

    /**
     * A detailed description of the alert (required).
     *
     * @return      mDescription
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * The UNIX time at which the alert will expire (required).
     *
     * @return      mExpires
     */
    public Date getExpires() {
        return mExpires;
    }

    /**
     * A brief description of the alerrt (required).
     *
     * @return      mTitle
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * An HTTP(S) URI that one may refer to for detailed about the alert (required).
     *
     * @return      mUri
     */
    public String getUri() {
        return mUri;
    }
}
