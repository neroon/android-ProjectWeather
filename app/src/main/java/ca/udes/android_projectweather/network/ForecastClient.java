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

import android.util.Log;
import android.text.TextUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializationContext;

import java.util.Map;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import ca.udes.android_projectweather.models.Unit;
import ca.udes.android_projectweather.models.Language;
import ca.udes.android_projectweather.utils.Constants;
import ca.udes.android_projectweather.models.ForecastResponse;

/**
 * Class for interacting with Dark Sky API.
 *
 * @version 1.0
 */
public final class ForecastClient {

    private final String TAG = getClass().getSimpleName();

    @Nullable
    private final Language mLanguage;
    @Nullable
    private final Unit mUnit;
    @Nullable
    private List<String> mExcludeBlocks;
    private final String mApiKey;
    private final String mCacheControl;
    private final ForecastService mService;
    private static ForecastClient mInstance;

    /**
     * Constructor.
     *
     * @param       forecastConfiguration
     *
     * @see ForecastClient#createGson
     * @see ForecastClient#createOkHttpClient
     */
    private ForecastClient(ForecastConfiguration forecastConfiguration) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(createOkHttpClient(forecastConfiguration))
                .build();

        mApiKey = forecastConfiguration.getApiKey();
        mLanguage = forecastConfiguration.getDefaultLanguage();
        mUnit = forecastConfiguration.getDefaultUnit();

        if (forecastConfiguration.getDefaultExcludeList() != null) {
            Log.d(TAG, "exclude list");
            mExcludeBlocks = new ArrayList<>(forecastConfiguration.getDefaultExcludeList());
        }

        CacheControl cacheControl =
                new CacheControl.Builder().maxAge(forecastConfiguration.getCacheMaxAge(), TimeUnit.SECONDS)
                .build();

        mCacheControl = cacheControl.toString();
        mService = retrofit.create(ForecastService.class);
    }

    /**
     * Configure the ForecastClient.
     *
     * @param       forecastConfiguration {@link ForecastConfiguration.Builder}
     */
    public static void create(@NonNull ForecastConfiguration forecastConfiguration) {
        mInstance = new ForecastClient(forecastConfiguration);
    }

    /**
     * Accessor.
     *
     * @return       mInstance
     */
    public static ForecastClient getInstance() {
        if (mInstance == null) {
            throw new AssertionError("create() is missing !");
        }
        return mInstance;
    }

    /**
     * Configure the http request.
     *
     * @return       client
     * @param        forecastConfiguration
     *
     * @see ForecastConfiguration
     */
    private static OkHttpClient createOkHttpClient(ForecastConfiguration forecastConfiguration) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient();
        client = client.newBuilder()
                .addInterceptor(interceptor)
                .cache(forecastConfiguration.getCache())
                .connectTimeout(forecastConfiguration.getConnectionTimeout(), TimeUnit.SECONDS)
                .readTimeout(forecastConfiguration.getConnectionTimeout(), TimeUnit.SECONDS)
                .build();
        return client;
    }

    /**
     * Configure the gson.
     *
     * @return       builder.create()
     */
    private static Gson createGson() {
        final long MILLIS = 1000;
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong() * MILLIS);
            }
        });
        return builder.create();
    }

    /**
     * Synchronous forecastResponse call.
     *
     * @return      getForecastSync(latitude, longitude, null)
     * @param       latitude
     * @param       longitude
     * @throws      IOException
     *
     * @see ForecastResponse
     */
    @SuppressWarnings("unused")
    public Response<ForecastResponse> getForecastSync(double latitude, double longitude) throws IOException {
        return getForecastSync(latitude, longitude, null);
    }

    /**
     * Synchronous forecastResponse call.
     *
     * @return      getForecastSync(latitude, longitude, null, null, null, false)
     * @param       latitude
     * @param       longitude
     * @param       time
     * @throws      IOException
     *
     * @see ForecastResponse
     */
    public Response<ForecastResponse> getForecastSync(double latitude, double longitude, @Nullable Integer time)
            throws IOException {
        return getForecastSync(latitude, longitude, time, null, null, null, false);
    }

    /**
     * Synchronous forecastResponse call.
     *
     * @return      forecastCall.execute()
     * @param       latitude
     * @param       longitude
     * @param       time
     * @param       language
     * @param       unit
     * @param       excludeList
     * @param       extendHourly
     * @throws      IOException
     *
     * @see ForecastResponse
     */
    public Response<ForecastResponse> getForecastSync(double latitude, double longitude, @Nullable Integer time,
                                                      @Nullable Language language, @Nullable Unit unit,
                                                      @Nullable List<String> excludeList, boolean extendHourly)
            throws IOException {
        Call<ForecastResponse> forecastCall = mService.getForecast(mApiKey, getLocation(latitude, longitude, time),
                getQueryMapParameters(language, unit, excludeList, extendHourly), mCacheControl);

        return forecastCall.execute();
    }

    /**
     * Asynchronous forecastResponse call.
     *
     * @return      getForecast(latitude, longitude, null, forecastCallback)
     * @param       latitude
     * @param       longitude
     * @param       forecastCallback
     *
     * @see ForecastResponse
     */
    @SuppressWarnings("unused")
    public Call<ForecastResponse> getForecast(double latitude, double longitude,
                                              @NonNull Callback<ForecastResponse> forecastCallback) {
        return getForecast(latitude, longitude, null, forecastCallback);
    }

    /**
     * Asynchronous forecastResponse call.
     *
     * @return      getForecast(latitude, longitude, null, null, null, false, forecastCallback)
     * @param       latitude
     * @param       longitude
     * @param       time
     * @param       forecastCallback
     *
     * @see ForecastResponse
     */
    public Call<ForecastResponse> getForecast(double latitude, double longitude, @Nullable Integer time,
                                              @NonNull Callback<ForecastResponse> forecastCallback) {
        return getForecast(latitude, longitude, time, null, null, null, false, forecastCallback);
    }

    /**
     * Asynchronous forecastResponse call.
     *
     * @return      forecastCall
     * @param       latitude
     * @param       longitude
     * @param       time
     * @param       language
     * @param       unit
     * @param       excludeList
     * @param       extendHourly
     * @param       forecastCallback
     *
     * @see ForecastResponse
     */
    public Call<ForecastResponse> getForecast(double latitude, double longitude, @Nullable Integer time,
                                              @Nullable Language language, @Nullable Unit unit,
                                              @Nullable List<String> excludeList, boolean extendHourly,
                                              @NonNull Callback<ForecastResponse> forecastCallback) {

        Call<ForecastResponse> forecastCall = mService.getForecast(mApiKey,
                getLocation(latitude, longitude, time), getQueryMapParameters(language, unit, excludeList,
                        extendHourly), mCacheControl);

        forecastCall.enqueue(forecastCallback);

        return forecastCall;
    }

    /**
     * Get the latitude and longitude for the request.
     *
     * @return       location
     * @param        latitude
     * @param        longitude
     * @param        time
     */
    private static String getLocation(double latitude, double longitude, @Nullable Integer time) {
        String location = String.valueOf(latitude) + "," + String.valueOf(longitude);
        if (time != null) {
            location += "," + time.toString();
        }
        return location;
    }

    /**
     * Configure queryMapParameters
     *
     * @return       queryMap
     */
    private Map<String, String> getQueryMapParameters(@Nullable Language language, @Nullable Unit unit,
                                                      @Nullable List<String> excludeBlocks, boolean extendHourly) {
        Map<String, String> queryMap = new HashMap<>();

        if (language != null) {
            Log.d(TAG, "language");
            queryMap.put(Constants.OPTIONS_LANGUAGE, language.getText());
        } else if (mLanguage != null) {
            Log.d(TAG, "mLanguage");
            queryMap.put(Constants.OPTIONS_LANGUAGE, mLanguage.getText());
        }

        if (unit != null) {
            Log.d(TAG, "unit");
            queryMap.put(Constants.OPTIONS_UNIT, unit.getText());
        } else if (mUnit != null) {
            Log.d(TAG, "mUnit");
            queryMap.put(Constants.OPTIONS_UNIT, mUnit.getText());
        }

        if (excludeBlocks != null && !excludeBlocks.isEmpty()) {
            Log.d(TAG, "excludeBlocks");
            String exclude = TextUtils.join(",", excludeBlocks);
            Log.d(TAG, "exclude");
            queryMap.put(Constants.OPTIONS_EXCLUDE, exclude);
        } else if (mExcludeBlocks != null && !mExcludeBlocks.isEmpty()) {
            Log.d(TAG, "mExcludeBlocks");
            String exclude = TextUtils.join(",", mExcludeBlocks);
            Log.d(TAG, "exclude");
            queryMap.put(Constants.OPTIONS_EXCLUDE, exclude);
        }

        if (extendHourly) {
            Log.d(TAG, "extendHourly");
            queryMap.put(Constants.OPTIONS_EXTEND, Constants.OPTIONS_EXTEND_HOURLY);
        }

        return queryMap;
    }
}
