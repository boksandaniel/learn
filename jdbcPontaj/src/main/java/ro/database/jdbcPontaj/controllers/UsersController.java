package ro.database.jdbcPontaj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Users;
import ro.database.jdbcPontaj.services.UsersService;


@Controller
public class UsersController {

    @Autowired
    UsersService service;

    @RequestMapping(value = "/Users", method = RequestMethod.GET)
    public String index(Model md) {
        md.addAttribute("users", service.findAll());

        return "Users";
    }

    @RequestMapping(value = "user/{id}/delete", method = RequestMethod.GET)
    public String del (Model md, @PathVariable Long id) {
        md.addAttribute("users", service.delete(id));
        return "redirect:/user";
    }

    @RequestMapping(value="user/new", method = RequestMethod.GET)
    public String newUser(Model md) {
       // md.addAttribute("jobTitles", jobTitleService.getAll());
        return "new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("jobId") int jobId, Model md) {
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setJobId(jobId);
        md.addAttribute("users", service.userAdd(user));
        return "redirect:/user";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("userName") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email,
                         @RequestParam("jobId") int jobId,
                         Model md) {
        Users user = service.findOne(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setJobId(jobId);
        md.addAttribute("users", service.userUpdate(user));
        return "redirect:/user";
    }


    @RequestMapping(value = "user/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Users user = service.findOne(id);
        model.addAttribute("user", user);
        return "edit";
    }
}