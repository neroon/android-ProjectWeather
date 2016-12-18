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

package ca.udes.android_projectweather;

import android.app.Application;
import android.content.Context;

import ca.udes.android_projectweather.network.ApiClient;

import com.squareup.leakcanary.LeakCanary;

/**
 * Application class to initialize the ForecastClient
 *
 * @version 1.0
 */
public class WeatherApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        ApiClient.init();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        mContext = this;
    }

    /**
     * Getting the context.
     *
     * @return      mContext
     */
    public static Context getAppContext() {
        return mContext;
    }
}
