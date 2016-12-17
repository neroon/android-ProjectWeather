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
import java.util.Date;

/**
 * A data point object contains various properties, each representing the average (unless otherwise
 * specified) of a particular weather phenomenon occurring during a period of time: an instant in
 * the case of currently, a minute for minutely, an hour for hourly, and a day for daily.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class DataPoint implements Serializable {

    @Nullable
    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE)
    private Double mApparentTemperature;

    @Nullable
    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MAX)
    private Double mApparentTemperatureMax;

    @Nullable
    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MAX_TIME)
    private Date mApparentTemperatureMaxTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MIN)
    private Double mApparentTemperatureMin;

    @Nullable
    @SerializedName(ModelConstants.FIELD_APPARENT_TEMPERATURE_MIN_TIME)
    private Date mApparentTemperatureMinTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_CLOUD_COVER)
    private Double mCloudCover;

    @Nullable
    @SerializedName(ModelConstants.FIELD_DEW_POINT)
    private Double mDewPoint;

    @Nullable
    @SerializedName(ModelConstants.FIELD_HUMIDITY)
    private Double mHumidity;

    @Nullable
    @SerializedName(ModelConstants.FIELD_ICON)
    private Icon mIcon;

    @Nullable
    @SerializedName(ModelConstants.FIELD_MOON_PHASE)
    private Double mMoonPhase;

    @Nullable
    @SerializedName(ModelConstants.FIELD_NEAREST_STORM_BEARING)
    private Double mNearestStormBearing;

    @Nullable
    @SerializedName(ModelConstants.FIELD_NEAREST_STORM_DISTANCE)
    private Double mNearestStormDistance;

    @Nullable
    @SerializedName(ModelConstants.FIELD_OZONE)
    private Double mOzone;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRECIP_ACCUMULATION)
    private Double mPrecipAccumulation;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRECIP_INTENSITY)
    private Double mPrecipIntensity;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRECIP_INTENSITY_MAX)
    private Double mPrecipIntensityMax;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRECIP_INTENSITY_MAX_TIME)
    private Date mPrecipIntensityMaxTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRECIP_PROBABILITY)
    private Double mPrecipProbability;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRECIP_TYPE)
    private PrecipitationType mPrecipitationType;

    @Nullable
    @SerializedName(ModelConstants.FIELD_PRESSURE)
    private Double mPressure;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SUMMARY)
    private String mSummary;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SUNRISE_TIME)
    private Date mSunriseTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_SUNSET_TIME)
    private Date mSunsetTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMPERATURE)
    private Double mTemperature;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MAX)
    private Double mTemperatureMax;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MAX_TIME)
    private Date mTemperatureMaxTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MIN)
    private Double mTemperatureMin;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TEMPERATURE_MIN_TIME)
    private Date mTemperatureMinTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_TIME)
    private Date mTime;

    @Nullable
    @SerializedName(ModelConstants.FIELD_VISIBILITY)
    private Double mVisibility;

    @Nullable
    @SerializedName(ModelConstants.FIELD_WIND_BEARING)
    private Double mWindBearing;

    @Nullable
    @SerializedName(ModelConstants.FIELD_WIND_SPEED)
    private Double mWindSpeed;

    /**
     * The apparent(or "feels like") temperature in degrees Fahrenheit (optional, not on daily).
     *
     * @return      mApparentTemperature
     */
    @Nullable
    public Double getApperentTemperature() {
        return mApparentTemperature;
    }

    /**
     * The maximum value of apparentTemperature during a given day (optional, only on daily).
     *
     * @return      mApparentTemperatureMax
     */
    @Nullable
    public Double getApperentTemperatureMax() {
        return mApparentTemperatureMax;
    }

    /**
     * The UNIX time of when apparentTemperatureMax occurs during a given day (optional,
     * only on daily).
     *
     * @return      mApparentTemperatureMaxTime
     */
    @Nullable
    public Date getApperentTemperatureMaxTime() {
        return mApparentTemperatureMaxTime;
    }

    /**
     * The minimum value of apparentTemperature during a given day (optional, only on daily).
     *
     * @return      mApparentTemperatureMin
     */
    @Nullable
    public Double getApperentTemperatureMin() {
        return mApparentTemperatureMin;
    }

    /**
     * The UNIX time of when apparentTemperatureMin occurs during a given day (optional,
     * only on daily).
     *
     * @return      mApparentTemperatureMinTime
     */
    @Nullable
    public Date getApperentTemperatureMinTime() {
        return mApparentTemperatureMinTime;
    }

    /**
     * The percentage of sky occluded by clouds, between  and, inclusive (optional).
     *
     * @return      mCloudCover
     */
    @Nullable
    public Double getCloudCover() {
        return mCloudCover;
    }

    /**
     * The dew point in degrees Fanhrenheit (optional)
     *
     * @return      mDewPoint
     */
    @Nullable
    public Double getDewPoint() {
        return mDewPoint;
    }

    /**
     * The relative humidity, between 0 and 1, incluse (optional).
     *
     * @return      mHumidity
     */
    @Nullable
    public Double getHumidity() {
        return mHumidity;
    }

    /**
     * A machine-readable text summary of this data point, suitable for selecting an icon for
     * display. If defined, this property will have one of the following values : clear-day,
     * clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night.
     * (Developers should ensure that a sensible default is defined, as additional values, such as
     * hail, thunderstorm, or tornado, may be defined in the future.)
     *
     * @return      mIcon
     */
    @Nullable
    public Icon getIcon() {
        return mIcon;
    }

    /**
     * The fractional part of the lunation number during the given day: a value of 0 corresponds to
     * a new moon, 0.25 to a first quarter moon, 0.5 to a full moon, and 0.75 to a last quarter moon.
     * (The ranges in between these represent waxing crescent, waxing gibbous, waning gibbous, and
     * waning crescent moons, respectively.) (optional, only on daily).
     *
     * @return      mMoonPhase
     */
    @Nullable
    public Double getMoonPhase() {
        return mMoonPhase;
    }

    /**
     * The approximate direction of the nearest storm in degrees, with true north at 0° and
     * progressing clockwise. (If nearestStormDistance is zero, then this value will not be defined)
     * (optional, only on currently).
     *
     * @return      mNearestStormBearing
     */
    @Nullable
    public Double getNearestStormBearing() {
        return mNearestStormBearing;
    }

    /**
     * The approximate distance to the nearest storm in miles. (A storm distance of 0 doesn't
     * necessraily refer to a storm at the requested location, but rather a storm in the vicinity of
     * that location.) (optional, only on currently).
     *
     * @return      mNearestStormDistance
     */
    @Nullable
    public Double getNearestStormDistance() {
        return mNearestStormDistance;
    }

    /**
     * The columnar density of total atmospheric ozone at the given time in Dobson units (optional)
     *
     * @return      mOzone
     */
    @Nullable
    public Double getOzone() {
        return mOzone;
    }

    /**
     * The amount of snowfall accumulation expected to occur, in inches. (If no snowfall is expected,
     * this property will not be defined.) (optional, only on hourly and daily).
     *
     * @return      mPrecipAccumulation
     */
    @Nullable
    public Double getPrecipAccumulation() {
        return mPrecipAccumulation;
    }

    /**
     * The intensity (in inches of liquid water per hour) of precipitation occurring at the given
     * time. This value is conditional on probability (that is, assuming any precipitation occurs at
     * all) for minutely data points, and unconditional otherwise (optional).
     *
     * @return      mPrecipIntensity
     */
    @Nullable
    public Double getPrecipIntensity() {
        return mPrecipIntensity;
    }

    /**
     * The maximum value of precipIntensity during a given day (optional, only on daily).
     *
     * @return      mPrecipIntensityMax
     */
    @Nullable
    public Double getPrecipIntensityMax() {
        return mPrecipIntensityMax;
    }

    /**
     * The UNIX time of when precipIntensityMax occurs during a given day (optional, only on daily).
     *
     * @return      mPrecipIntensityMaxTime
     */
    @Nullable
    public Date getPrecipIntensityMaxTime() {
        return mPrecipIntensityMaxTime;
    }

    /**
     * The probability of preciptation occurring, between 0 and 1, inclusive (optional).
     *
     * @return      mPrecipProbability
     */
    @Nullable
    public Double getPrecipProbability() {
        return mPrecipProbability;
    }

    /**
     * The type of precipitation occurring at the given time.If defined, this property will have one
     * of the follow values: "rain", "snow", or "sleet" (which refers to each of freezing rain, ice
     * pellets, and "wintery mix"). (If precipIntensity is zero, then this property will not be
     * defined.) (optional).
     *
     * @return      mPrecipitationType
     */
    @Nullable
    public PrecipitationType getPrecipitationType() {
        return mPrecipitationType;
    }

    /**
     * The sea-level air pressure in millibars (optional).
     *
     * @return      mPressure
     */
    @Nullable
    public Double getPressure() {
        return mPressure;
    }

    /**
     * A human-readable text summary of this data point. (This property has millions of possible
     * values, so don't use it for automated purposes: use the icon property, instead!) (optional).
     *
     * @return      mSummary
     */
    @Nullable
    public String getSummary() {
        return mSummary;
    }

    /**
     * The UNIX time of when the sun will rise during a given day (optional, only on daily).
     *
     * @return      mSunriseTime
     */
    @Nullable
    public Date getSunriseTime() {
        return mSunriseTime;
    }

    /**
     * The UNIX time of when the sun will set during a given day (optional, only on daily).
     *
     * @return      mSunsetTime
     */
    @Nullable
    public Date getSunsetTime() {
        return mSunsetTime;
    }

    /**
     * The air temperature in degrees Fanhrenheit (optional, not on daily).
     *
     * @return      mTemperature
     */
    @Nullable
    public Double getTemperature() {
        return mTemperature;
    }

    /**
     * The maximum value of temperature during a given day (optional, only on daily).
     *
     * @return      mTemperatureMax
     */
    @Nullable
    public Double getTemperatureMax() {
        return mTemperatureMax;
    }

    /**
     * The UNIX time of when temperatureMax occurs during a given day (optional, only on daily).
     *
     * @return      mTemperatureMaxTime
     */
    @Nullable
    public Date getTemperatureMaxTime() {
        return mTemperatureMaxTime;
    }

    /**
     * The minimum value of temperature during a given day (optional, only on daily).
     *
     * @return      mTemperatureMin
     */
    @Nullable
    public Double getTemperatureMin() {
        return mTemperatureMin;
    }

    /**
     * The UNIX time at which this data point begins. minutely data point are always aligned to the
     * top of the minute, hourly data point objetcs to the top of the hour, and daily data point
     * objetcs to midnight of the day, all according to the local time zone (required).
     *
     * @return      mTime
     */
    @Nullable
    public Date getTime() {
        return mTime;
    }

    /**
     * The average visibility in miles, capped at 10 miles.
     *
     * @return      mVisibility
     */
    @Nullable
    public Double getVisibility() {
        return mVisibility;
    }

    /**
     * The direction that the wind is coming from in degrees, with true north at 0° and processing
     * clockwise.(If windSpeed is zero, the this value will not be defined.) (optional).
     *
     * @return      mWindBearing
     */
    @Nullable
    public Double getWindBearing() {
        return mWindBearing;
    }

    /**
     * The wind speed in miles per hour (optional).
     *
     * @return      mWindSpeed
     */
    @Nullable
    public Double getWindSpeed() {
        return mWindSpeed;
    }
}
