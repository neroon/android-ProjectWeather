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

import ca.udes.android_projectweather.models.forecastdailymodel.ForecastDailyData;
import ca.udes.android_projectweather.models.weathermodel.WeatherData;

/**
 * A data coord object contains the latitude and longitude.
 *
 * @version 1.0
 */
public class CombinedData {

    private WeatherData mWeatherData;
    private ForecastDailyData mForecastDailyData;

    /**
     * Constructor.
     *
     * @param      weatherData
     * @param      forecastData
     *
     * @see WeatherData
     * @see ForecastDailyData
     */
    public CombinedData (WeatherData weatherData, ForecastDailyData forecastData) {
        mWeatherData = weatherData;
        mForecastDailyData = forecastData;
    }

    /**
     * The weatherData class.
     *
     * @return      mWeatherData
     */
    public WeatherData getWeatherData() {
        return mWeatherData;
    }

    /**
     * The forecastDailyData class.
     *
     * @return      mForecastDailyData
     */
    public ForecastDailyData getForecastDailyData() {
        return mForecastDailyData;
    }
}
