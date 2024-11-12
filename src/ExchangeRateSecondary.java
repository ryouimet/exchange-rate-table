import java.math.BigDecimal;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 * Layered implementations of secondary methods for {@code ExhangeRate}.
 */
public abstract class ExchangeRateSecondary implements ExchangeRate {

    /*
     * Public members ---------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Pair<String, BigDecimal> rep;

    /*
     * Common methods (from Object) -------------------------------------------
     */

    /**
     * New toString method that displays the BigDecimal value of the
     * ExchangeRate pair to a String.
     */
    @Override
    public String toString() {
        return this.getRateValue().toString();
    }

    /**
     * Inflates or deflates a rate by a certain amount.
     *
     * @param inflationRate
     *            the rate that {@code this} is multiplied by.
     * @ensures this = #this * inflationRate
     */
    @Override
    public void multiplyRate(ExchangeRate inflationRate) {
        assert inflationRate != null : "Violation of: r is not null";

        BigDecimal newRateValue = this.getRateValue()
                .multiply(inflationRate.getRateValue());

        Map<String, BigDecimal> tempMap = new Map1L<>();
        tempMap.add(this.rep.key(), newRateValue);

        this.rep = tempMap.remove(this.rep.key());
    }

    /**
     * Converts one currency into another using {@code r}.
     *
     * @param r
     *            the exchange rate of the new currency
     * @return the new currency amount
     */
    @Override
    public BigDecimal convertAmount(ExchangeRate r) {
        assert r != null : "Violation of: r is not null";
        return this.getRateValue().multiply(r.getRateValue());
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
     */
    @Override
    public boolean isWithinRange(BigDecimal lowerBound, BigDecimal upperBound) {
        return (lowerBound.compareTo(this.getRateValue()) <= 0
                && this.getRateValue().compareTo(upperBound) <= 0);
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
     */
    @Override
    public boolean isStable(ExchangeRate previousRate, BigDecimal threshold) {

        BigDecimal currentRateValue = this.getRateValue();
        BigDecimal previousRateValue = previousRate.getRateValue();

        return ((currentRateValue.subtract(previousRateValue))
                .compareTo((previousRateValue.multiply(threshold))
                        .divide(new BigDecimal(100))) < 0);
    }

}
