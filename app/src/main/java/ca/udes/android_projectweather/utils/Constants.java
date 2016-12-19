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

import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.WeatherApplication;

/**
 * All constants used for the forecast client
 *
 * @version 1.0
 */
public final class Constants {

    private static Context mContext = WeatherApplication.getAppContext();

    public static final String API_KEY = "c4d2774dfdd4e91a385b1b85b8c62c8f";
    public static final String API_APP_ID = "appid=" + API_KEY;
    public static final String API_APP_ID_LANG = API_APP_ID + "&lang=fr";
    public static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String API_WEATHER = "weather?";
    public static final String API_FORECAST_DAILY = "forecast/daily?";
    public static final String API_METRIC = "metric";
    public static final String API_IMPERIAL = "imperial";

    public static final String TAB_CURRENT = mContext.getString(R.string.tab_current);
    public static final String TAB_FORECAST = mContext.getString(R.string.tab_forecast);

    public static final String PREF_LOCATION_TOGGLE = "key_location_toggle";
    public static final String PREF_CITY = "key_city";
    public static final String PREF_COUNTRY = "key_country";
    public static final String PREF_UNIT = "key_unit";
    public static final String PREF_UNIT_METRIC = "c";
    public static final String PREF_NOTIFICATION_TOGGLE = "key_notification_toggle";

    public static final String PREF_CITY_SUMMARY_NULL = mContext.getString(R.string.pref_summary_city_null);
    public static final String PREF_CITY_DEFAULT = mContext.getString(R.string.pref_default_city);
    public static final String PREF_COUNTRY_DEFAULT = mContext.getString(R.string.pref_default_country);
    public static final String PREF_UNIT_SUMMARY = mContext.getString(R.string.pref_temperature_summary);

    public static final String DETAIL_LABEL_DEGREE = "°";
    public static final String DETAIL_LABEL_VISILIBITY = " km";
    public static final String DETAIL_LABEL_WIND_METRIC = " m/s";
    public static final String DETAIL_LABEL_WIND_IMP = " mph";
    public static final String DETAIL_LABEL_HUMIDITY = "%";
    public static final String DETAIL_LABEL_PRESSURE = " hPa";


    /**
     * Default constructor
     */
    private Constants() {
    }
}
