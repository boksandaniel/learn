package ro.database.jdbcExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App implements CommandLineRunner
{
    @Autowired
    UserServices service;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(strings.length);
        if(strings.length>=2){
            String name = strings[0];
            int age = Integer.parseInt(strings[1]);
            service.addUser(name, age);
        }
        else
            service.findAll().forEach(System.out::println);

    }
}

