package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Projects;
import ro.database.jdbcPontaj.services.ProjectsService;

import java.util.Date;


@Controller
public class ProjectsController {

    @Autowired
    ProjectsService projServ;

    @RequestMapping(value = "/Projects", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("projects", projServ.findAll());

        return "Projects";
    }

    @RequestMapping(value = "Projects/{projectId}/deleteProject", method = RequestMethod.GET)
    public String del (Model md, @PathVariable int projectId) {
        md.addAttribute("users", projServ.delete(projectId));
        return "redirect:/Projects";
    }

    @RequestMapping(value="Projects/AddProject", method = RequestMethod.GET)
    public String newUser(Model md) {
        return "AddProject";
    }

    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public String create(@RequestParam("projectName") String projectName,
                         @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, Model md) {
        Projects project = new Projects();
        project.setProjectName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        md.addAttribute("projects", projServ.projectAdd(project));
        return "redirect:/Projects";
    }

    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public String update(@RequestParam("projectId") int projectId,
                         @RequestParam("projectName") String projectName,
                         @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                         Model md) {
        Projects project = projServ.findOne(projectId);
        project.setProjectName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        md.addAttribute("projects", projServ.projectUpdate(project));
        return "redirect:/Projects";
    }


    @RequestMapping(value = "Projects/{projectId}/EditProject", method = RequestMethod.GET)
    public String edit(@PathVariable int projectId, Model model) {
        Projects project = projServ.findOne(projectId);
        model.addAttribute("project", project);
        return "EditProject";
    }
}
