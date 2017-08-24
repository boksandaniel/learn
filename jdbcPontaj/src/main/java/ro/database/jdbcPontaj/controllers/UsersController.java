package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.database.jdbcPontaj.model.Users;
import ro.database.jdbcPontaj.services.UsersService;

import java.util.List;
import java.util.Map;


@Controller
public class UsersController {

    @Autowired
    UsersService service;

    @RequestMapping(value = "/usertest", method = RequestMethod.GET)
    public String index(Model md) {
        md.addAttribute("users", service.findAll());

        return "usertest";
    }
}