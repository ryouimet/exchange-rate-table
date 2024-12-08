/**
 * {@code RateTableKernel} enhanced with secondary methods.
 */
public interface RateTable extends RateTableKernel {

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
    void addAllExchangeRates(RateTable t);

    /**
     * Retrives the most valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the maximum {@code ExchangeRate} in the {@code RateTable}
     * @requires |this| > 0
     * @ensures result = (the maximum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    ExchangeRate getMostValuable();

    /**
     * Retrives the least valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the minimum {@code ExchangeRate} in the {@code RateTable}
     * @requires |this| > 0
     * @ensures result = (the minimum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    ExchangeRate getLeastValuable();

    /**
     * Populates current exchange rates into the {@code RateTable}.
     *
     * @updates {@code this}
     * @ensures this = #this union [current exchange rates]
     */
    void getCurrentExchangeRates();

}
