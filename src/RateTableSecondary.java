import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Layered implementations of secondary methods for {@code RateTable}.
 */
public abstract class RateTableSecondary implements RateTable {

    /**
     * Adds all {@code ExchangeRates} from {@code t} to the {@code this}.
     *
     * @param t
     *            the {@code RateTable} to be copied into {@code this}
     * @requires {@code t} is not null
     * @ensures this contains a {@code Pair} of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    @Override
    public void addAllExchangeRates(RateTable t) {
        for (ExchangeRate r : t.exchangeRateSet()) {
            this.addExchangeRate(r);
        }
    }

    /**
     * Populates current exchange rates into the {@code RateTable}.
     *
     * @updates {@code this}
     * @ensures this = #this union [current exchange rates]
     */
    @Override
    public void getCurrentExchangeRates() {

        Dotenv dotenv = Dotenv.configure().directory("assets").filename("env")
                .load();

        String urlStr = "https://v6.exchangerate-api.com/v6/"
                + dotenv.get("API_KEY") + "/latest/USD";

        try {
            URL url = new URL(urlStr);
            HttpURLConnection request = (HttpURLConnection) url
                    .openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(
                    new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            JsonObject conversionRates = jsonobj
                    .getAsJsonObject("conversion_rates");

            for (String currency : conversionRates.keySet()) {
                BigDecimal rate = conversionRates.get(currency)
                        .getAsBigDecimal();
                this.addExchangeRate(currency, rate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrives the most valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the minimum {@code ExchangeRate} value in the {@code RateTable}
     * @requires |this| > 0
     * @ensures result = (the minimum {@code ExchangeRate} value in the
     *          {@code RateTable})
     */
    @Override
    public ExchangeRate getMostValuable() {
        assert this != null : "Violation of: this is not null";

        String resName = "";
        BigDecimal min = new BigDecimal(Integer.MAX_VALUE);

        RateTable temp = this.newInstance();
        temp.transferFrom(this);

        while (temp.size() > 0) {
            ExchangeRate r = temp.removeAny();
            if (r.rate().compareTo(min) < 0) {
                min = r.rate();
                resName = r.name();
            }
            this.addExchangeRate(r.name(), r.rate());
        }
        return new ExchangeRate(resName, min);
    }

    /**
     * Retrives the least valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the maxiumum {@code ExchangeRate} value in the {@code RateTable}
     * @requires |this| > 0
     * @ensures result = (the maximum {@code ExchangeRate} value in the
     *          {@code RateTable})
     */
    @Override
    public ExchangeRate getLeastValuable() {
        assert this != null : "Violation of: this is not null";

        String resName = "";
        BigDecimal max = new BigDecimal(Integer.MIN_VALUE);

        RateTable temp = this.newInstance();
        temp.transferFrom(this);

        while (temp.size() > 0) {
            ExchangeRate r = temp.removeAny();
            if (r.rate().compareTo(max) > 0) {
                max = r.rate();
                resName = r.name();
            }
            this.addExchangeRate(r.name(), r.rate());
        }
        return new ExchangeRate(resName, max);
    }

}
