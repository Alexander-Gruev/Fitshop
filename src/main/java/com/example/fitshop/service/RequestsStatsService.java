package com.example.fitshop.service;

import com.example.fitshop.model.view.RequestsStatsViewModel;

public interface RequestsStatsService {

    void onRequest();

    RequestsStatsViewModel getStats();

}
