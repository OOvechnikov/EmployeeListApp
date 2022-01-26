package ru.ovechnikov.emplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.api.response.CreateResponse;
import ru.ovechnikov.emplist.api.response.ResultResponse;
import ru.ovechnikov.emplist.domain.Employee;
import ru.ovechnikov.emplist.exception.EmployeeNotFoundException;
import ru.ovechnikov.emplist.repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    public List<Employee> getEmployeeList(String name, String region, String district) {
        if (region.equals("Region")) region = "";
        if (district.equals("District")) district = "";
        return applicationRepository.getEmployeeList(name, region, district);
    }

    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        return applicationRepository.getEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id = %s not found", id)));
    }

    public ResultResponse createNewEmployee(UpdateRequest request) {
        int id = applicationRepository.saveNewEmployee(request);
        return new CreateResponse(true, id);
    }

    public ResultResponse updateEmployee(UpdateRequest request)  {
        try {
            getEmployeeById(request.getId());
        } catch (EmployeeNotFoundException e) {
            return new ResultResponse(false);
        }
        applicationRepository.updateEmployee(request);
        return new ResultResponse(true);
    }

    public ResultResponse deleteEmployee(Integer id) {
        try {
            getEmployeeById(id);
        } catch (EmployeeNotFoundException e) {
            return new ResultResponse(false);
        }
        applicationRepository.deleteEmployeeById(id);
        return new ResultResponse(true);
    }

    public List<String> getRegionList() {
        return applicationRepository.getRegionList();
    }

    public List<String> getDistrictList() {
            return applicationRepository.getDistrictList();
    }
}
