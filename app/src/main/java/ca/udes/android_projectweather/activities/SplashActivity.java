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
import android.Manifest;
import android.os.Bundle;
import android.content.Intent;

/**
 * SplashActivity
 *
 * @version 1.0
 */
public class SplashActivity extends PermissionsActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private static int SPLASH_TIMER = 1500;
    private static final int RC_ACCESS_FINE = 123;
    private static final int RC_ACCESS_COARSE = 234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void addPermissions() {
        addPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        addPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    protected void startNextActivity() {
        Log.i(TAG, "startNextActivity");
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
