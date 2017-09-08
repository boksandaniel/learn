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

@Controller
public class AssignmentsController {

    @Autowired
    AssignmentsService serv;

    @RequestMapping(value = "/Assignments", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("assignments", serv.findAll());

        return "Assignments";
    }

    @RequestMapping(value = "Assignments/{assignmentId}/deleteAssign", method = RequestMethod.GET)
    public String del (Model md, @PathVariable int assignmentId) {
        md.addAttribute("assignments", serv.delete(assignmentId));
        return "redirect:/Assignments";
    }

    @RequestMapping(value="Assignments/AddAssignment", method = RequestMethod.GET)
    public String newUser(Model md) {

        return "AddAssignment";
    }

    @RequestMapping(value = "/createAssign", method = RequestMethod.POST)
    public String create(@RequestParam("userId") int userId,
                         @RequestParam("projectId") int projectId, Model md) {
        Assignments assign = new Assignments();
        assign.setUserId(userId);
        assign.setProjectId(projectId);
        md.addAttribute("assignments", serv.assignAdd(assign));
        return "redirect:/Assignments";
    }

    @RequestMapping(value = "/updateAssign", method = RequestMethod.POST)
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
        return "EditAssignment";
    }
}
