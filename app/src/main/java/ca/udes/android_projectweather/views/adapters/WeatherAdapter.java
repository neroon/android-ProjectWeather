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
package ca.udes.android_projectweather.views.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.support.v7.widget.RecyclerView;

import ca.udes.android_projectweather.R;

import ca.udes.android_projectweather.utils.Constants;
import ca.udes.android_projectweather.utils.FontHelper;
import ca.udes.android_projectweather.utils.WeatherUtil;
import ca.udes.android_projectweather.views.WeatherDetailLayout;
import ca.udes.android_projectweather.models.weathermodel.WeatherData;
import ca.udes.android_projectweather.managers.SharedPreferenceManager;

/**
 * WeatherAdapter
 *
 * @version 1.0
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private static final String TAG = WeatherAdapter.class.getSimpleName();

    private static int WEATHERVIEW = 0;
    private static int DETAILVIEW = 1;
    private Context mContext;
    private WeatherData mWeatherData = new WeatherData();
    private SharedPreferenceManager prefs;

    public WeatherAdapter(Context context) {
        mContext = context;
        prefs = SharedPreferenceManager.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? WEATHERVIEW : DETAILVIEW;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == WEATHERVIEW) {
            return new WeatherViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_weather, parent, false));
        }
        else if (viewType == DETAILVIEW) {
            return new DetailViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_details, parent, false));
        }
        else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        if (viewHolder.getItemViewType() == WEATHERVIEW) {
            WeatherViewHolder holder = (WeatherViewHolder) viewHolder;
            setWeatherIcon(holder);
            holder.tvCity.setText(mWeatherData.getName());
            if (mWeatherData.getWeather().size() != 0) {
                holder.tvCondition.setText(mWeatherData.getWeather().get(0).getWeatherDescription());
            }
            if (mWeatherData.getMain() != null) {
                holder.tvTemp.setText(WeatherUtil.getTempString(mWeatherData.getMain().getMainTemp(),
                        prefs.isUnitMetric()));
            }
            if (mWeatherData.getMain() != null) {
                holder.tempHigh.setText("MAX: " + WeatherUtil.getTempString(mWeatherData.getMain().getMainTempMax(),
                        prefs.isUnitMetric()));
            }
            if (mWeatherData.getMain() != null) {
                holder.tempLow.setText("MIN: " + WeatherUtil.getTempString(mWeatherData.getMain().getMainTempMin(),
                        prefs.isUnitMetric()));
            }
        }
        else if (viewHolder.getItemViewType() == DETAILVIEW) {
            DetailViewHolder holder = (DetailViewHolder) viewHolder;
            holder.llDetails.removeAllViews();
            populateDetails(holder);
        }
    }

    private void populateDetails(DetailViewHolder holder) {

        holder.llDetails.removeAllViewsInLayout();

        if (mWeatherData.getMain().getMainTemp() != null) {
            WeatherDetailLayout temperatudeDetail = createDetailLayout(WeatherUtil.getTemperatureIcon(),
                    "Température ressentie", WeatherUtil.getTempString(mWeatherData.getMain().getMainTemp(),
                            prefs.isUnitMetric()));
            holder.llDetails.addView(temperatudeDetail);
        }

        if (mWeatherData.getRain().getRainAll() != null) {
            WeatherDetailLayout rainDetail = createDetailLayout(WeatherUtil.getRainIcon(),
                    "Probabilité de pluie/neige", String.valueOf(mWeatherData.getRain().getRainAll())
                            + Constants.DETAIL_LABEL_HUMIDITY);
            holder.llDetails.addView(rainDetail);
        }

        if (mWeatherData.getWind().getWindSpeed() != null && mWeatherData.getWind().getWindDeg() != null) {
            WeatherDetailLayout windDetail = createDetailLayout(WeatherUtil.getWindIcon(),
                    "Vent", String.valueOf((mWeatherData.getWind().getWindSpeed())
                            + prefs.getWindSpeedUnit() + " " + mWeatherData.getWind().getWindDeg()
                            + Constants.DETAIL_LABEL_DEGREE));
            holder.llDetails.addView(windDetail);
        }

        if (mWeatherData.getMain().getMainHumidity() != null) {
            WeatherDetailLayout humidityDetail = createDetailLayout(WeatherUtil.getHumidityIcon(),
                    "Humidité", String.valueOf(mWeatherData.getMain().getMainHumidity())
                            + Constants.DETAIL_LABEL_HUMIDITY);
            holder.llDetails.addView(humidityDetail);
        }

        if (mWeatherData.getClouds().getCloudsAll() != null) {
            WeatherDetailLayout cloudsDetail = createDetailLayout(WeatherUtil.getPressureIcon(),
                    "Visibilité", String.valueOf(mWeatherData.getClouds().getCloudsAll())
                            + Constants.DETAIL_LABEL_VISILIBITY);
            holder.llDetails.addView(cloudsDetail);
        }

        if (mWeatherData.getMain().getMainPressure() != null) {
            WeatherDetailLayout pressureDetail = createDetailLayout(WeatherUtil.getPressureIcon(),
                    "Pression", String.valueOf(mWeatherData.getMain().getMainPressure())
                            + Constants.DETAIL_LABEL_PRESSURE);
            holder.llDetails.addView(pressureDetail);
        }

        if (mWeatherData.getSys().getSysSunrise() != null) {
            WeatherDetailLayout sunriseDetail = createDetailLayout(WeatherUtil.getSunriseIcon(),
                    "Lever du soleil", WeatherUtil.getTime(mWeatherData.getSys().getSysSunrise()));
            holder.llDetails.addView(sunriseDetail);
        }

        if (mWeatherData.getSys().getSysSunset() != null) {
            WeatherDetailLayout sunsetDetail = createDetailLayout(WeatherUtil.getSunsetIcon(),
                    "Couché du soleil", WeatherUtil.getTime(mWeatherData.getSys().getSysSunset()));
            holder.llDetails.addView(sunsetDetail);
        }
    }

    private WeatherDetailLayout createDetailLayout(String icon, String label, String value) {
        WeatherDetailLayout weatherDetailLayout = new WeatherDetailLayout(mContext);
        weatherDetailLayout.setIcon(icon);
        weatherDetailLayout.setLabel(label);
        weatherDetailLayout.setValue(value);
        return weatherDetailLayout;
    }

    private void setWeatherIcon(WeatherViewHolder holder) {
        if (mWeatherData.getWeather().size() > 0) {
            int id = (mWeatherData.getWeather().get(0).getWeatherId());
            holder.tvIcon.setText(WeatherUtil.getWeatherIcon(id));
        }
    }

    public void setWeatherData(WeatherData weatherData) {
        mWeatherData = weatherData;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    class WeatherViewHolder extends ViewHolder {
        private TextView tvIcon;
        private TextView tvCity;
        private TextView tvCondition;
        private TextView tvTemp;
        private TextView tempHigh;
        private TextView tempLow;

        public WeatherViewHolder(View v) {
            super(v);
            tvIcon = (TextView) v.findViewById(R.id.tv_icon);
            tvCity = (TextView) v.findViewById(R.id.tv_city);
            tvCondition = (TextView) v.findViewById(R.id.tv_condition);
            tvTemp = (TextView) v.findViewById(R.id.tv_temp);
            tempHigh = (TextView) v.findViewById(R.id.tempHigh);
            tempLow = (TextView) v.findViewById(R.id.tempLow);
        }
    }

    class DetailViewHolder extends ViewHolder {
        private LinearLayout llDetails;

        public DetailViewHolder(View v) {
            super(v);
            llDetails = (LinearLayout) v.findViewById(R.id.ll_details);
        }
    }
}
