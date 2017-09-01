package ro.database.jdbcTest.model;

public class JobTitle {



    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JobTitle(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public JobTitle(){

    }

}
