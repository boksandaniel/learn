package ro.database.jdbcPontaj.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Timetable {

    private int timetableId;
    private int assignmentId;
    private Date date;
    private String project;

    @NotNull
    @Min(0)
    @Max(12)
    private int hoursWorked;


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

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }


    public Timetable() {
    }

    public Timetable(int timetableId, String project, Date date, int hoursWorked) {
        this.timetableId = timetableId;
        this.project=project;
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

    public Timetable(int timetableId, int assignmentId, Date date, int hoursWorked) {
        this.timetableId = timetableId;
        this.assignmentId = assignmentId;
        this.date = date;
        this.hoursWorked = hoursWorked;
    }

}
