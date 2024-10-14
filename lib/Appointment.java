package lib;
/*
 * Develop Appointment and Calendar APIs that can be used to
keep track of appointments (by day) in a calendar year. Your goal is to enable clients
to schedule appointments that do not conﬂict and to report current appointments
to clients.
 */




public class Appointment {
    private Date start;
    private Date end;
    private String title;
    
    public Appointment(Date start, Date end, String title){
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public Date getStartDate(){
        return this.start;
    }

    public Date getEndDate(){
        return this.end;
    }

    public String getTitle(){
        return this.title;
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) return true; 
        if (!(other instanceof Appointment)) return false;

        Appointment that = (Appointment) other;
        return this.start == that.getStartDate() && this.end == that.getEndDate();
    }

}