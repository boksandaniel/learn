package ro.database.jdbcPontaj;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import ro.database.jdbcPontaj.controllers.ProjectsController;
import ro.database.jdbcPontaj.controllers.UsersController;

@SpringBootApplication
@EnableAsync
public class App
{
    @Autowired
    UsersController service;

    @Autowired
    ProjectsController projServ;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

}
