package ro.database.jdbcExample;

public class User {
    private int id;
    private String name;
    private int age;
    private String email;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%d\t%s", id, name, age, email);
    }
}
