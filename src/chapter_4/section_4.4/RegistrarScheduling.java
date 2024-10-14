/*
 * The registrar at a prominent northeastern university
recently scheduled an instructor to teach two different classes at the same exact
time. Help the registrar prevent future mistakes by describing a method to check
for such conﬂicts. For simplicity, assume all classes run for 50 minutes and start at
9, 10, 11, 1, 2, or 3.
 */


import lib.ST;
import lib.Queue;
import lib.StdOut;


public class RegistrarScheduling {

    // Method to check conflicts
    public static boolean checkConflicts(ST<Integer, Queue<String>> schedules, int key, String subject, String[] instructors){
        // Retrieve the queue associated with the time slot
        Queue<String> val = schedules.get(key);

        // Check if any of the instructors are already scheduled at this time
        for (String instructor : instructors) {
            if (val.contains(instructor)) {
                StdOut.println("Conflict detected for instructor: " + instructor + " at time " + key);
                return true;  // Conflict detected
            }
        }
        // If no conflicts, add the instructor and subject to the schedule
        for (String instructor : instructors) {
            val.enqueue(instructor);  // Add instructor to the time slot
        }
        StdOut.println("No conflict. " + subject + " scheduled at " + key);
        return false;  // No conflict
    }

    public static void main(String[] args) {
        // Array of available time slots
        int[] arr = {9, 10, 11, 1, 2, 3};

        // Instructors' classes
        String[] instructor1 = {"Chemistry", "Biology", "Physics"};
        String[] instructor2 = {"Calculus", "Linear Algebra", "Statistics"};

        // Symbol Table for schedules
        ST<Integer, Queue<String>> schedules = new ST<>();

        // Initialize each time slot with an empty queue
        for (int i = 0; i < arr.length; i++) {
            schedules.put(arr[i], new Queue<String>());
        }

        // Check for scheduling conflicts
        checkConflicts(schedules, 9, "Chemistry", instructor1);  // Scheduling for instructor 1
        checkConflicts(schedules, 9, "Calculus", instructor2);    // Scheduling for instructor 2 at the same time
    }
}