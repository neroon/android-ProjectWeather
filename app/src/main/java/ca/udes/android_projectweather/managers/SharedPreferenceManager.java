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

import static android.content.Context.MODE_PRIVATE;

/**
 * SharedPreferences to stock values.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public class SharedPreferenceManager {

    private SharedPreferences prefs;
    private int INDEX = 0;
    private  int INDEXREFRESH =0;

    /**
     * SharedPreferenceManager
     *
     * @param context
     */
    private SharedPreferenceManager(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        //prefs = PreferenceManager.getSharedPreferences(Constants.PREF_CITY_FAV, MODE_PRIVATE)
    }

    /**
     * SharedPreferenceManager
     *
     * @param context
     * @return
     */
    public static SharedPreferenceManager from(Context context) {
        return new SharedPreferenceManager(context);
    }

    /**
     * registerChangeListener
     *
     * @param listener
     */
    public void registerChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * unregisterChangeListener
     *
     * @param listener
     */
    public void unregisterChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener){
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    /**
     * getSharedPrefFav
     *
     * @param context
     * @return
     */
    public static String getSharedPrefFav(Context context){
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("LOCAL", MODE_PRIVATE);
        String save_id = prefs.getString("save_fav", null);

        return save_id;
    }

    /**
     * getSharedPrefName
     *
     * @param context
     * @return
     */
    public static String getSharedPrefName(Context context){
        SharedPreferences settings;
        settings = context.getSharedPreferences("LOCAL", MODE_PRIVATE); //1
        String save_id = settings.getString("save_name", null);
        return save_id;
    }

    /**
     * setLocationToggle
     *
     * @param locationToggle
     */
    public void setLocationToggle(boolean locationToggle){
        prefs.edit().putBoolean(Constants.PREF_LOCATION_TOGGLE, locationToggle).apply();
    }

    /**
     * getLocationToggle
     *
     * @return
     */
    public boolean getLocationToggle() {
        return prefs.getBoolean(Constants.PREF_LOCATION_TOGGLE, true);
    }

    /**
     * setNotificationToggle
     *
     * @param notificationToggle
     */
    public void setNotificationToggle(boolean notificationToggle){
        prefs.edit().putBoolean(Constants.PREF_NOTIFICATION_TOGGLE, notificationToggle).apply();
    }

    /**
     * getNotificationToggle
     *
     * @return
     */
    public boolean getNotificationToggle() {
        return prefs.getBoolean(Constants.PREF_NOTIFICATION_TOGGLE, true);
    }

    /**
     * getCity
     *
     * @return
     */
    public String getCity() {
        return prefs.getString(Constants.PREF_CITY, Constants.PREF_CITY_DEFAULT);
    }

    /**
     * getCity a partir de la liste des favoris
     *
     * @return sherbrooke par defaut si il n'y a pas de PREF_CITY
     */
    public String getCityListFav() {
        String listFav = prefs.getString("save_fav", "");
        listFav = ","+listFav;

        return listFav;
    }

    /**
     * setCity
     *
     * @param city
     */
    public void setCity(String city) {
        prefs.edit().putString(Constants.PREF_CITY, city).apply();
    }

    /**
     * getCountry
     *
     * @return
     */
    public String getCountry() {
        return prefs.getString(Constants.PREF_COUNTRY, Constants.PREF_COUNTRY_DEFAULT);
    }

    /**
     * setCountry
     *
     * @param country
     */
    public void setCountry(String country) {
        prefs.edit().putString(Constants.PREF_COUNTRY, country).apply();
    }

    /**
     * isUnitMetric
     *
     * @return
     */
    public boolean isUnitMetric(){
        return getUnit().equalsIgnoreCase(Constants.PREF_UNIT_METRIC);
    }

    /**
     * getUnit
     *
     * @return
     */
    public String getUnit() {
        return prefs.getString(Constants.PREF_UNIT, Constants.PREF_UNIT_METRIC);
    }

    /**
     * setUnit
     *
     * @param unit
     */
    public void setUnit(String unit) {
        prefs.edit().putString(Constants.PREF_UNIT, unit).apply();
    }

    /**
     * getSelectedCity
     *
     * @return
     */
    public String getSelectedCity(){
        //return getCity() + "," + WeatherUtil.getCountry(getCountry()); //ancien
        return getCity();
    }

    /**
     * getSelectedListFav
     * Principe on affichera toujours en 1er la météo qui est sauvegardé a partir des parametres
     * Ensuite on met a la suite les villes favoris
     * INDEX permet de switcher entre les villes
     * refresh=0
     * fav=1
     *
     * @return
     */
    public String getSelectedCityFav(int refreshOrFav, String ListFav) {
        String finalCity = "";
        if(ListFav==null){
            ListFav="";
        }else{
            ListFav=","+ListFav;
        }
        String listCity =  getCity() + ListFav;
        String[] parts = listCity.split(",");
        int sizeTab = parts.length;

        if(refreshOrFav==0){
                finalCity = parts[INDEXREFRESH];
        }else{
            if(INDEX>=sizeTab){
                INDEX=0;
                finalCity = parts[INDEX];
                INDEXREFRESH=INDEX;
                INDEX=INDEX+1;
            }else{
                finalCity = parts[INDEX];
                INDEXREFRESH=INDEX;
                INDEX=INDEX+1;
            }
        }
        return finalCity;
    }

    /**
     * getWindSpeedUnit
     *
     * @return
     */
    public String getWindSpeedUnit(){
        return isUnitMetric() ? Constants.DETAIL_LABEL_WIND_METRIC :
                Constants.DETAIL_LABEL_WIND_IMP;
    }

    /**
     * getTempUnit
     *
     * @return
     */
    public String getTempUnit(){
        return isUnitMetric() ? Constants.API_METRIC : Constants.API_IMPERIAL;
    }
}
