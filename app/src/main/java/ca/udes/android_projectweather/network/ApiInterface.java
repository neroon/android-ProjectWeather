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

package ca.udes.android_projectweather.network;

import ca.udes.android_projectweather.utils.Constants;
import ca.udes.android_projectweather.models.weathermodel.WeatherData;
import ca.udes.android_projectweather.models.forecastdailymodel.ForecastDailyData;

import retrofit2.http.GET;
import retrofit2.http.Query;

import rx.Observable;

/**
 * Weather and Forecast API endpoint(s)
 *
 * @version 1.0
 */
public interface ApiInterface {

    @GET(Constants.API_WEATHER + Constants.API_APP_ID_LANG)
    Observable<WeatherData> getWeatherByCity(@Query("q") String CityName,
                                             @Query("units") String units);

    @GET(Constants.API_FORECAST_DAILY + Constants.API_APP_ID_LANG)
    Observable<ForecastDailyData> getForecastDailyByCity(@Query("q") String CityName,
                                                         @Query("units") String units);

    @GET(Constants.API_WEATHER + Constants.API_APP_ID_LANG)
    Observable<WeatherData> getWeatherByLocation(@Query("lat") String lat,
                                                 @Query("lon") String lon,
                                                 @Query("units") String units);

    @GET(Constants.API_FORECAST_DAILY + Constants.API_APP_ID_LANG)
    Observable<ForecastDailyData> getForecastDailyByLocation(@Query("q") String CityName,
                                                             @Query("lon") String lon,
                                                             @Query("units") String units);
}
