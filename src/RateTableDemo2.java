import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Some use cases for {@code RateTables}.
 *
 * @author Ryan Ouimet
 */
public class RateTableDemo2 {

        /**
         * Main method.
         *
         * @param args
         */
        public static void main(String[] args) {
                BufferedReader in = new BufferedReader(
                                new InputStreamReader(System.in));

                System.out.println(
                                "Let's take a deeper look at the ExchangeRate subclass used in the RateTable.");
                System.out.println(
                                "I love LEGO, so let's keep track of LEGO VIP points to USD.");

                ExchangeRate lego = new ExchangeRate("LEGO VIP Points",
                                new BigDecimal(6.5));

                System.out.println("Current rate of LEGO VIP points to USD: "
                                + lego.rate());
                System.out.println(
                                "Over the years, the prices of LEGO sets have gotten absolutely ridiculous. I would imagine that in 10 years, they will be very inflated. Let's try and predict that.");

                System.out.println(
                                "By what factor do you predict LEGO VIP points will be inflated in 10 years?");

                String input = "";

                try {
                        input = in.readLine();
                        ExchangeRate newLego = new ExchangeRate(
                                        "LEGO VIP Points", lego.multiplyRate(
                                                        new BigDecimal(input)));
                        System.out.println(
                                        "In 10 years, the rate of LEGO VIP points to USD might be "
                                                        + newLego.rate());
                        if (newLego.isStable(lego, new BigDecimal(4.1))) {
                                System.out.println("This inflation is stable.");
                        } else {
                                System.out.println(
                                                "This inflation is unstable.");
                        }

                        RateTable t = new MapRateTable();
                        t.populateRatesFromAPI(); // May not work since env file is uncommitted
                        System.out.println(
                                        "It could even has much as Venezuelan Bolívar, "
                                                        + newLego.convertAmount(
                                                                        t.getExchangeRate(
                                                                                        "VES")));
                } catch (IOException e) {
                        System.err.println("Error reading from input stream");
                }

        }

}
