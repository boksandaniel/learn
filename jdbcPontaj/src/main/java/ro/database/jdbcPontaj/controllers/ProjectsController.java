package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.database.jdbcPontaj.services.ProjectsService;


@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projServ;

    @RequestMapping(value = "/projtest", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("projects", projServ.findAll());

        return "projtest";
    }
}
