/*
 * Develop a data type for the time of day. Provide client methods that
return the current hour, minute, and second, as well as toString(), equals(), and
hashCode()methods. Develop two implementations: one that keeps the time as a
single int value (number of seconds since midnight) and another that keeps three
int values, one each for seconds, minutes, and hours.
 */



public class TimeOfDay implements Comparable<TimeOfDay> {
    private int secondsSinceMidnight;

    // Constructor
    public TimeOfDay(int hour, int minute, int second) {
        if (!isValidTime(hour, minute, second)) {
            throw new IllegalArgumentException("Invalid time values");
        }
        this.secondsSinceMidnight = hour * 3600 + minute * 60 + second;
    }

    // Validation helper
    private boolean isValidTime(int hour, int minute, int second) {
        return (0 <= hour && hour <= 23) &&
               (0 <= minute && minute <= 59) &&
               (0 <= second && second <= 59);
    }

    // Getters
    public int getHour() {
        return secondsSinceMidnight / 3600; 
    }

    public int getMinute() {
        return (secondsSinceMidnight % 3600) / 60;
    }

    public int getSecond() {
        return secondsSinceMidnight % 60;
    }
   // Calculates elapsed seconds between this TimeOfDay and another.
   public int elapsedSecondsSince(TimeOfDay other) {
    int difference = this.secondsSinceMidnight - other.secondsSinceMidnight;

    // Handle the case where difference might be negative (e.g., earlier time)
    if (difference < 0) {
        difference += 24 * 3600;  // Add 24 hours worth of seconds
    }
    return difference; 
}
    // toString for easy display
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
    }
    /*
     * Modify Time (EXERCISE 3.3.21) so that it implements the Comparable inter-
face (comparing the times chronologically).
     */
    @Override
    public int compareTo(TimeOfDay other){
        return Integer.compare(this.secondsSinceMidnight, other.secondsSinceMidnight);
    }
    // equals for comparison
    @Override
    public boolean equals(Object other) {
        if (other == this) return true; // Same object
        if (!(other instanceof TimeOfDay)) return false; // Not a TimeOfDay

        TimeOfDay that = (TimeOfDay) other;  
        return this.getHour() == that.getHour() && this.getMinute() == that.getMinute() && this.getSecond() == that.getSecond();
    }

    // hashCode for use in hash-based collections
    @Override
    public int hashCode() {
        int result = 17; // Arbitrary prime number
        result = 31 * result + getHour();
        result = 31 * result + getMinute();
        result = 31 * result + getSecond();
        return result;
    }
}
