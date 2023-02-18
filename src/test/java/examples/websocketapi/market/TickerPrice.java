package examples.websocketapi.market;


import com.alibaba.fastjson2.JSONObject;
import com.binance.connector.client.impl.WebsocketApiClientImpl;

public final class TickerPrice {

    private TickerPrice() {
    }

    private static final int waitTime = 3000;

    public static void main(String[] args) throws InterruptedException {
        WebsocketApiClientImpl client = new WebsocketApiClientImpl();
        client.connect(((event) -> {
            System.out.println(event);
        }));

        JSONObject params = new JSONObject();
        String[] symbols = new String[]{"BTCUSDT", "BNBUSDT"};

        params.put("symbols", symbols);
        client.market().tickerPrice(params);

        Thread.sleep(waitTime);
        client.close();

    }
}
