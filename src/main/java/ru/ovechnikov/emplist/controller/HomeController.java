package ru.ovechnikov.emplist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ovechnikov.emplist.service.ApplicationService;

@Controller
public class HomeController {

    private final ApplicationService applicationService;

    @Autowired
    public HomeController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(@RequestParam(value = "search", defaultValue = "") String searchValue,
                           Model model) {
        model.addAttribute("employeeList", applicationService.getEmployeeList(searchValue));
        return "/home";
    }
}
