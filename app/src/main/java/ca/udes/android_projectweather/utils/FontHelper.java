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

import android.widget.TextView;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Helper class for applying font to avoid creating typeface from asset each time.
 *
 * @version 1.0
 */
@SuppressWarnings("unused")
public final class FontHelper {

    private static Typeface typeface = null;

    /**
     * Applying weather font on a textview.
     *
     * @param       context
     * @param       textView
     */
    public static void setWeatherTypeface(Context context, TextView textView) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Weather-Fonts.ttf");
        }
        textView.setTypeface(typeface);
    }

    /**
     * Applying roboto light font on a textview.
     *
     * @param       context
     * @param       textView
     */
    public static void setRobotoLightTypeface(Context context, TextView textView) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
        }
        textView.setTypeface(typeface);
    }

    /**
     * Applying roboto regular font on a textview.
     *
     * @param       context
     * @param       textView
     */
    public static void setRobotoRegularTypeface(Context context, TextView textView) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        }
        textView.setTypeface(typeface);
    }

    /**
     * Applying roboto thin font on a textview.
     *
     * @param       context
     * @param       textView
     */
    public static void setRobotoThinTypeface(Context context, TextView textView) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
        }
        textView.setTypeface(typeface);
    }
}
