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

import android.location.Location;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ca.udes.android_projectweather.models.Icon;
import ca.udes.android_projectweather.models.DataPoint;
import ca.udes.android_projectweather.ForecastApplication;
import ca.udes.android_projectweather.network.ForecastClient;
import ca.udes.android_projectweather.network.ForecastCallback;
import ca.udes.android_projectweather.models.ForecastResponse;
import ca.udes.android_projectweather.models.PrecipitationType;

import java.util.Map;
import java.util.Date;
import java.util.Locale;
import java.util.HashMap;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Helper class for getting the forecast, performing caculations and parsing some values
 *
 * @version 1.0
 */
public final class ForecastHelper {

    private static final String PREVIOUS_LATITUDE = "PREVIOUS_LATITUDE";
    private static final String PREVIOUS_LONGITUDE = "PREVIOUS_LONGITUDE";

    private static final int DISTANCE_TO_ISSUE_NEW_REQUEST = 50;

    /**
     * Default constructor.
     *
     */
    private ForecastHelper() {}

    /**
     * Getting the forecast.
     *
     * @param      location
     * @param      callback
     */
    public static void getForecast(Location location, final ForecastCallback callback) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ForecastApplication.getAppContext());
        double previousLatitude = sharedPreferences.getFloat(PREVIOUS_LATITUDE, 0.0f);
        double previousLongitude = sharedPreferences.getFloat(PREVIOUS_LONGITUDE, 0.0f);

        if (location != null) {
            sharedPreferences.edit()
                    .putFloat(PREVIOUS_LATITUDE, (float) location.getLatitude())
                    .putFloat(PREVIOUS_LONGITUDE, (float) location.getLongitude())
                    .apply();

            float[] results = new float[1];
            double forecastLatitude = location.getLatitude();
            double forecastLongitude = location.getLongitude();

            Location.distanceBetween(previousLatitude, previousLongitude, forecastLatitude, forecastLongitude,
                    results);

            if (results[0] < DISTANCE_TO_ISSUE_NEW_REQUEST) {
                forecastLatitude = previousLatitude;
                forecastLongitude = previousLongitude;
            }

            ForecastClient.getInstance()
                    .getForecast(forecastLatitude, forecastLongitude, new Callback<ForecastResponse>() {
                        @Override
                        public void onResponse(Call<ForecastResponse> forecastCall, Response<ForecastResponse> response) {
                            if (response.isSuccessful()) {
                                callback.onForecastSuccess(response.body());
                            } else {
                                callback.onForecastError(null);
                            }
                        }

                        @Override
                        public void onFailure(Call<ForecastResponse> forecastCall, Throwable t) {
                            callback.onForecastError(t);
                        }
                    });

        }
    }

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
     * Calculation of the snow precipation.
     *
     * @return     location
     * @param      forecast
     */
    public static double calculateSnowAccumulation(ForecastResponse forecast) {
        double snow = 0.0;

        if (forecast != null && forecast.getDaily() != null && forecast.getDaily().getDataPoints() != null) {
            ArrayList<DataPoint> dailyDataPoints = forecast.getDaily().getDataPoints();

            for(DataPoint dataPoint : dailyDataPoints) {
                if (dataPoint.getPrecipitationType() != null &&
                        dataPoint.getPrecipitationType() == PrecipitationType.SNOW) {
                    if(dataPoint.getPrecipAccumulation() != null) {
                        snow += dataPoint.getPrecipAccumulation();
                    }
                }
            }
        }
        return snow;
    }

    /**
     * Get weather icon based on weather name.
     *
     * @return
     * @param
     */
    public static void setWeatherIcon(ForecastResponse forecast) {
    }
}
