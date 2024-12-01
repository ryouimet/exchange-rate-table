/**
 * {@code RateTableKernel} enhanced with secondary methods.
 */
public interface RateTable extends RateTableKernel {

    /**
     * Populates current exchange rates into the {@code RateTable}.
     *
     * @updates {@code this}
     * @ensures this = #this union [current exchange rates]
     */
    void populateRatesFromAPI();

    /**
     * Retrives the most valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the maximum {@code ExchangeRate} in the {@code RateTable}
     * @ensures result = (the maximum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    ExchangeRate getMostValuable();

    /**
     * Retrives the least valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the minimum {@code ExchangeRate} in the {@code RateTable}
     * @ensures result = (the minimum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    ExchangeRate getLeastValuable();

}
