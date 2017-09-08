package ro.database.jdbcPontaj.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.database.jdbcPontaj.model.Jobs;
import ro.database.jdbcPontaj.services.JobsService;

@Controller
public class JobsController {


    @Autowired
    JobsService jobService;

    @RequestMapping(value = "/Jobs", method = RequestMethod.GET)
    public String index(Model md){
        md.addAttribute("jobs", jobService.findAll());

        return "Jobs";
    }

    @RequestMapping(value = "Jobs/{jobId}/deleteJob", method = RequestMethod.GET)
    public String del (Model md, @PathVariable int jobId) {
        md.addAttribute("jobs", jobService.delete(jobId));
        return "redirect:/Jobs";
    }

    @RequestMapping(value="Jobs/AddJob", method = RequestMethod.GET)
    public String newUser(Model md) {
        md.addAttribute("jobs", jobService.findRole());

        return "AddJob";
    }

    @RequestMapping(value = "/createJob", method = RequestMethod.POST)
    public String create(@RequestParam("title") String title,
                         @RequestParam("role") String role, Model md) {
        Jobs job = new Jobs();
        job.setTitle(title);
        job.setRole(role);
        md.addAttribute("jobs", jobService.jobAdd(job));
        return "redirect:/Jobs";
    }

    @RequestMapping(value = "/updateJob", method = RequestMethod.POST)
    public String update(@RequestParam("jobId") int jobId,
                         @RequestParam("title") String title,
                         @RequestParam("role") String role,
                         Model md) {
        Jobs job = jobService.findOne(jobId);
        job.setTitle(title);
        job.setRole(role);
        md.addAttribute("jobs", jobService.jobUpdate(job));
        return "redirect:/Jobs";
    }


    @RequestMapping(value = "Jobs/{jobId}/EditJob", method = RequestMethod.GET)
    public String edit(@PathVariable int jobId, Model model) {
        Jobs job = jobService.findOne(jobId);
        model.addAttribute("job", job);
        model.addAttribute("jobs", jobService.findRole());
        return "EditJob";
    }
}
