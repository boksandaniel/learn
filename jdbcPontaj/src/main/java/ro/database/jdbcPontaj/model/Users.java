package ro.database.jdbcPontaj.model;

public class Users {

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int jobId;
    private String email;

    public int getUserId() {
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


    public Users(int userId, String firstName, String lastName, String username, String password, int jobId){
        this.userId=userId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;
        this.jobId=jobId;
    }

    public void setEmail(String email){
        this.email=email;
    }
}
