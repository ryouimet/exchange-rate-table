import java.math.BigDecimal;

import components.standard.Standard;

/**
 * RateTable kernel component with primary methods.
 */
public interface RateTableKernel extends Standard<RateTable> {

    /**
     * Gets the name of an {@code ExchangeRate}.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @return {@code String} [The name of the {@code ExchangeRate}]
     * @requires [{@code name} is in {@code this}]
     * @ensures result is a pair of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    ExchangeRate getExchangeRate(String name);

    /**
     * Adds an {@code ExchangeRate} to the {@code RateTable}.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @param rate
     *            the value of the {@code ExchangeRate}
     * @requires [{@code name} is not null]
     * @ensures this.contains[{@code Pair} of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]]
     */
    void addExchangeRate(String name, BigDecimal rate);

    /**
     * Removes an {@code ExchangeRate} from the {@code RateTable}.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @return {@code ExchangeRate} associated with the name given
     * @requires [{@code name} is in {@code this}]
     * @ensures result is a pair of {@code String} [the name of the
     *          {@code ExchangeRate}] and {@code BigDecimal} [the value of the
     *          {@code ExchangeRate}]
     */
    ExchangeRate removeExchangeRate(String name);

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
     * Checks if an {@code RateTable} contains an {@code ExchangeRate}
     * associated with the given name.
     *
     * @param name
     *            the name of the {@code ExchangeRate}
     * @return true if [{@code this} contains an {@code ExchangeRate} associated
     *         with the given name]
     * @requires [name is in {@code this}]
     * @ensures result == ({@code this} contains an {@code ExchangeRate}
     *          associated with {@code name})
     */
    boolean containsRate(String name);

    /**
     * Reports the size of {@code this}.
     *
     * @return the number of pairs in {@code this}
     * @ensures size = |this|
     */
    int size();

}
