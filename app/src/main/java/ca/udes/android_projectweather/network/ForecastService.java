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

import ca.udes.android_projectweather.models.ForecastResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

/**
 * Forecast API endpoint(s)
 *
 * @version 1.0
 */
public interface ForecastService {

    @GET("{apiKey}/{location}")
    Call<ForecastResponse> getForecast(@Path(value = "apiKey", encoded = true) String apiKey,
                                       @Path(value = "location", encoded = true) String location,
                                       @QueryMap Map<String, String> queryParameter,
                                       @Header("Cache-Control") String cacheControl);
}
