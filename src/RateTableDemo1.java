import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Some use cases for {@code RateTables}.
 *
 * @author Ryan Ouimet
 */
public class RateTableDemo1 {

        /**
         * Demo for the {@code RateTable} component.
         *
         * @param args
         *                the command line arguments
         */
        public static void main(String[] args) {
                BufferedReader in = new BufferedReader(
                                new InputStreamReader(System.in));

                System.out.println(
                                "The Rate Table has a variety of uses. Have you ever googled something like \"EUR to USD\"? We can achieve a similar result here:");
                RateTable t = new MapRateTable();
                t.getCurrentExchangeRates();

                ExchangeRate r = t.getExchangeRate("EUR");
                System.out.println(
                                "EUR to USD from the Rate Table: " + r.rate());

                System.out.println(
                                "Try for yourself! Enter a currency code to compare to USD. Examples are: EUR, GBP, and JPY.");
                String inputCurrencyCode = "";

                try {
                        inputCurrencyCode = in.readLine();
                        r = t.getExchangeRate(inputCurrencyCode);
                        System.out.println(inputCurrencyCode
                                        + " to USD from the Rate Table: "
                                        + r.rate());
                } catch (IOException e) {
                        System.err.println("Error reading from input stream");
                }

                System.out.println(
                                "You can also add your own exchange rates to the Rate Table, like rewards points!");
                System.out.println(
                                "Say I want to add AMEX Points to USD, and 100 AMEX Points are equal to $1.");

                t.addExchangeRate("AMEX Points", new BigDecimal(100));
                r = t.getExchangeRate("AMEX Points");
                System.out.println("AMEX Points to USD from the Rate Table: "
                                + r.rate());
                System.out.println(
                                "I can see the most valuable currency in the Rate Table, which is "
                                                + t.getMostValuable().name()
                                                + " valued at "
                                                + t.getMostValuable().rate()
                                                + ".");
                System.out.println(
                                "Of course, I can also see the least valuable currency in the Rate Table, which is "
                                                + t.getLeastValuable().name()
                                                + " valued at "
                                                + t.getLeastValuable().rate()
                                                + ".");
                System.out.println("That's the Rate Table! Enjoy!");
        }

}
