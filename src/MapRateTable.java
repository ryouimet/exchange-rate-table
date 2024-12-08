import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public class MapRateTable extends RateTableSecondary {

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
        this.rep = new HashMap<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public MapRateTable() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final void clear() {
        this.createNewRep();
    }

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
    public final void transferFrom(RateTable source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof MapRateTable : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        MapRateTable localSource = (MapRateTable) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void addExchangeRate(ExchangeRate r) {
        assert r != null : "Violation of: r is not null";
        this.rep.put(r.name(), r.rate());
    }

    @Override
    public final void addExchangeRate(String name, BigDecimal rate) {
        assert name != null : "Violation of: name is not null";
        assert rate != null : "Violation of: rate is not null";
        this.rep.put(name, rate);
    }

    @Override
    public final boolean containsRate(String name) {
        return this.rep.containsKey(name);
    }

    @Override
    public final Set<ExchangeRate> exchangeRateSet() {
        Set<ExchangeRate> exchangeRateSet = new HashSet<>();
        for (Map.Entry<String, BigDecimal> e : this.rep.entrySet()) {
            exchangeRateSet.add(new ExchangeRate(e.getKey(), e.getValue()));
        }
        return exchangeRateSet;
    }

    @Override
    public final ExchangeRate getExchangeRate(String name) {
        assert name != null : "Violation of: name is not null";
        return new ExchangeRate(name, this.rep.get(name));
    }

    @Override
    public final Collection<BigDecimal> rates() {
        return this.rep.values();
    }

    @Override
    public final ExchangeRate removeAny() {
        assert this.rep != null : "Violation of: this is not null";
        String key = this.rep.keySet().iterator().next();
        return new ExchangeRate(key, this.rep.remove(key));
    }

    @Override
    public final ExchangeRate removeExchangeRate(String name) {
        assert name != null : "Violation of: name is not null";
        ExchangeRate removed = new ExchangeRate(name, this.rep.get(name));
        this.rep.remove(name);
        return removed;
    }

    @Override
    public final int size() {
        assert this.rep != null : "Violation of: this is not null";
        return this.rep.size();
    }

    @Override
    public final Set<String> nameSet() {
        return this.rep.keySet();
    }

}
