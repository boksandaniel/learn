package ro.database.jdbcTest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.database.jdbcTest.model.Users;
import ro.database.jdbcTest.services.JobTitleService;
import ro.database.jdbcTest.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UsersController {

    @Autowired
    UserService service;

    @Autowired
    JobTitleService jobTitleService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("users", service.findAll());

        return "user";
    }

    @RequestMapping(value = "user/{id}/delete", method = RequestMethod.GET)
    public String del (Model md, @PathVariable Long id) {
        md.addAttribute("users", service.delete(id));
        return "redirect:/user";
    }

    @RequestMapping(value="user/new", method = RequestMethod.GET)
    public String newUser(Model md) {
        md.addAttribute("jobTitles", jobTitleService.getAll());
        return "new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("name") String name, @RequestParam("age") int age,
                         @RequestParam("email") String email, Model md) {
        Users user = new Users();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        md.addAttribute("users", service.userAdd(user));
        return "redirect:/user";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") Long id,
                         @RequestParam("name") String name,
                         @RequestParam("age") int age,
                         @RequestParam("email") String email,
                         Model md) {
        Users user = service.findOne(id);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        md.addAttribute("users", service.userUpdate(user));
        return "redirect:/user";
    }


    @RequestMapping(value = "user/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Users user = service.findOne(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @RequestMapping(value="test/pag1", method = RequestMethod.GET)
    public String testpag1(@CookieValue(value = "mycookie", defaultValue = "defaultCookieVal") String cookieValue,
                           Model md){
        System.out.println(cookieValue);
        md.addAttribute("attribute", cookieValue);
        return "testcookie";
    }

    @RequestMapping(value="test/pag2", method = RequestMethod.GET)
    public String testpag2(){
        return "testcookie";
    }


    @RequestMapping(value="test/setcookie", method = RequestMethod.GET)
    public String testset(HttpServletResponse response){
        Cookie cookie = new Cookie("mycookie", "testvalue");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        return "redirect:/test/pag1";
    }


    //https://stackoverflow.com/questions/36575698/spring-mvc-how-to-display-data-from-database-into-a-table
}
