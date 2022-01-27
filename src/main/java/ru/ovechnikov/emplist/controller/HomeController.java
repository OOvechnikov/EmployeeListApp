package ru.ovechnikov.emplist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ovechnikov.emplist.api.request.ExportRequest;
import ru.ovechnikov.emplist.service.ApplicationService;
import ru.ovechnikov.emplist.service.ExcelExporter;

import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    private final ApplicationService applicationService;
    private final ExcelExporter excelExporter;

    @Autowired
    public HomeController(ApplicationService applicationService, ExcelExporter excelExporter) {
        this.applicationService = applicationService;
        this.excelExporter = excelExporter;
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
        model.addAttribute("isAdmin", SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "/home";
    }

    @PostMapping("/home/export")
    public void exportToExcel(@RequestBody ExportRequest request,
                              HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "epmloyee_list.xlsx");
        excelExporter.exportExc(response, request);
    }
}
