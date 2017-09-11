package ro.database.jdbcPontaj.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.database.jdbcPontaj.services.UsersService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInController {

    @Autowired
    UsersService service;

    @RequestMapping(value={"/","Home"}, method = RequestMethod.GET)
    public String returnAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "Admin";
        }
        return "Timetable";
    }

    @RequestMapping(value={"/Timetable"}, method = RequestMethod.GET)
    public String welcome(){
        return "Timetable";
    }

    @RequestMapping(value="/Admin", method = RequestMethod.GET)
    public String admin(){
        return "Admin";
    }

    @RequestMapping(value={"/Login"}, method = RequestMethod.GET)
    public String login(){
        return "Login";
    }

    @RequestMapping(value="/LoginError", method = RequestMethod.GET)
    public String Error403(){
        return "LoginError";
    }

}
