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
package ca.udes.android_projectweather.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import ca.udes.android_projectweather.utils.FontHelper;

/**
 * WeartherIconTextView
 *
 * @version 1.0
 */
public class WeatherIconTextView extends TextView {

    public WeatherIconTextView(Context context){
        super(context, null);
        FontHelper.setWeatherTypeface(context, this);
    }

    public WeatherIconTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        FontHelper.setWeatherTypeface(context, this);
    }

    public WeatherIconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        FontHelper.setWeatherTypeface(context, this);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}
