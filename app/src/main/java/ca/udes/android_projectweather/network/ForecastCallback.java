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

import android.support.annotation.Nullable;

import ca.udes.android_projectweather.models.ForecastResponse;

/**
 * Callback invoked when a forecast is obtained
 *
 * @version 1.0
 */
public class ForecastCallback {

    /**
     * Forecast.
     *
     * @param      forecast
     */
    public void onForecastSuccess(ForecastResponse forecast) {}

    /**
     * Errors.
     *
     * @param      throwable
     */
    public void onForecastError(@Nullable Throwable throwable) {}
}
