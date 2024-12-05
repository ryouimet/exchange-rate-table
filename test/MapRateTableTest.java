import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Customized JUnit test fixture for {@code MapRateTable}.
 */
public class MapRateTableTest extends RateTableTest {

    /**
     * Test for populateRatesFromAPI.
     */
    @Test
    public void testAPIPull1() {
        RateTable t = new MapRateTable();
        t.populateRatesFromAPI();
        assertEquals(162, t.size());
    }

    /**
     * Test for getMostValuable on a {@code RateTable} of size 1.
     */
    @Test
    public void testGetMostValuable1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));

        ExchangeRate rExpected = new ExchangeRate("penniesToUSD",
                new BigDecimal(100));
        assertEquals(rExpected, t.getMostValuable());
    }

    /**
     * Test for getMostValuable on a {@code RateTable} of size 3.
     */
    @Test
    public void testGetMostValuable2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));

        ExchangeRate rExpected = new ExchangeRate("legoVIPToUSD",
                new BigDecimal(6.5));
        assertEquals(rExpected, t.getMostValuable());
    }

    /**
     * Test for getLeastValuable on a {@code RateTable} of size 1.
     */
    @Test
    public void testGetLeastValuable1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));

        ExchangeRate rExpected = new ExchangeRate("penniesToUSD",
                new BigDecimal(100));
        assertEquals(rExpected, t.getLeastValuable());
    }

    /**
     * Test for getLeastValuable on a {@code RateTable} of size 3.
     */
    @Test
    public void testGetLeastValuable2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));

        ExchangeRate rExpected = new ExchangeRate("penniesToUSD",
                new BigDecimal(100));
        assertEquals(rExpected, t.getLeastValuable());
    }

}
