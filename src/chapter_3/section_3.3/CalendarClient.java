
import lib.Appointment;
import lib.Calendar;
import lib.StdOut;
import lib.Date;

import java.util.List;

public class CalendarClient {
    public static void main(String[] args) {
        // Create a Calendar
        Calendar calendar = new Calendar(2023); // For the year 2023

        // Create some sample appointments (assuming your Appointment has start, end, description)
        Appointment meeting1 = new Appointment(new Date(2023, 11, 25), new Date(2023, 11, 25), "Team Meeting");
        Appointment docAppointment = new Appointment(new Date(2023, 11, 28), new Date(2023, 11, 28), "Doctor's Appointment");
        Appointment projectDeadline = new Appointment(new Date(2023, 11, 30), new Date(2023, 11, 30), "Project Deadline");

        // Test adding appointments
        calendar.addAppointment(meeting1);
        calendar.addAppointment(docAppointment);
        calendar.addAppointment(projectDeadline);

        // Test conflict detection (you might need to adjust based on isConflict implementation)
        try {
            Appointment conflictingAppt = new Appointment(new Date(2023, 11, 25), new Date(2023, 11, 25), "Overlapping Meeting");
            calendar.addAppointment(conflictingAppt);
        } catch (IllegalArgumentException e) {
            StdOut.println("Conflict detected: " + e.getMessage()); 
        }

        // Test retrieving appointments
        Date targetDate = new Date(2023, 11, 28);
        List<Appointment> appointmentsOnDate = calendar.getAppointmentsForDay(targetDate);
        StdOut.println("Appointments on " + targetDate + " :");
        for (Appointment appt : appointmentsOnDate) {
            StdOut.println(appt.getTitle()); 
        } 
    }
} 
