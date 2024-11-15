import java.math.BigDecimal;

/**
 * ExchangeRate subclass used in the {@code RateTable} component.
 *
 * @param name
 *            the name of the ExchangeRate
 * @param rate
 *            the ratio of the currency to USD
 */
public record ExchangeRate(String name, BigDecimal rate) {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public String toString() {
        return this.rate.toString();
    }

    @Override
    public final ExchangeRate clone() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    /**
     * Inflates or deflates a rate by a certain amount.
     *
     * @param inflationRate
     *            the rate that {@code this} is multiplied by.
     * @ensures this = #this * inflationRate
     */
    void multiplyRate(ExchangeRate inflationRate) {
        assert inflationRate != null : "Violation of: r is not null";
        this.rate.multiply(inflationRate.rate);
    }

    /**
     * Converts one currency into another using {@code r}.
     *
     * @param r
     *            the exchange rate of the new currency
     * @return {@code BigDecimal} [The new currency amount]
     * @ensures result.equals(this.clone().rate.multiply(r.rate))
     */
    BigDecimal convertAmount(ExchangeRate r) {
        ExchangeRate temp = this.clone();
        return temp.rate.multiply(r.rate);
    }

    /**
     * Determines whether or not an ExchangeRate is within a certain range.
     *
     * @param lowerBound
     *            the lower bound of the range
     *
     * @param upperBound
     *            the upper bound of the range
     *
     * @return true if [lowerBound <= this.getRateValue() <= upperBound]
     * @ensures result == (lowerBound.compareTo(this.rate) <= 0 &&
     *          this.rate.compareTo(upperBound) <= 0)
     */
    boolean isWithinRange(BigDecimal lowerBound, BigDecimal upperBound) {
        return (lowerBound.compareTo(this.rate) <= 0
                && this.rate.compareTo(upperBound) <= 0);
    }

    /**
     * Determines whether or not an ExchangeRate has changed by less than a
     * specified threshold percentage.
     *
     * @param previousRate
     *            the previous rate of the currency
     *
     * @param threshold
     *            the threshold the user uses to view the percentage of change
     *
     * @return true if [(currentRate - previousRate) < (previousRate * threshold
     *         / 100)]
     * @ensures result == ((this.rate.subtract(previousRate.rate))
     *          .compareTo((previousRate.rate.multiply(threshold)) .divide(new
     *          BigDecimal(100))) < 0)
     */
    boolean isStable(ExchangeRate previousRate, BigDecimal threshold) {

        BigDecimal currentRateValue = this.rate;
        BigDecimal previousRateValue = previousRate.rate;

        return ((currentRateValue.subtract(previousRateValue))
                .compareTo((previousRateValue.multiply(threshold))
                        .divide(new BigDecimal(100))) < 0);
    }

    /**
     * Determines whether or not an ExchangeRate is worthless.
     *
     * @return true if [this <= 0].
     * @ensures result == (this.rate().compareTo(new BigDecimal(0)) <= 0)
     */
    boolean isWorthless() {
        return this.rate().compareTo(new BigDecimal(0)) <= 0;
    }

}
