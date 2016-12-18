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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializationContext;

import java.util.Date;
import java.lang.reflect.Type;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import ca.udes.android_projectweather.utils.Constants;

/**
 * Class for interacting with OpenWeatherMap API.
 *
 * @version 1.0
 */
public class ApiClient {

    private static ApiInterface mApiInterface;
    private static ApiClient mInstance;

    /**
     * Constructor.
     *
     * @see ApiClient#createGson
     * @see ApiClient#createOkHttpClient
     */
    private ApiClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(createOkHttpClient())
                .build();

        mApiInterface = retrofit.create(ApiInterface.class);
    }

    /**
     * Instance the ApiClient.
     *
     */
    public static void init() {
        if (mInstance == null) {
            mInstance = new ApiClient();
        }
    }

    /**
     * Accessor.
     *
     * @return       mInstance
     */
    public static ApiInterface getApi() {
        if (mInstance == null) {
            throw new AssertionError("init() is missing !");
        }
        return mApiInterface;
    }

    /**
     * Configure the http request.
     *
     * @return       client
     */
    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient();
        client = client.newBuilder()
                .addInterceptor(interceptor)
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
}
