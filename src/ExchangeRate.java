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
        return String.format("ExchangeRate[name=%s, rate=%s]", this.name,
                this.rate.toPlainString());
    }

    /*
     * Domain-Specific Methods ------------------------------------------------
     */

    /**
     * Converts one currency into another using {@code r}.
     *
     * @param r
     *            the exchange rate of the new currency
     * @return {@code BigDecimal} [The new currency amount]
     * @ensures result = [this.rate * r.rate]
     */
    BigDecimal convertAmount(ExchangeRate r) {
        assert r != null : "Violation of: r is not null";
        return this.rate.multiply(r.rate);
    }

    /**
     * Determines whether or not an {@code ExchangeRate} has changed by less
     * than a specified threshold percentage.
     *
     * @param previousRate
     *            the previous rate of the currency
     *
     * @param threshold
     *            the threshold the user uses to view the percentage of change
     *
     * @return true if [(currentRate - previousRate) < (previousRate * threshold
     *         / 100)]
     * @ensures result == (this.rate - previousRate.rate) < (previousRate.rate *
     *          threshold / 100)
     */
    boolean isStable(ExchangeRate previousRate, BigDecimal threshold) {
        assert previousRate != null : "Violation of: previousRate is not null";
        assert threshold != null : "Violation of: threshold is not null";

        BigDecimal currentRateValue = this.rate;
        BigDecimal previousRateValue = previousRate.rate;

        return ((currentRateValue.subtract(previousRateValue))
                .compareTo((previousRateValue.multiply(threshold))
                        .divide(new BigDecimal(100))) < 0);
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
     * @return true if [lowerBound <= this.rate <= upperBound]
     * @ensures result == (lowerBound.compareTo(this.rate) <= 0 &&
     *          this.rate.compareTo(upperBound) <= 0)
     */
    boolean isWithinRange(BigDecimal lowerBound, BigDecimal upperBound) {
        assert lowerBound != null : "Violation of: lowerBound is not null";
        assert upperBound != null : "Violation of: lowerBound is not null";
        return (lowerBound.compareTo(this.rate) <= 0
                && this.rate.compareTo(upperBound) <= 0);
    }

    /**
     * Determines whether or not an {@code ExchangeRate} is worthless.
     *
     * @return true if [this <= 0].
     * @ensures result == (this.rate <= 0)
     */
    boolean isWorthless() {
        assert this != null : "Violation of: this is not null";
        return this.rate().compareTo(new BigDecimal(0)) <= 0;
    }

    /**
     * Inflates or deflates a rate by a certain amount.
     *
     * @param inflationRate
     *            the rate that {@code this} is multiplied by.
     * @ensures this = #this * inflationRate
     */
    BigDecimal multiplyRate(BigDecimal inflationRate) {
        assert inflationRate != null : "Violation of: inflationRate is not null";
        return this.rate.multiply(inflationRate);
    }

}
