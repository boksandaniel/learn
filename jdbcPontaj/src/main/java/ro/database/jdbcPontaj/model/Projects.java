package ro.database.jdbcPontaj.model;

public class Projects {
    private int projectId;
    private String projectName;
    private String startDate;
    private String endDate;


    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Projects(int projectId, String projectName, String startDate, String endDate){
        this.projectId =projectId;
        this.projectName =projectName;
        this.startDate =startDate;
        this.endDate=endDate;
    }

}
