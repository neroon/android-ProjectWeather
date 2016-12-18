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

package ca.udes.android_projectweather.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ca.udes.android_projectweather.utils.Constants;
import ca.udes.android_projectweather.utils.WeatherUtil;

/**
 * SharedPreferences to stock values.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class SharedPreferenceManager {

    private SharedPreferences prefs;

    private SharedPreferenceManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceManager from(Context context) {
        return new SharedPreferenceManager(context);
    }

    public void registerChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void setLocationToggle(boolean locationToggle){
        prefs.edit().putBoolean(Constants.PREF_LOCATION_TOGGLE, locationToggle).apply();
    }

    public boolean getLocationToggle() {
        return prefs.getBoolean(Constants.PREF_LOCATION_TOGGLE, true);
    }

    public String getCity() {
        return prefs.getString(Constants.PREF_CITY, Constants.PREF_CITY_DEFAULT);
    }

    public void setCity(String city) {
        prefs.edit().putString(Constants.PREF_CITY, city).apply();
    }

    public String getCountry() {
        return prefs.getString(Constants.PREF_COUNTRY, Constants.PREF_COUNTRY_DEFAULT);
    }

    public void setCountry(String country) {
        prefs.edit().putString(Constants.PREF_COUNTRY, country).apply();
    }

    public boolean isUnitMetric(){
        return getUnit().equalsIgnoreCase(Constants.PREF_UNIT_METRIC);
    }

    public String getUnit() {
        return prefs.getString(Constants.PREF_UNIT, Constants.PREF_UNIT_METRIC);
    }

    public void setUnit(String unit) {
        prefs.edit().putString(Constants.PREF_UNIT, unit).apply();
    }

    public String getSelectedCity(){
        return getCity() + "," + WeatherUtil.getCountry(getCountry());
    }

    public String getWindSpeedUnit(){
        return isUnitMetric() ? Constants.DETAIL_LABEL_WIND_METRIC :
                Constants.DETAIL_LABEL_WIND_IMP;
    }

    public String getTempUnit(){
        return isUnitMetric() ? Constants.API_METRIC : Constants.API_IMPERIAL;
    }
}
