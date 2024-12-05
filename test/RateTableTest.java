import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * JUnit test fixture for {@code RateTable}'s kernel methods.
 *
 * @author Ryan Ouimet
 *
 */
public abstract class RateTableTest {

    /**
     * Test for getExchangeRate.
     */
    @Test
    public void testGetExchangeRate() {

        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToDollar", new BigDecimal(100));

        ExchangeRate rExpected = new ExchangeRate("penniesToDollar",
                new BigDecimal(100));

        ExchangeRate rActual = t.getExchangeRate("penniesToDollar");

        assertEquals(rExpected, rActual);
    }

    /**
     * Test for addExchangeRate on a {@code RateTable} of size 0.
     */
    @Test
    public void testAddExchangeRate1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToDollar", new BigDecimal(100));
        assertEquals(1, t.size());
    }

    /**
     * Test for addExchangeRate on a {@code RateTable} of size 3.
     */
    @Test
    public void testAddExchangeRate3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));
        assertEquals(3, t.size());
    }

    /**
     * Test for removeExchangeRate on a {@code RateTable} of size 1.
     */
    @Test
    public void testRemoveExchangeRate1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        ExchangeRate rExpected = new ExchangeRate("penniesToUSD",
                new BigDecimal(100));
        ExchangeRate rActual = t.removeExchangeRate("penniesToUSD");

        assertEquals(rExpected, rActual);
        assertEquals(0, t.size());
    }

    /**
     * Test for removeExchangeRate on a {@code RateTable} of size 2.
     */
    @Test
    public void testRemoveExchangeRate2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));

        ExchangeRate rExpected = new ExchangeRate("penniesToUSD",
                new BigDecimal(100));

        ExchangeRate rActual = t.removeExchangeRate("penniesToUSD");

        assertEquals(rExpected, rActual);
        assertEquals(2, t.size());
    }

    /**
     * Test for removeAny on a {@code RateTable} of size 1.
     */
    @Test
    public void testRemoveAny1() {
        RateTable tExpected = new MapRateTable();
        tExpected.addExchangeRate("penniesToUSD", new BigDecimal(100));

        RateTable tActual = new MapRateTable();
        tActual.addExchangeRate("penniesToUSD", new BigDecimal(100));

        ExchangeRate rActual = tActual.removeAny();
        assertEquals(true, tExpected.containsRate(rActual.name()));

        ExchangeRate rExpected = tExpected.removeExchangeRate(rActual.name());
        assertEquals(rExpected, rActual);
    }

    /**
     * Test for removeAny on a {@code RateTable} of size 3.
     */
    @Test
    public void testRemoveAny2() {
        RateTable tExpected = new MapRateTable();
        tExpected.addExchangeRate("penniesToUSD", new BigDecimal(100));
        tExpected.addExchangeRate("dimesToUSD", new BigDecimal(10));
        tExpected.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));

        RateTable tActual = new MapRateTable();
        tActual.addExchangeRate("penniesToUSD", new BigDecimal(100));
        tActual.addExchangeRate("dimesToUSD", new BigDecimal(10));
        tActual.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));

        ExchangeRate rActual = tActual.removeAny();
        assertEquals(true, tExpected.containsRate(rActual.name()));

        ExchangeRate rExpected = tExpected.removeExchangeRate(rActual.name());
        assertEquals(rExpected, rActual);
    }

    /**
     * Test for containsRate on a {@code RateTable} of size 0 (false).
     */
    @Test
    public void testContainsRate1() {
        RateTable t = new MapRateTable();
        assertEquals(false, t.containsRate("penniesToUSD"));
    }

    /**
     * Test for containsRate on a {@code RateTable} of size 2 (false).
     */
    @Test
    public void testContainsRate2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));
        assertEquals(false, t.containsRate("penniesToUSD"));
    }

    /**
     * Test for containsRate on a {@code RateTable} of size 3 (true).
     */
    @Test
    public void testContainsRate3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));
        assertEquals(true, t.containsRate("penniesToUSD"));
    }

    /**
     * Test for size on a {@code RateTable} of size 0.
     */
    @Test
    public void sizeTest1() {
        RateTable t = new MapRateTable();
        assertEquals(0, t.size());
    }

    /**
     * Test for size on a {@code RateTable} of size 1.
     */
    @Test
    public void sizeTest2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        assertEquals(1, t.size());
    }

    /**
     * Test for size on a {@code RateTable} of size 3.
     */
    @Test
    public void sizeTest3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("penniesToUSD", new BigDecimal(100));
        t.addExchangeRate("dimesToUSD", new BigDecimal(10));
        t.addExchangeRate("legoVIPToUSD", new BigDecimal(6.5));
        assertEquals(3, t.size());
    }

}
