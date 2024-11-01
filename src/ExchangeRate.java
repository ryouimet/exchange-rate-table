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
     */
    void multiplyRate(ExchangeRate inflationRate);

    /**
     * Converts one currency into another using {@code r}.
     *
     * @param r
     *            the exchange rate of the new currency
     * @return the new currency amount
     */
    double convertAmount(ExchangeRate r);

    /**
     * Applies a discount to {@code r}.
     *
     * @param r
     *            the exchange rate that will be lowered with the discount
     */
    void applyDiscount(ExchangeRate r);
  
}