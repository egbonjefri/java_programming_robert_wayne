import lib.Queue;
import lib.StdRandom;
import lib.StdOut;
/*
 * Modify MM1Queue (PROGRAM 4.3.7) to make a program MD1Queue that sim-
ulates a queue for which the service times are ﬁxed (deterministic) at rate of μ.
Verify Little’s law for this model.
 */
public class MD1Queue {

    static class Customer {
        double arrivalTime;
        double serviceTime;

        Customer(double arrivalTime, double serviceTime) {
            this.arrivalTime = arrivalTime;
            this.serviceTime = serviceTime;
        }
    }

    public static void main(String[] args) {
        double lambda = 1.0; // Arrival rate (customers per time unit)
        double mu = 1.5;     // Service rate (customers per time unit)
        double simulationTime = 10000.0; // Total time for the simulation

        // Calculated Parameters
        double serviceTime = 1.0 / mu;
        double currentTime = 0.0;
        double nextArrivalTime = StdRandom.exponential(lambda);

        Queue<Customer> queue = new Queue<>();
        double totalWaitingTime = 0.0;
        int totalCustomers = 0;

        while (currentTime < simulationTime) {
            // If no customers in the queue, wait for next arrival
            if (queue.isEmpty()) {
                currentTime = nextArrivalTime;
                nextArrivalTime += StdRandom.exponential(lambda);
                queue.enqueue(new Customer(currentTime, serviceTime));
            } else {
                // Serve the next customer
                Customer currentCustomer = queue.dequeue();
                double waitTime = Math.max(0, currentTime - currentCustomer.arrivalTime);
                totalWaitingTime += waitTime + currentCustomer.serviceTime;
                currentTime += currentCustomer.serviceTime;

                // Schedule next arrival
                if (currentTime > nextArrivalTime) {
                    queue.enqueue(new Customer(nextArrivalTime, serviceTime));
                    nextArrivalTime += StdRandom.exponential(lambda);
                }

                totalCustomers++;
            }
        }

        // Compute results
        double averageNumberInSystem = totalWaitingTime / simulationTime;
        double averageTimeInSystem = totalWaitingTime / totalCustomers;
        double arrivalRate = totalCustomers / simulationTime;

        // Verify Little's Law: L = λW
        StdOut.printf("Average number in system (L): %.3f%n", averageNumberInSystem);
        StdOut.printf("Average time in system (W): %.3f%n", averageTimeInSystem);
        StdOut.printf("Arrival rate (λ): %.3f%n", arrivalRate);
        StdOut.printf("Verifying Little's Law: %.3f = %.3f * %.3f%n", averageNumberInSystem, arrivalRate, averageTimeInSystem);
    }


}
