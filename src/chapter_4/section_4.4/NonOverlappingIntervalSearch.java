import lib.ArrayList;
import lib.StdOut;
/*
 * Given a list of non-overlapping intervals of integers, write a java static method that takes an integer argument and determines in
which, if any, interval that value lies. For example, if the intervals are 1643–2033,
5532–7643, 8999–10332, and 5666653–5669321, then the query point 9122 lies in
the third interval and 8122 lies in no interval.

 */
public class NonOverlappingIntervalSearch {

    // Represents an interval with start and end points
    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + "-" + end;
        }
    }

    /**
     * Finds the interval in which the query point lies.
     * 
     * @param intervals List of non-overlapping intervals
     * @param queryPoint The point to search for
     * @return The interval containing the query point, or null if not found
     */
    public static Interval findInterval(ArrayList<Interval> intervals, int queryPoint) {
        // Using binary search for efficiency
        int left = 0;
        int right = intervals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Interval current = intervals.get(mid);

            if (queryPoint >= current.start && queryPoint <= current.end) {
                return current;
            } else if (queryPoint < current.start) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ArrayList<Interval> intervals = ArrayList.of(
            new Interval(1643, 2033),
            new Interval(5532, 7643),
            new Interval(8999, 10332),
            new Interval(5666653, 5669321)
        );

        int[] queryPoints = {9122, 8122, 1800, 5667000};

        for (int point : queryPoints) {
            Interval result = findInterval(intervals, point);
            if (result != null) {
                StdOut.println("Query point " + point + " lies in interval " + result);
            } else {
                StdOut.println("Query point " + point + " lies in no interval");
            }
        }
    }
}