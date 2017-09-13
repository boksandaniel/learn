package ro.database.jdbcPontaj.model;

import java.sql.Time;
import java.util.Date;

public class Timetable {

    private int timetableId;
    private int assignmentId;
    private Date date;
    private Time hoursWorked;


    public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Time hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Timetable() {
    }

    public Timetable(int timetableId, int assignmentId, Date date, Time hoursWorked) {
        this.timetableId = timetableId;
        this.assignmentId = assignmentId;
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

}
