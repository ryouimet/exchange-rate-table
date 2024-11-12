import java.math.BigDecimal;

import components.map.Map;
import components.standard.Standard;

/**
 * ExchangeRate kernel component with primary methods.
 */
public interface ExchangeRateKernel extends Standard<ExchangeRate> {

    /**
     * Gets an exchange rate's name.
     *
     * @return {@code String} [The name of the exchange rate]
     */
    String getRateName();

    /**
     * Gets an exchange rate's value.
     *
     * @return {@code BigDecimal} [The value of the exchange rate]
     */
    BigDecimal getRateValue();

    /**
     * Creates a map of exchange rates.
     *
     * @return {@code Map} of {@code String} [The name of the exchange rate] and
     *         {@code BigDecimal} [The value of the exchange rate]
     */
    Map<String, BigDecimal> setRateMap();

    /**
     * Determines whether or not an ExchangeRate is worthless.
     *
     * @return true if [this <= 0].
     */
    boolean isWorthless();

}
