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
package ca.udes.android_projectweather.activities;

import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ProgressBar;
import android.location.Location;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.views.adapters.ViewPagerAdapter;
import ca.udes.android_projectweather.managers.LocationProvider;
import ca.udes.android_projectweather.network.ApiClient;
import ca.udes.android_projectweather.models.CombinedData;
import ca.udes.android_projectweather.models.weathermodel.WeatherData;
import ca.udes.android_projectweather.models.forecastdailymodel.ForecastDailyData;
import ca.udes.android_projectweather.fragments.ForecastDailyFragment;
import ca.udes.android_projectweather.fragments.SettingsFragment;
import ca.udes.android_projectweather.fragments.WeatherFragment;
import ca.udes.android_projectweather.managers.SharedPreferenceManager;


/**
 * PermissionsActivity
 *
 * @version 1.0
 */
public class MainActivity extends BaseActivity implements LocationProvider.CustomLocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private ViewPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private SharedPreferenceManager prefs;
    private LocationProvider mLocationProvider;
    private Location mLocation;
    private ProgressBar mProgressBar;
    private String mTempUnit = "";
    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeSubscription = new CompositeSubscription();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        initUi();
        initPrefs();
    }

    private void initUi() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(mViewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setVisibility(View.INVISIBLE);
    }

    private void initPrefs() {
        prefs = SharedPreferenceManager.from(this);
        mTempUnit = prefs.getTempUnit();
        getLocationPrefs();
    }

    private void getLocationPrefs() {
        if (prefs.getLocationToggle()) {
            if (mLocationProvider == null) {
                mLocationProvider = new LocationProvider(this, this);
            }
        }
        else {
            getCombinedDataByCity();
        }
    }

    @Override
    public void onLocationFetched(Location location) {
        mLocation = new Location(location);
        getCombinedDataByLocation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    Log.i(TAG, "User agreed to make required location settings changes.");
                    mLocationProvider.startLocationUpdates();
                    break;
                case Activity.RESULT_CANCELED:
                    Log.i(TAG, "User chose not to make required location settings changes.");
                    getCombinedDataByCity();
                    prefs.setLocationToggle(false);
                    break;
            }
        }
        else if (requestCode == SETTINGS_ACTION) {
            if (resultCode == SettingsFragment.PREFS_UPDATED) {
                Log.i(TAG, "User made changes to preferences");
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void getCombinedDataByLocation() {
        showProgressBar();
        Observable<CombinedData> combined = Observable.zip(getWeatherByLocation(),
                getForecastByLocation(), new Func2<WeatherData, ForecastDailyData, CombinedData>() {
                    @Override
                    public CombinedData call(WeatherData weatherData, ForecastDailyData forecastDailyData) {
                        return new CombinedData(weatherData, forecastDailyData);
                    }
                });

        mCompositeSubscription.add(combined
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<CombinedData>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressBar();
                    }

                    @Override
                    public void onNext(CombinedData combinedData) {
                        updateFragments(combinedData);
                        mViewPager.setVisibility(View.VISIBLE);
                        hideProgressBar();
                    }
                }));
    }

    private void getCombinedDataByCity() {
        showProgressBar();
        if (prefs != null) {
            String selectedCity = prefs.getSelectedCity();
            Observable<CombinedData> combined2 = Observable.zip(getWeatherByCity(selectedCity),
                    getForecastByCity(selectedCity), new Func2<WeatherData, ForecastDailyData, CombinedData>() {
                        @Override
                        public CombinedData call(WeatherData weatherData, ForecastDailyData forecastDailyData) {
                            return new CombinedData(weatherData, forecastDailyData);
                        }
                    });

            mCompositeSubscription.add(combined2
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CombinedData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            hideProgressBar();
                        }

                        @Override
                        public void onNext(CombinedData combinedData) {
                            updateFragments(combinedData);
                            mViewPager.setVisibility(View.VISIBLE);
                            hideProgressBar();
                        }
                    }));
        }
    }

    private Observable<WeatherData> getWeatherByLocation() {
        return ApiClient.getApi()
                .getWeatherByLocation(String.valueOf(mLocation.getLatitude()), String.valueOf(mLocation.getLongitude()),
                        mTempUnit);
    }

    private Observable<ForecastDailyData> getForecastByLocation() {
        return ApiClient.getApi()
                .getForecastDailyByLocation(String.valueOf(mLocation.getLatitude()), String.valueOf(mLocation.getLongitude()),
                        mTempUnit);
    }

    private Observable<WeatherData> getWeatherByCity(String selectedCity) {
        return ApiClient.getApi().getWeatherByCity(selectedCity, mTempUnit);
    }

    private Observable<ForecastDailyData> getForecastByCity(String selectedCity) {
        return ApiClient.getApi().getForecastDailyByCity(selectedCity, mTempUnit);
    }

    private void updateFragments(CombinedData combinedData) {
        ((WeatherFragment) mAdapter.getRegisteredFragment(0))
                .updateData(combinedData.getWeatherData());
        ((ForecastDailyFragment) mAdapter.getRegisteredFragment(1))
                .updateData(combinedData.getForecastDailyData());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLocationProvider != null) {
            mLocationProvider.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationProvider != null) {
            mLocationProvider.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mLocationProvider != null) {
            if (mLocationProvider.isConnected()) {
                mLocationProvider.stopLocationUpdates();
            }
        }
    }

    @Override
    protected void onDestroy() {
        mCompositeSubscription.unsubscribe();
        super.onDestroy();
    }

    private void showProgressBar() {
        if (mProgressBar.getVisibility() == View.GONE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    private void hideProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
