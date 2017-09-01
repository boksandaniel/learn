package ro.database.jdbcTest.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Users {

   // @Id
   // @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private int age;
    private String email;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Users(){

    }

    public Users(int id, String name, int age){
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public void setEmail(String email){
        this.email=email;
    }
}
