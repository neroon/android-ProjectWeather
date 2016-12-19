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
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;

import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.utils.WeatherUtil;
import ca.udes.android_projectweather.managers.SharedPreferenceManager;
import ca.udes.android_projectweather.models.forecastdailymodel.ListDaily;
import ca.udes.android_projectweather.models.forecastdailymodel.ForecastDailyData;

/**
 * ForecastDailyAdapter
 *
 * @version 1.0
 */
public class ForecastDailyAdapter extends RecyclerView.Adapter<ForecastDailyAdapter.ViewHolder> {

    private Context mContext;
    private ForecastDailyData mForecastDailyData = new ForecastDailyData();
    private SharedPreferenceManager prefs;

    public ForecastDailyAdapter(Context context) {
        mContext = context;
        prefs = SharedPreferenceManager.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mForecastDailyData.getList().size();
    }

    @Override
    public ForecastDailyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_forecast, parent, false));
    }

    @Override
    public void onBindViewHolder(ForecastDailyAdapter.ViewHolder holder, int position) {
        if(mForecastDailyData.getList() != null){
            ListDaily forecastItem = mForecastDailyData.getList().get(position);
            holder.tvIcon.setText(WeatherUtil.getWeatherIcon(forecastItem.getListWeather().get(0).getWeatherId()));
            holder.tvWeekday.setText(WeatherUtil.getWeekDay(forecastItem.getListDt()));
            holder.tvCondition.setText(forecastItem.getListWeather().get(0).getWeatherDescription());
            holder.tvTempMin.setText("MIN: " + WeatherUtil.getTempString(forecastItem.getListTemp().getListTempMin(), prefs.isUnitMetric()));
            holder.tvTempMax.setText("MAX: " + WeatherUtil.getTempString(forecastItem.getListTemp().getListTempMax(), prefs.isUnitMetric()));
        }
    }

    public void setForecastData(ForecastDailyData forecastDailyData) {
        mForecastDailyData = forecastDailyData;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIcon;
        private TextView tvWeekday;
        private TextView tvCondition;
        private TextView tvTempMin;
        private TextView tvTempMax;

        public ViewHolder(View v) {
            super(v);
            tvIcon = (TextView) v.findViewById(R.id.tv_icon);
            tvWeekday = (TextView) v.findViewById(R.id.tv_weekday);
            tvCondition = (TextView) v.findViewById(R.id.tv_condition);
            tvTempMin = (TextView) v.findViewById(R.id.tv_temp_min);
            tvTempMax = (TextView) v.findViewById(R.id.tv_temp_max);
        }
    }
}
