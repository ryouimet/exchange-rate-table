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

    /*
     * Tests for common methods
     */

    /**
     * Test for toString.
     */
    @Test
    public void testToString() {
        ExchangeRate r = new ExchangeRate("US Pennies", new BigDecimal(100));
        assertEquals("ExchangeRate[name=US Pennies, rate=100]", r.toString());
    }

    /*
     * Tests for standard methods
     */

    /**
     * Test for newInstance.
     */
    @Test
    public void testNewInstance() {
        RateTable t1 = new MapRateTable();
        RateTable t2 = t1.newInstance();
        assertEquals(t1.getClass(), t2.getClass());
    }

    /**
     * Test for clear on an {@code RateTable} of length 0.
     */
    @Test
    public void testClear1() {
        RateTable t = new MapRateTable();
        t.clear();
        assertEquals(0, t.size());
    }

    /**
     * Test for clear on an {@code RateTable} of length 1.
     */
    @Test
    public void testClear2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.clear();
        assertEquals(0, t.size());
    }

    /**
     * Test for clear on an {@code RateTable} of length 3.
     */
    @Test
    public void testClear3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        t.clear();
        assertEquals(0, t.size());
    }

    /**
     * Test for transferFrom.
     */
    @Test
    public void testTransferFrom() {
        RateTable t1 = new MapRateTable();
        t1.addExchangeRate("US Pennies", new BigDecimal(100));
        RateTable t2 = new MapRateTable();
        t2.transferFrom(t1);
        assertEquals(t1.getClass(), t2.getClass());
        assertEquals(new ExchangeRate("US Pennies", new BigDecimal(100)),
                t2.getExchangeRate("US Pennies"));
    }

    /*
     * Tests for kernel methods
     */

    /**
     * Test for getExchangeRate.
     */
    @Test
    public void testGetExchangeRate() {

        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));

        ExchangeRate rExpected = new ExchangeRate("US Pennies",
                new BigDecimal(100));

        ExchangeRate rActual = t.getExchangeRate("US Pennies");

        assertEquals(rExpected, rActual);
    }

    /**
     * Test for addExchangeRate on a {@code RateTable} of size 0.
     */
    @Test
    public void testAddExchangeRate1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        assertEquals(1, t.size());
    }

    /**
     * Test for addExchangeRate on a {@code RateTable} of size 3.
     */
    @Test
    public void testAddExchangeRate3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        assertEquals(3, t.size());
    }

    /**
     * Test for removeExchangeRate on a {@code RateTable} of size 1.
     */
    @Test
    public void testRemoveExchangeRate1() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        ExchangeRate rExpected = new ExchangeRate("US Pennies",
                new BigDecimal(100));
        ExchangeRate rActual = t.removeExchangeRate("US Pennies");

        assertEquals(rExpected, rActual);
        assertEquals(0, t.size());
    }

    /**
     * Test for removeExchangeRate on a {@code RateTable} of size 2.
     */
    @Test
    public void testRemoveExchangeRate2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));

        ExchangeRate rExpected = new ExchangeRate("US Pennies",
                new BigDecimal(100));

        ExchangeRate rActual = t.removeExchangeRate("US Pennies");

        assertEquals(rExpected, rActual);
        assertEquals(2, t.size());
    }

    /**
     * Test for removeAny on a {@code RateTable} of size 1.
     */
    @Test
    public void testRemoveAny1() {
        RateTable tExpected = new MapRateTable();
        tExpected.addExchangeRate("US Pennies", new BigDecimal(100));

        RateTable tActual = new MapRateTable();
        tActual.addExchangeRate("US Pennies", new BigDecimal(100));

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
        tExpected.addExchangeRate("US Pennies", new BigDecimal(100));
        tExpected.addExchangeRate("US Dimes", new BigDecimal(10));
        tExpected.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));

        RateTable tActual = new MapRateTable();
        tActual.addExchangeRate("US Pennies", new BigDecimal(100));
        tActual.addExchangeRate("US Dimes", new BigDecimal(10));
        tActual.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));

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
        assertEquals(false, t.containsRate("US Pennies"));
    }

    /**
     * Test for containsRate on a {@code RateTable} of size 2 (false).
     */
    @Test
    public void testContainsRate2() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        assertEquals(false, t.containsRate("US Pennies"));
    }

    /**
     * Test for containsRate on a {@code RateTable} of size 3 (true).
     */
    @Test
    public void testContainsRate3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        assertEquals(true, t.containsRate("US Pennies"));
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
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        assertEquals(1, t.size());
    }

    /**
     * Test for size on a {@code RateTable} of size 3.
     */
    @Test
    public void sizeTest3() {
        RateTable t = new MapRateTable();
        t.addExchangeRate("US Pennies", new BigDecimal(100));
        t.addExchangeRate("US Dimes", new BigDecimal(10));
        t.addExchangeRate("LEGO VIP Points", new BigDecimal(6.5));
        assertEquals(3, t.size());
    }

}
