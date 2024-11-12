import java.math.BigDecimal;

/**
 * {@code ExchangeRateKernel} enhanced with secondary methods.
 */
public interface ExchangeRate
        extends Comparable<ExchangeRate>, ExchangeRateKernel {

    /**
     * Inflates or deflates a rate by a certain amount.
     *
     * @param inflationRate
     *            the rate that {@code this} is multiplied by.
     * @ensures this = #this * inflationRate
     */
    void multiplyRate(ExchangeRate inflationRate);

    /**
     * Converts one currency into another using {@code r}.
     *
     * @param r
     *            the exchange rate of the new currency
     * @return the new currency amount
     */
    BigDecimal convertAmount(ExchangeRate r);

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
     */
    boolean isWithinRange(BigDecimal lowerBound, BigDecimal upperBound);

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
     */
    boolean isStable(ExchangeRate previousRate, BigDecimal threshold);

}
