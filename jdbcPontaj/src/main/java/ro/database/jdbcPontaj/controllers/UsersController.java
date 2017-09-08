package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Users;
import ro.database.jdbcPontaj.services.JobsService;
import ro.database.jdbcPontaj.services.UsersService;


@Controller
public class UsersController {

    @Autowired
    UsersService service;

    @Autowired
    JobsService jobsService;

    @RequestMapping(value = "/Users", method = RequestMethod.GET)
    public String index(Model md) {
        md.addAttribute("users", service.findAll());

        return "Users";
    }

    @RequestMapping(value = "Users/{userId}/deleteUser", method = RequestMethod.GET)
    public String del (Model md, @PathVariable int userId) {
        md.addAttribute("users", service.delete(userId));
        return "redirect:/Users";
    }

    @RequestMapping(value="Users/AddUser", method = RequestMethod.GET)
    public String newUser(Model md) {
        md.addAttribute("jobs", jobsService.findAll());
        return "AddUser";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String create(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("enabled") boolean enabled,
                         @RequestParam("email") String email,
                         @RequestParam("jobId") int jobId,
                         Model md) {
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setEmail(email);
        user.setJobId(jobId);
        md.addAttribute("users", service.userAdd(user));
        return "redirect:/Users";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String update(@RequestParam("userId") Long userId,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("enabled") boolean enabled,
                         @RequestParam("email") String email,
                         @RequestParam("jobId") int jobId,
                         Model md) {
        Users user = service.findOne(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setJobId(jobId);
        user.setEmail(email);
        md.addAttribute("users", service.userUpdate(user));
        return "redirect:/Users";
    }


    @RequestMapping(value = "Users/{userId}/EditUser", method = RequestMethod.GET)
    public String edit(@PathVariable Long userId, Model model) {
        Users user = service.findOne(userId);
        model.addAttribute("user", user);
        model.addAttribute("jobs", jobsService.findAll());
        return "EditUser";
    }
}