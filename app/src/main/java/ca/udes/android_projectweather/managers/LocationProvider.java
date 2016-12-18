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

import android.util.Log;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.content.IntentSender;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import ca.udes.android_projectweather.activities.MainActivity;

/**
 * Use google api to get location.
 *
 * @version 1.0
 */
public class LocationProvider implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult>,
        LocationListener {

    private static final String TAG = LocationProvider.class.getSimpleName();

    private Context mContext;
    private Location mLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private CustomLocationListener mCallback;
    private final long FASTEST_INTERVAL = 2000; //2 secs
    private final long UPDATE_INTERVAL = 10 * 1000;  //10 secs
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private LocationSettingsRequest mLocationSettingsRequest;

    /**
     * Constructor for location provider.
     *
     * @param       context
     * @param       listener
     */
    public LocationProvider(Context context, CustomLocationListener listener) {
        mContext = context;
        mCallback = listener;
        buildGoogleApiClient();
        createLocationRequest();
    }

    /**
     * Create an instance of GoogleAPIClient.
     *
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /**
     * Set Up a Location Request
     *
     */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        buildLocationSettingsRequest();
    }

    /**
     * Get Current Location Settings
     *
     */
    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
        checkLocationSettings();
    }

    /**
     * Check wether the current location settings are statisfied
     *
     */
    protected void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mLocationSettingsRequest
                );
        result.setResultCallback(this);
    }

    /**
     * Prompt the User to Change Location Settings
     *
     * @param locationSettingsResult
     */
    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {

        final Status status = locationSettingsResult.getStatus();

        switch (status.getStatusCode()) {

            case LocationSettingsStatusCodes.SUCCESS:
                // All location settings are satisfied. The client can
                // initialize location requests here.
                Log.i(TAG, "All location settings are satisfied.");
                startLocationUpdates();
                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to" +
                        "upgrade location settings ");
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    status.startResolutionForResult((MainActivity) mContext, REQUEST_CHECK_SETTINGS);
                }
                catch (IntentSender.SendIntentException e) {
                    Log.i(TAG, "PendingIntent unable to execute request.");
                }
                break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // Location settings are not satisfied. However, we have no way
                // to fix the settings so we won't show the dialog.
                Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog " +
                        "not created.");
                break;
        }
    }

    /**
     * Request Location Updates
     *
     */
    public void startLocationUpdates() {
        try {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect to GoogleAPIClient
     *
     */
    public void connect() {
        mGoogleApiClient.connect();
    }

    /**
     * Disconnect to GoogleAPIClient
     *
     */
    public void disconnect() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Check if GoogleAPIClient is connected
     *
     */
    public boolean isConnected() {
        return mGoogleApiClient.isConnected();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                mLocation = mLastLocation;
                mCallback.onLocationFetched(mLocation);
            }
            else {
                startLocationUpdates();
            }
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    /**
     * Interrupt update.
     *
     */
    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    /**
     * Define the Location Update Callback
     *
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        mLocation = new Location(location);
        mCallback.onLocationFetched(mLocation);
    }

    public interface CustomLocationListener {
        void onLocationFetched(Location location);
    }
}
