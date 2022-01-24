package ru.ovechnikov.emplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.api.response.ResultResponse;
import ru.ovechnikov.emplist.domain.Employee;
import ru.ovechnikov.emplist.repository.ApplicationRepository;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    public List<Employee> getEmployeeList(String name, String age, String region, String district) {
        //TODO: Сделать поиск по всем полям
        List<Employee> employeeList = applicationRepository.getEmployeeList();
        return employeeList;
    }

    public Employee getEmployeeById(String id) {
        return applicationRepository.getEmployeeById(id);
    }

    public ResultResponse addEmployee(Employee request) {
        applicationRepository.saveNewEmployee(request);
        return new ResultResponse(true);
    }

    public ResultResponse updateEmployee(UpdateRequest request) {
        applicationRepository.updateEmployee(request);
        return new ResultResponse(true);
    }

    public ResultResponse deleteEmployee(String id) {
        applicationRepository.deleteEmployeeById(id);
        return new ResultResponse(true);
    }

    public List<String> getRegionList() {
        List<String> regionList = applicationRepository.getRegionList();
        return regionList;
    }

    public List<String> getDistrictList() {
        List<String> districtList = applicationRepository.getDistrictList();
        return districtList;
    }
}
