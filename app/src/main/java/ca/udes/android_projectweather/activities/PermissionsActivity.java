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

import android.os.Build;
import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

import ca.udes.android_projectweather.managers.SharedPreferenceManager;

/**
 * PermissionsActivity
 *
 * @version 1.0
 */
public abstract class PermissionsActivity extends AppCompatActivity {

    private static final String TAG = PermissionsActivity.class.getSimpleName();

    protected static final int REQUEST_PERMISSIONS = 1;
    protected static List<String> permissionList;
    private static String[] PERMISSIONS_STRINGS;
    protected SharedPreferenceManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = SharedPreferenceManager.from(this);
        addPermissions();
        initPermissionsIfNecessary();
    }

    protected abstract void addPermissions();

    private void initPermissionsIfNecessary() {
        Log.i(TAG, "initPermissionsIfNecessary");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionList != null &&
                permissionList.size() > 0) {
            Log.i(TAG, "Permissions required to be checked.");
            PERMISSIONS_STRINGS = new String[permissionList.size()];
            PERMISSIONS_STRINGS = permissionList.toArray(PERMISSIONS_STRINGS);
            checkPermissions();
        }
        else {
            Log.i(TAG, "Permissions not required");
            startNextActivity();
        }
    }

    private void checkPermissions() {
        if (!isPermissionPermitted(permissionList)) {
            Log.i(TAG, "Permissions have NOT been granted. Requesting permissions.");
            requestPermissions();
        }
        else{
            startNextActivity();
        }
    }

    protected void requestPermissions() {
        if (shouldShowPermissionRationale(permissionList)) {
            //Prompt some dialog to show rationale
            ActivityCompat.requestPermissions(this, PERMISSIONS_STRINGS, REQUEST_PERMISSIONS);
        }
        else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STRINGS, REQUEST_PERMISSIONS);
        }
    }

    protected abstract void startNextActivity();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            Log.i(TAG, "Received response for permissions request.");
            if (verifyPermissions(grantResults)) {
                Log.i(TAG, "All required permissions have been granted.");
            }
            else {
                Log.i(TAG, "Required permissions NOT granted.");
                Toast.makeText(this, "Required permissions not granted!", Toast.LENGTH_LONG).show();
                prefs.setLocationToggle(false);
            }
            startNextActivity();
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Source: https://github.com/googlesamples/android-RuntimePermissions/
     */

    private static boolean verifyPermissions(int[] grantResults) {
        if (grantResults.length < 1) {
            return false;
        }
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private boolean isPermissionPermitted(List<String> permissionList) {
        for (String permission : permissionList) {
            if (!(ContextCompat.checkSelfPermission(this, permission)
                    == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    private boolean shouldShowPermissionRationale(List<String> permissionList) {
        for (String permission : permissionList) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return false;
            }
        }
        return true;
    }

    protected void addPermission(String permission) {
        if (permissionList == null) {
            permissionList = new ArrayList<>();
        }
        permissionList.add(permission);
    }
}
