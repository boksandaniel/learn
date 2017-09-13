package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Timetable;
import ro.database.jdbcPontaj.services.AssignmentsService;
import ro.database.jdbcPontaj.services.TimetableService;

import java.sql.Time;
import java.util.Date;

@Controller
public class TimetableController {

    @Autowired
    TimetableService service;

    @Autowired
    AssignmentsService serv;

    @RequestMapping(value = {"/{username}", "/Timetable/{username}"}, method = RequestMethod.GET)
    public String index(@PathVariable String username, Model md){
        md.addAttribute("timetables", service.findAll(username));

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
                         @RequestParam("hoursWorked") @DateTimeFormat(pattern = "hh:mm:ss") Time hoursWorked, Model md) {
        Timetable timetable = new Timetable();
        timetable.setAssignmentId(assignmentId);
        timetable.setDate(date);
        timetable.setHoursWorked(hoursWorked);
        md.addAttribute("timetables", service.timetableAdd(timetable));
        return "redirect:/Timetable";
    }
}
