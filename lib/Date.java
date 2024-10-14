package lib;
/*
 * Develop an implementation of Java’s java.util.Date API that is
immutable and therefore corrects the defects of the previous exercise.
 */

public class Date {
    private final int year;
    private final int month;
    private final int day;

    // Constructor ensures no mutation possible
    public Date(int year, int month, int day) {
       
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // Methods for calculations (return NEW ImmutableDate objects)
    public Date addDays(int days) {
        int newYear = year;
        int newMonth = month;
        int newDay = day + days;
    
        // Adjust days based on month lengths
        while (newDay > getDaysInMonth(newYear, newMonth)) {
            newDay -= getDaysInMonth(newYear, newMonth);
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }
    
        return new Date(newYear, newMonth, newDay); 
    }
    
    private int getDaysInMonth(int year, int month) {
        switch (month) {
            case 2: 
                return isLeapYear(year) ? 29 : 28;
            case 4: case 6: case 9: case 11: 
                return 30;
            default: 
                return 31;
        }
    }
    
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    // toString for easy display 
    @Override
    public String toString() {
       return String.format("%d-%02d-%02d", year, month, day); 
    }
    public boolean after(Date other) {
        // Logic: Compare year, then month, then day in order 
        if (this.year > other.year) {
          return true; 
        } else if (this.year == other.year) {
          // Same year check
          if (this.month > other.month) {
            return true; 
          } else if (this.month == other.month) {
            return this.day > other.day;  
          }
        } 
        return false;
    }
    public boolean before(Date other) {
        if (this.year < other.year) {
            return true; 
        } else if (this.year == other.year) {
            if (this.month < other.month) {
                return true; 
            } else if (this.month == other.month) {
                return this.day < other.day;  
            }
        } 
        return false;
    }
    @Override 
    public Object clone() {
        return new Date(this.year, this.month, this.day); 
    }

    public int compareTo(Date other) {
        if (this.equals(other)) return 0; 
        if (this.after(other)) return 1; 
        return -1; 
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true; 
        if (!(other instanceof Date)) return false;

        Date that = (Date) other;
        return this.year == that.year && this.month == that.month && this.day == that.day;
    }
    @Override
    public int hashCode() {
        int result = 17;  // Arbitrary prime as a starting point
        result = 31 * result + year;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }
    public static void main(String[] args){
        Date date = new Date(2023, 11, 15);

        // Getting a new Date
        Date laterDate = date.addDays(165);  
        StdOut.println(laterDate); // Output: 2023-11-25
    }
}
