package ro.database.jdbcPontaj;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import ro.database.jdbcPontaj.controllers.*;

@SpringBootApplication
@EnableAsync
public class App
{
    @Autowired
    UsersController service;

    @Autowired
    ProjectsController projServ;

    @Autowired
    JobsController jobService;

    @Autowired
    AssignmentsController serv;

    @Autowired
    TimetableController timetable;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

}
