import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import components.standard.Standard;

/**
 * RateTable kernel component with primary methods.
 */
public interface RateTableKernel extends Standard<RateTable> {

    /**
     * Adds an {@code ExchangeRate} to the {@code RateTable}.
     *
     * @param r
     *            the {@code ExchangeRate} to be added
     * @requires {@code r} is not null
     * @ensures this contains a {@code Pair} of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    void addExchangeRate(ExchangeRate r);

    /**
     * Adds an {@code ExchangeRate} to the {@code RateTable}.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @param rate
     *            the value of the {@code ExchangeRate}
     * @requires {@code name} and {@code rate} is not null
     * @ensures this contains a {@code Pair} of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    void addExchangeRate(String name, BigDecimal rate);

    /**
     * Checks if an {@code RateTable} contains an {@code ExchangeRate}
     * associated with the given name.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @return true if [{@code this} contains an {@code ExchangeRate} associated
     *         with the given name]
     * @requires name is in {@code this}
     * @ensures result == ({@code this} contains an {@code ExchangeRate}
     *          associated with {@code name})
     */
    boolean containsRate(String name);

    /**
     * Returns a {@code Set} of the {@code ExchangeRates} in the
     * {@code RateTable}.
     *
     * @return the {@code Set} of unique {@code ExchangeRates} in the
     *         {@code RateTable}.
     */
    Set<ExchangeRate> exchangeRateSet();

    /**
     * Gets the name of an {@code ExchangeRate}.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @return {@code String} [The name of the {@code ExchangeRate}]
     * @requires {@code name} is in {@code this}
     * @ensures result is a pair of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    ExchangeRate getExchangeRate(String name);

    /**
     * Returns a {@code Collection} of the {@code ExchangeRate} values in the
     * {@code RateTable}.
     *
     * @return a {@code Collection} of the {@code ExchangeRate} values in the
     *         {@code RateTable}.
     */
    Collection<BigDecimal> rates();

    /**
     * Removes and returns an arbitrary pair from {@code this}.
     *
     * @return the pair removed from {@code this}
     * @updates {@code this}
     * @requires |this| > 0
     * @ensures removeAny is in #this and this = #this \ {removeAny}
     */
    ExchangeRate removeAny();

    /**
     * Removes an {@code ExchangeRate} from the {@code RateTable}.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @return {@code ExchangeRate} associated with the name given
     * @requires {@code name} is in {@code this}
     * @ensures result is a pair of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    ExchangeRate removeExchangeRate(String name);

    /**
     * Reports the size of {@code this}.
     *
     * @return the number of pairs in {@code this}
     * @ensures size = |this|
     */
    int size();

    /**
     * Returns a {@code Set} of the {@code ExchangeRate} names in the
     * {@code RateTable}.
     *
     * @return the {@code Set} of unique {@code ExchangeRate} names in the
     *         {@code RateTable}.
     */
    Set<String> nameSet();

}
