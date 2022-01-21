package ru.ovechnikov.emplist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.api.response.ResultResponse;
import ru.ovechnikov.emplist.domain.Employee;
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
    public String getEmployeePage(@PathVariable("id") String id,
                                  Model model) {
        Employee employee = applicationService.getEmployeeById(id);
        if (employee == null) {
            return "redirect:/home";
        }
        model.addAttribute("emp", employee);
        model.addAttribute("regionList", applicationService.getRegionList());
        model.addAttribute("districtList", applicationService.getDistrictList());
        return "/employee";
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResultResponse> updateEmployee(@RequestBody Employee request,
                                                         @PathVariable("id") String id) {
        ResultResponse response = applicationService.updateEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResultResponse> deleteEmployee(@PathVariable("id") String id) {
        ResultResponse response = applicationService.deleteEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
