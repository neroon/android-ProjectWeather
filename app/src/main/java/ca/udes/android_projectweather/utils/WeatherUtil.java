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

package ca.udes.android_projectweather.utils;

import android.content.Context;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.util.Locale;
import java.util.HashMap;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.WeatherApplication;

/**
 * Helper class for performing calculation and parsing some values
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public final class WeatherUtil {

    private static Context mContext = WeatherApplication.getAppContext();
    /**
     * Default constructor.
     *
     */
    private WeatherUtil() {}

    /**
     * Get day of the week for the forecast display
     *
     * @return      dateFormat.format(c.getTime()
     * @param       timestamp
     */
    public static String getWeekDay(Integer timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp * 1000L);
        TimeZone timeZone = TimeZone.getDefault();
        c.add(Calendar.MILLISECOND, timeZone.getOffset(c.getTimeInMillis()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        return dateFormat.format(c.getTime());
    }

    /**
     * Get day of the week for the forecast display
     *
     * @return      dateFormat.format(c.getTime()
     * @param       timestamp
     */
    public static String getDate(Integer timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd", Locale.getDefault());
        return dateFormat.format(c.getTime());
    }

    /**
     * Get formatted local time for the sunrise/sunset.
     *
     * @return      sdf.format(date);
     * @param       unixSeconds
     */
    public static String getTime(Integer unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    /**
     * Get String of temperature from double.
     *
     * @return      String.valueOf(tempInt) + getUnits(true)
     * @param       tempDouble
     * @param       isMetric
     * @return      String.valueOf(tempInt) + getUnits(false)
     *
     * @see WeatherUtil#getUnits
     */
    public static String getTempString(Double tempDouble, boolean isMetric) {
        if (tempDouble != null) {
            int tempInt = (int) Math.round(tempDouble);
            if (isMetric) {
                return String.valueOf(tempInt) + getUnits(true);
            }
            else {
                return String.valueOf(tempInt) + getUnits(false);
            }
        }
        return null;
    }

    /**
     * Get Units symbol.
     *
     * @return      mContext
     * @param       isMetric
     */
    private static String getUnits(boolean isMetric) {
        return isMetric ? mContext.getString(R.string.weather_unit_metric) :
                mContext.getString(R.string.weather_unit_imp);
    }

    /**
     * Get ISO country code for use in API call.
     *
     * @return      countries.get(countryName)
     * @param       countryName
     */
    public static String getCountry(String countryName) {
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale locale = new Locale("en", iso);
            countries.put(locale.getDisplayCountry(), iso);
        }
        return countries.get(countryName);
    }

    /**
     * Get weather icon based on weather name.
     *
     * @return
     * @param
     */
    public static String getWeatherIcon(int weatherId) {
        int id = weatherId / 100;
        String iconText = "";

        if (id == 8) {
            iconText = mContext.getString(R.string.weather_sunny);
        } else {
            switch(id) {
                case 2:
                    iconText = mContext.getString(R.string.weather_thunder);
                    break;
                case 3:
                    iconText = mContext.getString(R.string.weather_drizzle);
                    break;
                case 7:
                    iconText = mContext.getString(R.string.weather_foggy);
                    break;
                case 8:
                    iconText = mContext.getString(R.string.weather_cloudy);
                    break;
                case 6:
                    iconText = mContext.getString(R.string.weather_snowy);
                    break;
                case 5:
                    iconText = mContext.getString(R.string.weather_rainy);
                    break;
            }
        }
        return iconText;
    }

    public static String getHumidityIcon() {
        return mContext.getString(R.string.weather_humidity);
    }

    public static String getPressureIcon() {
        return mContext.getString(R.string.weather_barometer);
    }

    public static String getWindIcon() {
        return mContext.getString(R.string.weather_wind);
    }

    public static String getSunriseIcon() {
        return mContext.getString(R.string.weather_sunrise);
    }

    public static String getSunsetIcon() {
        return mContext.getString(R.string.weather_sunset);
    }
}
