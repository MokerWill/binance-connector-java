package com.binance.connector.client.impl.future.usdt;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;

import java.util.LinkedHashMap;

/**
 * <h2>User Data Streams Endpoints</h2>
 * All endpoints under the
 * <a href="https://binance-docs.github.io/apidocs/futures/en/#listenkey-user_stream">User Data Streams</a>
 * section of the API documentation will be implemented in this class.
 * <br>
 * Response will be returned in <i>String format</i>.
 */
public class UserData {
    private final String baseUrl;
    private final RequestHandler requestHandler;
    private final boolean showLimitUsage;

    public UserData(String baseUrl, String apiKey, boolean showLimitUsage) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey);
        this.showLimitUsage = showLimitUsage;
    }

    private final String USDT_LISTEN_KEY = "/fapi/v1/listenKey";
    /**
     * Start a new user data stream. The stream will close after 60 minutes unless a keepalive is sent.
     * If the account has an active listenKey, that listenKey will be returned and its validity will be extended for 60 minutes.
     * <br><br>
     * POST /fapi/v1/listenKey
     * <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#listenkey-user_stream">
     *     https://binance-docs.github.io/apidocs/futures/en/#listenkey-user_stream</a>
     */
    public String createListenKey() {
        return requestHandler.sendWithApiKeyRequest(baseUrl, USDT_LISTEN_KEY, null, HttpMethod.POST, showLimitUsage);
    }

    /**
     * Keepalive a user data stream to prevent a time out. User data streams will close after 60 minutes.
     * It's recommended to send a ping about every 30 minutes.
     * <br><br>
     * PUT /fapi/v1/listenKey
     * <br>
     * @param
     * parameters LinkedHashedMap of String,Object pair
     *            where String is the name of the parameter and Object is the value of the parameter
     * <br><br>
     * listenKey -- mandatory/string <br>
     * @return String
     * @see <a href="https://binance-docs.github.io/apidocs/futures/en/#listenkey-user_stream">
     *     https://binance-docs.github.io/apidocs/futures/en/#listenkey-user_stream</a>
     */
    public String extendListenKey(LinkedHashMap<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "listenKey", String.class);
        return requestHandler.sendWithApiKeyRequest(baseUrl, USDT_LISTEN_KEY, parameters, HttpMethod.PUT, showLimitUsage);
    }

}
