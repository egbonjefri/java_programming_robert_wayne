package lib;



public class MM1Simulation {
    private double arrivalRate;
    private double serviceRate;
    private int maxCustomers;

    public MM1Simulation(double arrivalRate, double serviceRate, int maxCustomers) {
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
        this.maxCustomers = maxCustomers;
    }

    protected double getInterarrivalTime() {
        return StdRandom.exponential(arrivalRate);
    }

    protected double getServiceTime() {
        return StdRandom.exponential(serviceRate);
    }

    public abstract class MM1Base {
        protected ArrayList<Double> waitingTimes = new ArrayList<>();
        protected double totalTime = 0;
        protected int totalCustomers = 0;

        public abstract void addCustomer(double arrivalTime);
        public abstract double removeCustomer();

        public ArrayList<Double> simulate() {
            double time = 0;
            double nextArrival = getInterarrivalTime();
            double nextDeparture = Double.POSITIVE_INFINITY;

            while (totalCustomers < maxCustomers) {
                if (nextArrival < nextDeparture) {
                    time = nextArrival;
                    addCustomer(time);
                    nextArrival = time + getInterarrivalTime();
                    if (totalCustomers == 1) {
                        nextDeparture = time + getServiceTime();
                    }
                } else {
                    time = nextDeparture;
                    double waitingTime = removeCustomer();
                    waitingTimes.add(waitingTime);
                    if (totalCustomers > 0) {
                        nextDeparture = time + getServiceTime();
                    } else {
                        nextDeparture = Double.POSITIVE_INFINITY;
                    }
                }
            }
            totalTime = time;
            return waitingTimes;
        }

        public double getAverageWaitingTime() {
            return waitingTimes.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        }

        public double getAverageNumberInSystem() {
            return waitingTimes.stream().mapToDouble(Double::doubleValue).sum() / totalTime;
        }

        public double getArrivalRate() {
            return totalCustomers / totalTime;
        }
    }

    public class MM1Queue extends MM1Base {
        private Queue<Double> queue = new Queue<>();

        @Override
        public void addCustomer(double arrivalTime) {
            queue.enqueue(arrivalTime);
            totalCustomers++;
        }

        @Override
        public double removeCustomer() {
            double arrivalTime = queue.dequeue();
            return totalTime - arrivalTime;
        }
    }

    public class MM1Stack extends MM1Base {
        private Stack<Double> stack = new Stack<>();

        @Override
        public void addCustomer(double arrivalTime) {
            stack.push(arrivalTime);
            totalCustomers++;
        }

        @Override
        public double removeCustomer() {
            double arrivalTime = stack.pop();
            return totalTime - arrivalTime;
        }
    }

    public class MM1RandomQueue extends MM1Base {
        private ArrayList<Double> randomQueue = new ArrayList<>();

        @Override
        public void addCustomer(double arrivalTime) {
            randomQueue.add(arrivalTime);
            totalCustomers++;
        }

        @Override
        public double removeCustomer() {
            int index = StdRandom.uniformInt(randomQueue.size());
            double arrivalTime = randomQueue.remove(index);
            return totalTime - arrivalTime;
        }
    }
}