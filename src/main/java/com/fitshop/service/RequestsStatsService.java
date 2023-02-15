package com.fitshop.service;

import com.fitshop.model.view.RequestsStatsViewModel;

public interface RequestsStatsService {

    void onRequest();

    RequestsStatsViewModel getStats();

}
