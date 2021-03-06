package ro.database.jdbcPontaj.model;

public class Users {

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int jobId;
    private String email;
    private boolean enabled;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }


    public boolean getEnabled() {
        return enabled;
    }

    public int getUserId(){
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getJobId() {
        return jobId;
    }

    public String getEmail() {
        return email;
    }

    public Users(){

    }


    public Users(int userId, String firstName, String lastName, String username, String password, boolean enabled,
                 int jobId){
        this.userId=userId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;
        this.enabled = enabled;
        this.jobId=jobId;
    }

    public void setEmail(String email){
        this.email=email;
    }
}
