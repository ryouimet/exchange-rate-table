
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * {@code ExchangeRate} represented as a {@code Double} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']
 * </pre>
 * @correspondence <pre>
 * this = [the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Ryan Ouimet
 *
 */

public class ExchangeRate {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Double rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Double(0);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public ExchangeRate() {
        this.createNewRep();
    }

    /**
     * Constructor from {@code double}.
     *
     * @param d
     *            {@code double} to initialize from
     */
    public ExchangeRate(double d) {
        this.rep = new Double(d);
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public ExchangeRate(int i) {
        this.rep = new Double(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public ExchangeRate(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches(
                "-?\\d+(\\.\\d{1,2})?") : "Violation of: s must be a double value";

        this.rep = new Double(s);
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    /**
     * Sets an exchange rate based on the ratio of currency2 to currency1.
     *
     * @param currency1
     *            the currency in the denominator
     *
     * @param currency2
     *            the currency in the numerator
     *
     */
    private void setRate(double currency1, double currency2) {
        this.rep = Double.valueOf(currency2 / currency1);
    }

    /**
     * Determines whether or not an ExchangeRate is worthless.
     *
     * @return true if the ExchangeRate is <= 0.
     */
    private boolean isWorthless() {
        return this.rep.compareTo(0.0) <= 0;
    }

    /*
     * Secondary methods
     */

    /**
     * Compares the values of two ExchangeRates.
     *
     * @param r
     * @return 0 if {@code this} is equal to r, a positive number is
     *         {@code this} is greater than r, or a negative number if
     *         {@code this} is less than r.
     */
    private int compareValue(ExchangeRate r) {
        return this.rep.compareTo(r.rep);
    }

    /**
     * Inflates or deflates {@code this} by a given {@code ExchangeRate}.
     *
     * @param r
     */
    private void multiplyRate(ExchangeRate r) {
        assert r != null : "Violation of: r is not null";
        this.rep *= r.rep;
    }

    /**
     * New toString method.
     */
    @Override
    public String toString() {
        return this.rep.toString();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println(
                "Welcome! Let's see some the ExchangeRate methods in action. The three we will see today are:");
        out.println("setRate, isWorthless, compareValue, and multiplyRate");
        out.println();

        // Showcase setRate
        out.println("Let's start with setRate.");
        out.println();
        out.println(
                "Enter two currencies to make an ExchangeRate! The rate will be the ratio of the second entry to the first!");
        double currency1 = Double.valueOf(in.nextLine());
        double currency2 = Double.valueOf(in.nextLine());

        ExchangeRate r1 = new ExchangeRate();

        r1.setRate(currency1, currency2);

        out.println("The exchange rate of " + currency2 + " to " + currency1
                + " is " + r1.toString());
        out.println();

        // Showcase isWorthless
        out.println("Let's check out the isWorthless method!");
        out.println();
        if (r1.isWorthless()) {
            out.println(r1.toString() + "is ≤ 0, so it's worthless!");
        } else {
            out.println(r1.toString() + "is ≥ 0, so it is NOT worthless!");
        }
        out.println();

        // Showcase compareValue
        out.println("Let's check out compareValue.");
        out.println();
        out.println(
                "Enter a new ExchangeRate to compare to the value of the other one:");
        ExchangeRate r2 = new ExchangeRate(in.nextLine());
        if (r1.compareValue(r2) > 0) {
            out.println(r1.toString() + " is greater than " + r2.toString());
        } else if (r1.compareValue(r2) < 0) {
            out.println(r1.toString() + " is less than " + r2.toString());
        } else {
            out.println(r1.toString() + " is equal to " + r2.toString());
        }
        out.println();

        // Showcase multiplyRate
        out.println("Let's see the multiplyRate method.");
        out.println();
        out.println(
                "Enter an ExchangeRate to multiply your previous ExchangeRate by:");
        String inflationRateString = in.nextLine();
        ExchangeRate inflRate = new ExchangeRate(inflationRateString);
        r1.multiplyRate(inflRate);
        out.println("Your old rate is now " + r1.toString() + ".");
        out.println();

        // Wrap up
        out.println("That's all for now! Goodbye!");
        in.close();
        out.close();
    }

}
