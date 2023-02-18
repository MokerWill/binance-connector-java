package com.binance.connector.client.impl;

import com.binance.connector.client.UsdtFutureClient;
import com.binance.connector.client.enums.DefaultUrls;
import com.binance.connector.client.impl.future.usdt.Market;
import com.binance.connector.client.utils.HmacSignatureGenerator;
import com.binance.connector.client.utils.SignatureGenerator;


public class UsdtFutureClientImpl implements UsdtFutureClient {
    private final String apiKey;
    private final SignatureGenerator signatureGenerator;
    private final String baseUrl;
    private boolean showLimitUsage = false;

    public UsdtFutureClientImpl() {
        this(DefaultUrls.U_FUTURE_URL);
    }

    public UsdtFutureClientImpl(String baseUrl) {
        this("", "", baseUrl);
    }

    public UsdtFutureClientImpl(String baseUrl, boolean showLimitUsage) {
        this(baseUrl);
        this.showLimitUsage = showLimitUsage;
    }

    public UsdtFutureClientImpl(String apiKey, String secretKey) {
        this(apiKey, secretKey, DefaultUrls.PROD_URL);
    }

    public UsdtFutureClientImpl(String apiKey, String secretKey, String baseUrl) {
        this(apiKey, new HmacSignatureGenerator(secretKey), baseUrl);
    }

    public UsdtFutureClientImpl(String apiKey, SignatureGenerator signatureGenerator, String baseUrl) {
        this.apiKey = apiKey;
        this.signatureGenerator = signatureGenerator;
        this.baseUrl = baseUrl;
    }

    public void setShowLimitUsage(boolean showLimitUsage) {
        this.showLimitUsage = showLimitUsage;
    }

    @Override
    public Market createMarket() {
        return new Market(baseUrl, apiKey, showLimitUsage);
    }

}
