package lib;
public class Counter implements Comparable<Counter> {
        private final String name;
        private int count;

        public Counter(String id) {
            name = id;
        }

        public void increment() {
            count++;
        
        }

        public int value() {
         return count;
    }

        @Override
        public String toString() {
            return name + ": " + count;
        }
        @Override public int compareTo(Counter other){
            return Integer.compare(this.count, other.count);
        }
        public static void main(String[] args) {
            int n = Integer.parseInt(args[0]);
            int trials = Integer.parseInt(args[1]);

        // Check for empty array and handle accordingly
            if (n == 0) {
            StdOut.println("No counters created (n is 0)");
            return;
            }

            Counter[] hits = new Counter[n];
            for (int i = 0; i < n; i++) {
                hits[i] = new Counter("counter" + i);
            }

            for (int t = 0; t < trials; t++) {
    // Wrap around for random index exceeding array size
            int randomIndex = StdRandom.uniformInt(n);
            hits[randomIndex % n].increment();
            }

            for (int i = 0; i < n; i++) {
                StdOut.println(hits[i]);
    }
  }
}
