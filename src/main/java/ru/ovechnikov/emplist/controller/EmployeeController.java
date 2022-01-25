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
        model.addAttribute("regionList", applicationService.getRegionList());
        model.addAttribute("districtList", applicationService.getDistrictList());
        Employee employee;
        if (id.equals("new")) {
            employee = Employee.buildNewEmployee();
        } else {
            employee = applicationService.getEmployeeById(id);
        }
        model.addAttribute("emp", employee);
        return "/employee";
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<ResultResponse> createNewEmployee(@RequestBody UpdateRequest request) {
        ResultResponse response = applicationService.createNewEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ResultResponse> updateEmployee(@RequestBody UpdateRequest request) {
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
