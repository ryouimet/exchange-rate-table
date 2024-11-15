/**
 * {@code RateTableKernel} enhanced with secondary methods.
 */
public interface RateTable extends Comparable<RateTable>, RateTableKernel {

    /**
     * Retrives the most valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the maximum {@code ExchangeRate} in the {@code RateTable}
     * @ensures result.equals(the maximum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    ExchangeRate getMostValuable();

    /**
     * Retrives the least valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the minimum {@code ExchangeRate} in the {@code RateTable}
     * @ensures result.equals(the minimum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    ExchangeRate getLeastValuable();

}
