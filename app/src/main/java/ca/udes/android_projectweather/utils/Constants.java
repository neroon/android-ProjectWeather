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

/**
 * All constants used for the forecast client
 *
 * @version 1.0
 */
public final class Constants {

    public static final String API_BASE_URL = "https://api.darksky.net/forecast/";

    public static final String OPTIONS_LANGUAGE = "lang";
    public static final String OPTIONS_UNIT = "units";
    public static final String OPTIONS_EXTEND = "extend";
    public static final String OPTIONS_EXTEND_CURRENLY = "currently";
    public static final String OPTIONS_EXTEND_MINUTELY = "minutely";
    public static final String OPTIONS_EXTEND_HOURLY = "hourly";
    public static final String OPTIONS_EXTEND_DAILY = "daily";
    public static final String OPTIONS_EXTEND_ALERTS = "alerts";
    public static final String OPTIONS_EXTEND_FLAGS = "flags";
    public static final String OPTIONS_EXCLUDE = "exclude";
    public static final String OPTIONS_EXCLUDE_CURRENLY = "currently";
    public static final String OPTIONS_EXCLUDE_MINUTELY = "minutely";
    public static final String OPTIONS_EXCLUDE_HOURLY = "hourly";
    public static final String OPTIONS_EXCLUDE_DAILY = "daily";
    public static final String OPTIONS_EXCLUDE_ALERTS = "alerts";
    public static final String OPTIONS_EXCLUDE_FLAGS = "flags";

    public static final int DEFAULT_CACHE_SIZE = 5 * 1024 * 1024; /** 5 MB */
    public static final int DEFAULT_CACHE_AGE = 60 * 60 * 6; /** 6 hours */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 60; /** seconds */

    /**
     * Default constructor
     */
    private Constants() {
    }
}
