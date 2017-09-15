package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.database.jdbcPontaj.services.TimetableService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInController {

    @Autowired
    TimetableService serv;

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String returnAfterLogin(HttpServletRequest request, Model md) {
        /*org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginname = auth.getName();
        System.out.println(loginname);
        md.addAttribute("username", serv.findAll(loginname));*/
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "Admin";
        }
        else {
            return "Home";
        }
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
    public String error403(){
        return "LoginError";
    }

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String home(){
        return "Home";
    }

}
