package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Timetable;
import ro.database.jdbcPontaj.services.AssignmentsService;
import ro.database.jdbcPontaj.services.TimetableService;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class TimetableController {

    @Autowired
    TimetableService service;

    @Autowired
    AssignmentsService serv;

    @RequestMapping(value = {"/Timetable"}, method = RequestMethod.GET)
    public String index(Model md){
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loginname = auth.getName();
        md.addAttribute("timetables", service.findAll(loginname));
        return "Timetable";
    }


    @RequestMapping(value="Timetable/AddToTimetable", method = RequestMethod.GET)
    public String newUser(Model md) {
        md.addAttribute("assignments", serv.findAll());
        return "AddToTimetable";
    }

    @RequestMapping(value = "/createEntry", method = RequestMethod.POST)
    public String create(@RequestParam("assignmentId") int assignmentId,
                         @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                         @RequestParam("hoursWorked") int hoursWorked,
                         @Valid Timetable timetable, BindingResult bindingResult,
                         Model md) {
        timetable = new Timetable();
        timetable.setAssignmentId(assignmentId);
        timetable.setDate(date);
        timetable.setHoursWorked(hoursWorked);
        md.addAttribute("timetables", service.timetableAdd(timetable));
     if (bindingResult.hasErrors()) {
            return "AddToTimetable";
        }
        return "redirect:/Timetable";
    }

  /*  @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@Valid @ModelAttribute("timetable") Timetable timetable, BindingResult bindingResult,
                           Model md){
        if (bindingResult.hasErrors()) {
            return "createEntry";
        }
        return "redirect:/Timetable";
    }



    @PostMapping("/")
    public String checkPersonInfo(@Valid Timetable timetable, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "AddToTimetable";
        }

        return "redirect:/Timetable";
    }*/
}
