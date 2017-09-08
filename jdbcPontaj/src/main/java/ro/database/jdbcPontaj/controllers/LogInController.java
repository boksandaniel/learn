package ro.database.jdbcPontaj.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogInController {

    @RequestMapping(value={"/","Home"})
    public String home(){
        return "Home";
    }

    @RequestMapping(value={"/Welcome"})
    public String welcome(){
        return "Welcome";
    }

    @RequestMapping(value="/Admin")
    public String admin(){
        return "Admin";
    }

    @RequestMapping(value={"/Login"})
    public String login(){
        return "Login";
    }


    @RequestMapping(value="/LoginError")
    public String Error403(){
        return "LoginError";
    }

}
