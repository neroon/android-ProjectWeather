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

/**
 * The several types of measurement units that the Dark Sky API uses.
 *
 * @version 1.0
 */
public enum Unit {

    @SerializedName(ModelConstants.UNIT_US)
    US(ModelConstants.UNIT_US),
    @SerializedName(ModelConstants.UNIT_SI)
    SI(ModelConstants.UNIT_SI),
    @SerializedName(ModelConstants.UNIT_CA)
    CA(ModelConstants.UNIT_CA),
    @SerializedName(ModelConstants.UNIT_UK)
    UK(ModelConstants.UNIT_UK),
    @SerializedName(ModelConstants.UNIT_UK2)
    UK2(ModelConstants.UNIT_UK2),
    @SerializedName(ModelConstants.UNIT_AUTO)
    AUTO(ModelConstants.UNIT_AUTO);

    private final String mText;

    /**
     * The value of an unit.
     *
     * @return      mText
     */
    Unit(String text) {
        mText = text;
    }

    /**
     * Accessor
     *
     * @return      mText
     */
    public String getText() {
        return mText;
    }

    /**
     * The unit corresponding to the value.
     *
     * @return      unit
     * @param       text
     */
    @SuppressWarnings("unused")
    public static Unit unitFromString(String text) {
        if (text != null) {
            for (Unit unit : Unit.values()) {
                if (text.equalsIgnoreCase(unit.mText)) {
                    return unit;
                }
            }
        }
        return null;
    }
}
