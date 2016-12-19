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
package ca.udes.android_projectweather.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.preference.Preference;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;

import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.utils.Constants;
import ca.udes.android_projectweather.managers.SharedPreferenceManager;

/**
 * ForecastDailyFragment.
 *
 * @version 1.0
 */
public class SettingsFragment extends PreferenceFragment {

    public final static int PREFS_UPDATED = 1;
    private Preference prefCity;
    private Preference prefCountry;
    private Preference prefTemp;
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    private SharedPreferenceManager prefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        prefs = SharedPreferenceManager.from(getActivity());
        prefCity = getPreferenceScreen().findPreference(Constants.PREF_CITY);
        prefCountry = getPreferenceScreen().findPreference(Constants.PREF_COUNTRY);
        prefTemp = getPreferenceScreen().findPreference(Constants.PREF_UNIT);

        setCitySummary();
        setCountrySummary();
        setTempSummary();
        initPrefListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs.registerChangeListener(prefListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.unregisterChangeListener(prefListener);
    }

    /**
     * Accessor for city summary
     *
     */
    private void setCitySummary() {
        String city = prefs.getCity();
        if (TextUtils.isEmpty(city)) {
            prefCity.setSummary(Constants.PREF_CITY_SUMMARY_NULL);
        }
        else {
            prefCity.setSummary(city);
        }
    }

    /**
     * Accessor for country summary
     *
     */
    private void setCountrySummary() {
        prefCountry.setSummary(prefs.getCountry());
    }

    /**
     * Accessor for temperature summary
     *
     */
    private void setTempSummary() {
        String unit;

        if (prefs.getUnit() != null) {
            if(prefs.getUnit().equalsIgnoreCase("c")) {
                unit = " Métrique \u00B0" + prefs.getUnit().toUpperCase() + ", km/h";
            } else {
                unit = "Impérial \u00B0" + prefs.getUnit().toUpperCase() + ", mph";
            }
        } else {
            unit = "";
        }
        prefTemp.setSummary(unit);
    }

    /**
     * Initialise preference listener
     *
     */
    private void initPrefListener() {
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                getActivity().setResult(PREFS_UPDATED);
                switch (key) {
                    case Constants.PREF_CITY:
                        setCitySummary();
                        break;

                    case Constants.PREF_COUNTRY:
                        setCountrySummary();
                        break;

                    case Constants.PREF_UNIT:
                        setTempSummary();
                        break;
                }
            }
        };
    }
}
