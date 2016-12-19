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

import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.view.LayoutInflater;

import ca.udes.android_projectweather.R;

/**
 * WeartherDetailLayout
 *
 * @version 1.0
 */
public class WeatherDetailLayout extends LinearLayout {

    private WeatherIconTextView mIcon;
    private TextView mLabel;
    private TextView mValue;

    /**
     *  WeatherDetailLayout
     *
     * @param context
     * @param attrs
     */
    public WeatherDetailLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    /**
     * WeatherDetailLayout
     *
     * @param context
     */
    public WeatherDetailLayout(Context context){
        this(context, null);

        setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,16);
        setLayoutParams(params);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_weather_detail, this, true);

        mIcon = (WeatherIconTextView) findViewById(R.id.tv_icon);
        mLabel = (TextView) findViewById(R.id.tv_label);
        mValue = (TextView) findViewById(R.id.tv_value);
    }

    /**
     * Set the icon in the textview.
     *
     * @param icon
     */
    public void setIcon(String icon){
        mIcon.setText(icon);
    }

    /**
     * Set the icon in the textview.
     *
     * @param label
     */
    public void setLabel(String label){
        mLabel.setText(label);
    }

    /**
     * Set the icon in the textview.
     *
     * @param value
     */
    public void setValue(String value){
        mValue.setText(value);
    }
}
