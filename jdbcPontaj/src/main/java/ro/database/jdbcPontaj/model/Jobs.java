package ro.database.jdbcPontaj.model;

public class Jobs {

    private int jobId;
    private String title;
    private String accessRights;


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

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public Jobs(){

    }

    public Jobs(int jobId, String title, String accessRights){
        this.jobId=jobId;
        this.accessRights=accessRights;
        this.title=title;
    }
}
