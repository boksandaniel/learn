package ro.database.jdbcTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import ro.database.jdbcTest.controllers.UsersController;

@SpringBootApplication
@EnableAsync
public class App
{
    @Autowired
    UsersController service;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

}
