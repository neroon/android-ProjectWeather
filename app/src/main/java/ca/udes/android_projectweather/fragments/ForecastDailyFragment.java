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
package ca.udes.android_projectweather.fragments;

import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ca.udes.android_projectweather.R;
import ca.udes.android_projectweather.views.adapters.ForecastDailyAdapter;
import ca.udes.android_projectweather.models.forecastdailymodel.ForecastDailyData;

/**
 * ForecastDailyFragment.
 *
 * @version 1.0
 */
public class ForecastDailyFragment extends Fragment {

    private ForecastDailyAdapter mForecastAdapter;

    /**
     * Constructor.
     *
     */
    public static ForecastDailyFragment newInstance() {
        return new ForecastDailyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecastAdapter = new ForecastDailyAdapter(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mForecastAdapter);
    }

    /**
     * Update data.
     *
     * @param forecastDailyData
     */
    public void updateData(ForecastDailyData forecastDailyData) {
        mForecastAdapter.setForecastData(forecastDailyData);
    }
}
