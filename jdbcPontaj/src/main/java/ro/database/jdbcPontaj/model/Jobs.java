package ro.database.jdbcPontaj.model;

public class Jobs {

    private int jobId;
    private String title;
    private String role;


    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Jobs(){

    }

    public Jobs(String role){
        this.role = role;
    }

    public Jobs(int jobId, String title, String role){
        this.jobId=jobId;
        this.role = role;
        this.title=title;
    }
}
