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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ca.udes.android_projectweather.models.Unit;
import ca.udes.android_projectweather.utils.Constants;
import ca.udes.android_projectweather.models.Language;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import okhttp3.Cache;

/**
 * Used to configure the ForecastClient. The only required parameter is the API key.
 *
 * @version 1.0
 *
 * @see ForecastClient#create(ForecastConfiguration°
 */

public class ForecastConfiguration {

    private final String TAG = getClass().getSimpleName();

    @NonNull
    private String mApiKey;
    @Nullable
    private Language mDefaultLanguage;
    @Nullable
    private Unit mDefaultUnit;
    @Nullable
    private List<String> mDefaultExcludeList;
    @Nullable
    private File mCacheDirectory;
    private int mCacheSize;
    private int mCacheMaxAge;
    private int mConnectionTimeout;

    /**
     * Constructor
     *
     * @param       builder
     */
    private ForecastConfiguration(Builder builder) {

        mApiKey = builder.apiKey;
        mDefaultLanguage = builder.defaultLanguage;
        mDefaultUnit = builder.defaultUnit;

        if (builder.defaultExcludeList != null) {
            Log.d(TAG, "ForecastConfiguration added exclude list items");
            mDefaultExcludeList = new ArrayList<>();
            mDefaultExcludeList.addAll(builder.defaultExcludeList);
        }

        mCacheDirectory = builder.cacheDirectory;
        mCacheSize = builder.cacheSize;
        mCacheMaxAge = builder.cacheMaxAge;
        mConnectionTimeout = builder.connectionTimeout;
    }

    /**
     * The required API KEY (required).
     *
     * @return      mApiKey
     */
    @NonNull
    public String getApiKey() {
        return mApiKey;
    }

    /**
     * The default language in the request (optional).
     *
     * @return      mDefaultLanguage
     */
    @Nullable
    public Language getDefaultLanguage() {
        return mDefaultLanguage;
    }

    /**
     * The default unit in the request (optional).
     *
     * @return      mDefaultUnit
     */
    @Nullable
    public Unit getDefaultUnit() {
        return mDefaultUnit;
    }

    /**
     * The parameter that you can exclude in the request (optional).
     *
     * @return      mDefaultExcludeList
     */
    @Nullable
    public List<String> getDefaultExcludeList() {
        return mDefaultExcludeList;
    }

    /**
     * (optional).
     *
     * @return      Cache(mCacheDirectory, mCacheSize)
     */
    @Nullable
    public Cache getCache() {
        if (mCacheDirectory == null) {
            return null;
        }
        return new Cache(mCacheDirectory, mCacheSize);
    }

    /**
     * (optional).
     *
     * @return      mCacheMaxAge
     */
    @Nullable
    public int getCacheMaxAge() {
        return mCacheMaxAge;
    }

    /**
     * (optional).
     *
     * @return      mConnectionTimeout
     */
    @Nullable
    public int getConnectionTimeout() {
        return mConnectionTimeout;
    }

    /**
     * Building the configuration for the request.
     *
     * @version 1.0
     */
    public static class Builder {

        @NonNull
        private String apiKey;
        @Nullable
        private Language defaultLanguage;
        @Nullable
        private Unit defaultUnit;
        @Nullable
        private List<String> defaultExcludeList;
        @Nullable
        private File cacheDirectory;
        private int cacheSize =  Constants.DEFAULT_CACHE_SIZE;
        private int cacheMaxAge =  Constants.DEFAULT_CACHE_AGE;
        private int connectionTimeout =  Constants.DEFAULT_CONNECTION_TIMEOUT;

        /**
         * API KEY.
         *
         * @param      apiKey
         */
        public Builder(@NonNull String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * Language.
         *
         * @param       defaultLanguage
         * @return      this
         */
        public Builder setDefaultLanguage(@Nullable Language defaultLanguage) {
            this.defaultLanguage = defaultLanguage;
            return this;
        }

        /**
         * Unit.
         *
         * @param       defaultUnit
         * @return      this
         */
        public Builder setDefaultUnit(@Nullable Unit defaultUnit) {
            this.defaultUnit = defaultUnit;
            return this;
        }

        /**
         * Exclude list.
         *
         * @param       defaultExcludeList
         * @return      this
         */
        public Builder setDefaultExcludeList(@Nullable List<String> defaultExcludeList) {
            this.defaultExcludeList = defaultExcludeList;
            return this;
        }

        /**
         * Cache directory.
         *
         * @param       cacheDirectory
         * @return      this
         */
        public Builder setCacheDirectory(@Nullable File cacheDirectory) {
            this.cacheDirectory = cacheDirectory;
            return this;
        }

        /**
         * Cache size.
         *
         * @param       cacheSize
         * @return      this
         */
        public Builder setCacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        /**
         * Cache max age.
         *
         * @param       cacheMaxAge
         * @return      this
         */
        public Builder setCacheMaxAge(int cacheMaxAge) {
            this.cacheMaxAge = cacheMaxAge;
            return this;
        }

        /**
         * Connection timeout.
         *
         * @param       connectionTimeout
         * @return      this
         */
        public Builder setConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * Build ForecastConfiguration with new parameters.
         *
         * @return      ForecastConfiguration(this)
         */
        public ForecastConfiguration build() {
            return new ForecastConfiguration(this);
        }
    }
}
