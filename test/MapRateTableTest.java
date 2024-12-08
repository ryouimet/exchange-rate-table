import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Customized JUnit test fixture for {@code MapRateTable}.
 */
public class MapRateTableTest extends RateTableTest {

    /**
     * Test for addAllExchangeRates on a {@code RateTable} of size 0.
     */
    @Test
    public void testAddAllExchangeRates1() {
        RateTable t1 = new MapRateTable();
        t1.addExchangeRate("US Pennies", new BigDecimal(100));
        t1.addExchangeRate("US Dimes", new BigDecimal(10));

        RateTable t2 = t1.newInstance();
        t2.addAllExchangeRates(t1);

        assertEquals(2, t2.size());
    }

    /**
     * Test for addAllExchangeRates on a {@code RateTable} of size 1.
     */
    @Test
    public void testAddAllExchangeRates2() {
        RateTable t1 = new MapRateTable();
        t1.addExchangeRate("US Pennies", new BigDecimal(100));
        t1.addExchangeRate("US Dimes", new BigDecimal(10));

        RateTable t2 = t1.newInstance();
        t2.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        t2.addAllExchangeRates(t1);

        assertEquals(3, t2.size());
    }

    /**
     * Test for addAllExchangeRates on a {@code RateTable} of size 4 with a
     * duplicate.
     */
    @Test
    public void testAddAllExchangeRates3() {
        RateTable t1 = new MapRateTable();
        t1.addExchangeRate("US Pennies", new BigDecimal(100));
        t1.addExchangeRate("US Dimes", new BigDecimal(10));

        RateTable t2 = t1.newInstance();
        t2.addExchangeRate("US Pennies", new BigDecimal(100));
        t2.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        t2.addExchangeRate("Delta SkyMiles", new BigDecimal(0.012));
        t2.addAllExchangeRates(t1);

        assertEquals(4, t2.size());
    }

    /**
     * Test for addAllExchangeRates on a {@code RateTable} with a duplicate
     * {@code ExchangeRate}.
     */
    @Test
    public void testAddAllExchangeRates4() {
        RateTable t1 = new MapRateTable();
        t1.addExchangeRate("US Pennies", new BigDecimal(100));
        t1.addExchangeRate("US Dimes", new BigDecimal(10));

        RateTable t2 = t1.newInstance();
        t2.addExchangeRate("US Pennies", new BigDecimal(100));
        t2.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        t2.addAllExchangeRates(t1);

        assertEquals(3, t2.size());
    }

    /**
     * Test for getMostValuable on a {@code RateTable} of size 1.
     */
    @Test
    public void testGetMostValuable1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));

        ExchangeRate rExpected = new ExchangeRate("US Pennies",
                new BigDecimal(100));
        assertEquals(rExpected, t.getMostValuable());
    }

    /**
     * Test for getMostValuable on a {@code RateTable} of size 3.
     */
    @Test
    public void testGetMostValuable2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));

        ExchangeRate rExpected = new ExchangeRate("LEGO VIP Points",
                new BigDecimal(6.5));
        assertEquals(rExpected, t.getMostValuable());
    }

    /**
     * Test for getLeastValuable on a {@code RateTable} of size 1.
     */
    @Test
    public void testGetLeastValuable1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));

        ExchangeRate rExpected = new ExchangeRate("US Pennies",
                new BigDecimal(100));
        assertEquals(rExpected, t.getLeastValuable());
    }

    /**
     * Test for getLeastValuable on a {@code RateTable} of size 3.
     */
    @Test
    public void testGetLeastValuable2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));

        ExchangeRate rExpected = new ExchangeRate("US Pennies",
                new BigDecimal(100));
        assertEquals(rExpected, t.getLeastValuable());
    }

    /**
     * Test for populateRatesFromAPI.
     */
    @Test
    public void testPopulateRatesFromAPI() {
        RateTable t = new MapRateTable();
        t.populateRatesFromAPI();
        assertEquals(162, t.size());
    }

}
