package ru.ovechnikov.emplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.api.response.ResultResponse;
import ru.ovechnikov.emplist.mapper.AddressMapper;
import ru.ovechnikov.emplist.mapper.EmployeeMapper;
import ru.ovechnikov.emplist.mapper.WorkTimeMapper;
import ru.ovechnikov.emplist.model.Employee;

import java.util.List;

@Service
public class ApplicationService {

    private final EmployeeMapper employeeMapper;
    private final AddressMapper addressMapper;
    private final WorkTimeMapper workTimeMapper;

    @Autowired
    public ApplicationService(EmployeeMapper employeeMapper, AddressMapper addressMapper, WorkTimeMapper workTimeMapper) {
        this.employeeMapper = employeeMapper;
        this.addressMapper = addressMapper;
        this.workTimeMapper = workTimeMapper;
    }

    public List<Employee> getEmployeeList(String name, String region, String district) {
        if (region.equals("Region")) region = "";
        if (district.equals("District")) district = "";
        return employeeMapper.getEmployeeList(name, region, district);
    }

    public Employee getEmployeeById(int id) {
        return employeeMapper.getEmployeeById(id);
    }

    public ResultResponse createNewEmployee(UpdateRequest request) {
        employeeMapper.saveNewEmployee(request);
        addressMapper.saveNewAddress(request);
        workTimeMapper.saveNewWorkTime(request);
        return new ResultResponse(true);
    }

    public ResultResponse updateEmployee(UpdateRequest request) {
        employeeMapper.updateEmployee(request);
        addressMapper.updateAddress(request);
        workTimeMapper.updateWorkTime(request);
        return new ResultResponse(true);
    }

    public ResultResponse deleteEmployee(Integer id) {
        employeeMapper.deleteEmployeeById(id);
        return new ResultResponse(true);
    }

    public List<String> getRegionList() {
        return addressMapper.getRegionList();
    }

    public List<String> getDistrictList() {
        return addressMapper.getDistrictList();
    }
}
