package ro.database.jdbcPontaj.model;


import java.util.Date;

public class Projects {
    private int projectId;
    private String projectName;
    private Date startDate;
    private Date endDate;


    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Projects(){

    }

    public Projects(int projectId, String projectName, Date startDate, Date endDate){
        this.projectId =projectId;
        this.projectName =projectName;
        this.startDate =startDate;
        this.endDate=endDate;
    }

}
