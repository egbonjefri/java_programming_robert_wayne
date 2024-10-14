/*
 * Develop a data type that supports the
following operations: insert a date, search for a date, and count the number of dates
in the data structure that lie in a particular interval. Use Java’s java.util.Date
data type.
 */

import java.util.Date;
import lib.BST;
import lib.StdOut;
import java.time.LocalDate;
import java.time.ZoneId;

public class OneDimensionalRangeSearch {
    private BST<Date, Integer> dateMap;

    public OneDimensionalRangeSearch() {
        dateMap = new BST<>();
    }
    // Insert a date into the data structure
    public void insertDate(Date date) {
        if (!dateMap.contains(date)) {
            dateMap.put(date, 0);
        } else {
            dateMap.put(date, dateMap.get(date) + 1);
        }    
    }
    // Search for a specific date
    public boolean searchDate(Date date) {
        return dateMap.contains(date);
    }
    // Count the number of dates in the given interval [startDate, endDate]
    public int countDatesInRange(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            return 0;
        }
        return dateMap.rangeCount(startDate, endDate);
    }


    public static void main(String[] args) {
        OneDimensionalRangeSearch dateRangeBST = new OneDimensionalRangeSearch();

        Date date1 = Date.from(LocalDate.of(2023 - 1900, 8, 27).atStartOfDay(ZoneId.systemDefault()).toInstant()); // 2023-08-27
        Date date2 = Date.from(LocalDate.of(2023 - 1900, 8, 27).atStartOfDay(ZoneId.systemDefault()).toInstant()); // 2023-08-28
        Date date3 = Date.from(LocalDate.of(2023 - 1900, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()); // 2023-09-01


        // Insert dates
        dateRangeBST.insertDate(date1);
        dateRangeBST.insertDate(date2);
        dateRangeBST.insertDate(date3);

        // Search for a date
        StdOut.println("Is 2023-08-27 present? " + dateRangeBST.searchDate(date1)); // Output: true

        // Count dates in a range
        Date startDate = Date.from(LocalDate.of(2023 - 1900, 8, 26).atStartOfDay(ZoneId.systemDefault()).toInstant()); // 2023-08-26
        Date endDate = Date.from(LocalDate.of(2023 - 1900, 8, 30).atStartOfDay(ZoneId.systemDefault()).toInstant());   // 2023-08-30

        StdOut.println("Number of dates between 2023-08-26 and 2023-08-30: "
                + dateRangeBST.countDatesInRange(startDate, endDate)); // Output: 2
    }
}
