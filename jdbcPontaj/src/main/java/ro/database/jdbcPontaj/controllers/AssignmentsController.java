package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Assignments;
import ro.database.jdbcPontaj.services.AssignmentsService;
import ro.database.jdbcPontaj.services.ProjectsService;
import ro.database.jdbcPontaj.services.UsersService;

@Controller
public class AssignmentsController {

    @Autowired
    AssignmentsService serv;

    @Autowired
    UsersService userServ;

    @Autowired
    ProjectsService projServ;

    @RequestMapping(value = "/Assignments", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("assignments", serv.findAll());

        return "Assignments";
    }

    @RequestMapping(value = "Assignments/{assignmentId}/deleteAssignment", method = RequestMethod.GET)
    public String del (Model md, @PathVariable int assignmentId) {
        md.addAttribute("assignments", serv.delete(assignmentId));
        return "redirect:/Assignments";
    }

    @RequestMapping(value="Assignments/AddAssignment", method = RequestMethod.GET)
    public String newUser(Model md) {
        md.addAttribute("users", userServ.findAll());
        md.addAttribute("projects", projServ.findAll());
        return "AddAssignment";
    }

    @RequestMapping(value = "/createAssignment", method = RequestMethod.POST)
    public String create(@RequestParam("userId") int userId,
                         @RequestParam("projectId") int projectId, Model md) {
        Assignments assign = new Assignments();
        assign.setUserId(userId);
        assign.setProjectId(projectId);
        md.addAttribute("assignments", serv.assignAdd(assign));
        return "redirect:/Assignments";
    }

    @RequestMapping(value = "/updateAssignment", method = RequestMethod.POST)
    public String update(@RequestParam("assignmentId") int assignmentId,
                         @RequestParam("userId") int userId,
                         @RequestParam("projectId") int projectId,
                         Model md) {
        Assignments assign = serv.findOne(assignmentId);
        assign.setUserId(userId);
        assign.setProjectId(projectId);
        md.addAttribute("assignments", serv.assignUpdate(assign));
        return "redirect:/Assignments";
    }


    @RequestMapping(value = "Assignments/{assignmentId}/EditAssignment", method = RequestMethod.GET)
    public String edit(@PathVariable int assignmentId, Model model) {
        Assignments assign = serv.findOne(assignmentId);
        model.addAttribute("assignment", assign);
        model.addAttribute("users", userServ.findAll());
        model.addAttribute("projects", projServ.findAll());
        return "EditAssignment";
    }
}
