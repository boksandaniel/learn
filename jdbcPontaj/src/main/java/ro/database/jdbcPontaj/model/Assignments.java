package ro.database.jdbcPontaj.model;

public class Assignments {
    private int assignmentId;
    private int userId;
    private int projectId;
    private String name;
    private String project;

    public int getAssignmentId() {
        return assignmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Assignments(){

    }

    public Assignments(int assignmentId, String name, String project){
        this.assignmentId=assignmentId;
        this.name=name;
        this.project=project;
    }

    public Assignments(int assignmentId, int userId, int projectId) {
        this.assignmentId = assignmentId;
        this.userId = userId;
        this.projectId = projectId;
    }
}
