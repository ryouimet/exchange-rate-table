import java.math.BigDecimal;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 * {@code RateTable} represented as a {@code Map} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [the RateTable map is non-null, contains unique keys (currency names),
 *             and each associated BigDecimal value represents a valid exchange rate]
 * </pre>
 * @correspondence <pre>
 * this = [map of currency names (String) to exchange rate values (BigDecimal)]
 * </pre>
 *
 * @author Ryan Ouimet
 *
 */
public class RateTable1 extends RateTableSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Map<String, BigDecimal> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Map1L<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public RateTable1() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final RateTable newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(RateTable source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof RateTable1 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        RateTable1 localSource = (RateTable1) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final ExchangeRate getExchangeRate(String name) {
        return new ExchangeRate(name, this.rep.value(name));
    }

    @Override
    public final void addExchangeRate(String name, BigDecimal rate) {
        this.rep.add(name, rate);
    }

    @Override
    public final ExchangeRate removeExchangeRate(String name) {
        ExchangeRate removed = new ExchangeRate(name, this.rep.value(name));
        this.rep.remove(name);
        return removed;
    }

    @Override
    public final ExchangeRate removeAny() {
        Pair<String, BigDecimal> p = this.rep.removeAny();
        return new ExchangeRate(p.key(), p.value());
    }

    @Override
    public final boolean containsRate(String name) {
        return this.rep.hasKey(name);
    }

    @Override
    public final int size() {
        return this.rep.size();
    }
}
