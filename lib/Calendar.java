package lib;
/*
 * 
 */

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Calendar {
    private int year;
    private Map<Date, ArrayList<Appointment>> appointments; 

    public Calendar(int year){
        this.year = year;
        this.appointments = new HashMap<>();
    }

    public void addAppointment(Appointment appointment) {
        Date date = appointment.getStartDate(); 
        if (!appointments.containsKey(date)) {
            appointments.put(date, new ArrayList<>());
        }
        if (hasConflicts(appointment, date)) {
            throw new IllegalArgumentException("Appointment conflicts with existing schedule");
        } 
        this.appointments.get(date).add(appointment);
    }

    public List<Appointment> getAppointmentsForDay(Date date) {
        return this.appointments.getOrDefault(date, new ArrayList<>()); 
    }

    private boolean hasConflicts(Appointment newAppointment, Date date) {
        List<Appointment> existingAppointments = this.appointments.get(date);
        for (Appointment existing : existingAppointments) {
            if (existing.equals(newAppointment)) {
                return true;
            }
        }
        return false;
    }

    public int getYear(){
        return this.year;
    }

} 
