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
    public String homePage(@RequestParam(value = "name", defaultValue = "") String name,
                           @RequestParam(value = "region", defaultValue = "") String region,
                           @RequestParam(value = "district", defaultValue = "") String district,
                           Model model) {
        model.addAttribute("employeeList", applicationService.getEmployeeList(name, region, district));
        model.addAttribute("regionList", applicationService.getRegionList());
        model.addAttribute("districtList", applicationService.getDistrictList());
        return "/home";
    }
}
