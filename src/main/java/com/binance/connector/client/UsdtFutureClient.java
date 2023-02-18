package com.binance.connector.client;

import com.binance.connector.client.impl.future.usdt.Market;

public interface UsdtFutureClient {
    Market createMarket();
}
