package ru.ovechnikov.emplist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.api.response.ResultResponse;
import ru.ovechnikov.emplist.model.Employee;
import ru.ovechnikov.emplist.service.ApplicationService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final ApplicationService applicationService;

    @Autowired
    public EmployeeController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping("/{id}")
    public String getEmployeePage(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("regionList", applicationService.getRegionList());
        model.addAttribute("districtList", applicationService.getDistrictList());
        model.addAttribute("emp", applicationService.getEmployeeById(id));
        model.addAttribute("isAdmin", SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "/employee";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/new")
    public String getNewEmployeePage(Model model) {
        model.addAttribute("regionList", applicationService.getRegionList());
        model.addAttribute("districtList", applicationService.getDistrictList());
        model.addAttribute("emp", Employee.buildEmpty());
        model.addAttribute("isAdmin", SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return "/employee";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    @ResponseBody
    public ResponseEntity<ResultResponse> createNewEmployee(@RequestBody UpdateRequest request) {
        ResultResponse response = applicationService.createNewEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResultResponse> updateEmployee(@RequestBody UpdateRequest request) {
        ResultResponse response = applicationService.updateEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResultResponse> deleteEmployee(@PathVariable("id") Integer id) {
        ResultResponse response = applicationService.deleteEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
