import java.math.BigDecimal;

/**
 * Layered implementations of secondary methods for {@code RateTable}.
 */
public abstract class RateTableSecondary implements RateTable {

    /**
     * Retrives the most valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the maximum {@code ExchangeRate} in the {@code RateTable}
     * @ensures result.equals(the maximum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    @Override
    public ExchangeRate getMostValuable() {

        String resName = "";
        BigDecimal max = new BigDecimal(0);

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

    /**
     * Retrives the least valuable {@code ExchangeRate} from the
     * {@code RateTable}.
     *
     * @return the minimum {@code ExchangeRate} in the {@code RateTable}
     * @ensures result.equals(the minimum {@code ExchangeRate} in the
     *          {@code RateTable})
     */
    @Override
    public ExchangeRate getLeastValuable() {

        String resName = "";
        BigDecimal min = new BigDecimal(0);

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

}
