package com.binance.connector.client.impl.future.usdt;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.JSONParser;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * <h2>Market Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/futures/en/#market-data-endpoints">Market Data Endpoint</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class Market {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public Market(String baseUrl, String apiKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.showLimitUsage = showLimitUsage;
    }
    private static final String PING = "/fapi/v1/ping";
    /**
     * Test connectivity to the Rest API.
     * <br><br>
     * GET /fapi/v1/ping
     * <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#test-connectivity">
     *     https://binance-docs.github.io/apidocs/futures/en/#test-connectivity</a>
     */
    public String ping() {
        return requestHandler.sendPublicRequest(baseUrl, PING, null, HttpMethod.GET, showLimitUsage);
    }

    private static final String TIME = "/fapi/v1/time";
    /**
     * Test connectivity to the Rest API and get the current server time.
     * <br><br>
     * GET /fapi/v1/time
     * <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#test-connectivity">
     *     https://binance-docs.github.io/apidocs/futures/en/#check-server-time</a>
     */
    public String time() {
        return requestHandler.sendPublicRequest(baseUrl, TIME, null, HttpMethod.GET, showLimitUsage);
    }

    private static final String EXCHANGE_INFO = "/fapi/v1/exchangeInfo";
    /**
     * Current exchange trading rules and symbol information.
     * <br><br>
     * GET /fapi/v1/exchangeinfo
     * <br>
     * <br><br>
     * symbol -- optional/string <br>
     * symbols -- optional/ArrayList <br>
     * permissions -- optional/ArrayList -- support single or multiple values (e.g. "SPOT", ["MARGIN","LEVERAGED"]) <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#exchange-information">
     *     https://binance-docs.github.io/apidocs/futures/en/#exchange-information</a>
     */
    public String exchangeInfo() {
        return requestHandler.sendPublicRequest(baseUrl, EXCHANGE_INFO, null, HttpMethod.GET, showLimitUsage);
    }

    private static final String DEPTH = "/fapi/v1/depth";
    /**
     * GET /fapi/v1/depth
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * limit -- optional/integer -- limit the results
     *            Default 100; max 5000. Valid limits:[5, 10, 20, 50, 100, 500, 1000, 5000] <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#order-book">
     *     https://binance-docs.github.io/apidocs/futures/en/#order-book</a>
     */
    public String depth(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String TRADES = "/fapi/v1/trades";
    /**
     * Get recent trades.
     * <br><br>
     * GET /fapi/v1/trades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#recent-trades-list">
     *     https://binance-docs.github.io/apidocs/futures/en/#recent-trades-list</a>
     */
    public String trades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String HISTORICAL_TRADES = "/fapi/v1/historicalTrades";
    /**
     * Get older market trades.
     * <br><br>
     * GET /fapi/v1/historicalTrades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * limit -- optional/integer -- limit the result Default 500; max 1000 <br>
     * fromId -- optional/long -- trade id to fetch from. Default gets most recent trades <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#old-trade-lookup">
     *     https://binance-docs.github.io/apidocs/futures/en/#old-trade-lookup</a>
     *
     */
    public String historicalTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, HISTORICAL_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String AGG_TRADES = "/fapi/v1/aggTrades";
    /**
     * Get compressed, aggregate trades. Trades that fill at the time, from the same order,
     * with the same price will have the quantity aggregated.
     * <br><br>
     * GET /fapi/v1/aggTrades
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * fromId -- optional/long -- id to get aggregate trades from INCLUSIVE <br>
     * startTime -- optional/long -- Timestamp in ms to get aggregate trades from INCLUSIVE <br>
     * endTime -- optional/long -- Timestamp in ms to get aggregate trades until INCLUSIVE <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#compressed-aggregate-trades-list">
     *     https://binance-docs.github.io/apidocs/futures/en/#compressed-aggregate-trades-list</a>
     */
    public String aggTrades(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, AGG_TRADES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String KLINES = "/fapi/v1/klines";
    /**
     * Kline/candlestick bars for a symbol.
     * Klines are uniquely identified by their open time.
     * <br><br>
     * GET /fapi/v1/klines
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- mandatory/string <br>
     * interval -- mandatory/string <br>
     * startTime -- optional/long <br>
     * endTime -- optional/long <br>
     * limit -- optional/integer -- limit the results Default 500; max 1000 <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#kline-candlestick-data">
     *     https://binance-docs.github.io/apidocs/futures/en/#kline-candlestick-data</a>
     */
    public String klines(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "interval", String.class);
        return requestHandler.sendPublicRequest(baseUrl, KLINES, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String TICKER_24H = "/fapi/v1/ticker/24hr";
    /**
     * 24 hour rolling window price change statistics. Careful when accessing this with no symbol.
     * <br><br>
     * GET /fapi/v1/ticker/24hr
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * symbols -- optional/string <br>
     * type -- optional/enum -- Supported values: FULL or MINI. If none provided, the default is FULL <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#24hr-ticker-price-change-statistics">
     *     https://binance-docs.github.io/apidocs/futures/en/#24hr-ticker-price-change-statistics</a>
     */
    public String ticker24H(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new BinanceConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, TICKER_24H, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String TICKER_SYMBOL = "/fapi/v1/ticker/price";
    /**
     * Latest price for a symbol or symbols.
     * <br><br>
     * GET /fapi/v1/ticker/price
     * <br>
     * https://binance-docs.github.io/apidocs/futures/en/#24hr-ticker-price-change-statistics
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * symbols -- optional/string <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#symbol-price-ticker">
     *     https://binance-docs.github.io/apidocs/futures/en/#symbol-price-ticker</a>
     */
    public String tickerSymbol(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new BinanceConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, TICKER_SYMBOL, parameters, HttpMethod.GET, showLimitUsage);
    }

    private static final String BOOK_TICKER = "/fapi/v1/ticker/bookTicker";
    /**
     * Best price/qty on the order book for a symbol or symbols.
     * <br><br>
     * GET /fapi/v1/ticker/bookTicker
     * <br>
     * https://binance-docs.github.io/apidocs/futures/en/#24hr-ticker-price-change-statistics
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * symbol -- optional/string -- the trading pair <br>
     * symbols -- optional/string <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#symbol-order-book-ticker">
     *     https://binance-docs.github.io/apidocs/futures/en/#symbol-order-book-ticker</a>
     */
    public String bookTicker(LinkedHashMap<String, Object> parameters) {
        if (parameters.containsKey("symbol") && parameters.containsKey("symbols")) {
            throw new BinanceConnectorException("symbol and symbols cannot be sent together.");
        }
        if (parameters.containsKey("symbols")) {
            ParameterChecker.checkParameterType(parameters.get("symbols"), ArrayList.class, "symbols");
            parameters.put("symbols", JSONParser.getJSONArray(
                    (ArrayList<?>) parameters.get("symbols"), "symbols"));
        }
        return requestHandler.sendPublicRequest(baseUrl, BOOK_TICKER, parameters, HttpMethod.GET, showLimitUsage);
    }
}
