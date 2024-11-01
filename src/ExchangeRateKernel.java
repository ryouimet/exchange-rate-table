import components.standard.Standard;

/**
 * ExchangeRate kernel component with primary methods.
 */
public interface ExchangeRateKernel extends Standard<ExchangeRate> {

    /**
     * Determines whether or not an ExchangeRate is worthless.
     *
     * @return true if [this <= 0].
     */
    boolean isWorthless();

    /**
     * Determines whether or not an ExchangeRate is within a certain range.
     *
     * @param lowerBound
     *            the lower bound of the range
     *
     * @param upperBound
     *            the upper bound of the range
     *
     * @return true if [lowerBound <= this.value() <= upperBound]
     */
    boolean isWithinRange(double lowerBound, double upperBound);

    /**
     * Determines whether or not an ExchangeRate has changed by less than a
     * specified threshold percentage.
     *
     * @param threshold
     *            the threshold the user uses to view the percentage of change
     *
     * @return true if [(this - previousRate) < (previousRate * threshold /
     *         100)]
     */
    boolean isStable(double threshold);

}
