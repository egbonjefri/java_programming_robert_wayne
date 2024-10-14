import lib.StdOut;
public class Chaos {
    public static void main(String[] args) {

        for (double r = 0.1; r <= 3.0; r += 0.1) { // Iterate through a range of r values
            double population = 0.1; // Initial population (between 0 and 1)

            StdOut.println("For r = " + r);

            population = r * population * (1 - population); // Population growth formula

            StdOut.println("Population = " + population);

            StdOut.println(); // Separate output for different r values
        }
    }
}