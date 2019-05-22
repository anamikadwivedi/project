package com.example.indo_asia.payment;

import com.example.indo_asia.BuildConfig;

import retrofit.RequestInterceptor;

class ApiClientRequestInterceptor implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("User-Agent", "https://developer.paypal.com/developer/applications/" + BuildConfig.VERSION_NAME);
        request.addHeader("Accept", "application/json");
    }
}
