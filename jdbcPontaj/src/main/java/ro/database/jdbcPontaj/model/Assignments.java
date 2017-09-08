package ro.database.jdbcPontaj.model;

public class Assignments {
    private int assignmentId;
    private int userId;
    private int projectId;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
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

    public Assignments(){

    }

    public Assignments(int assignmentId, int userId, int projectId) {
        this.assignmentId = assignmentId;
        this.userId = userId;
        this.projectId = projectId;
    }


}
