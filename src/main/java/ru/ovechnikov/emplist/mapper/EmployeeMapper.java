package ru.ovechnikov.emplist.mapper;

import org.apache.ibatis.annotations.Mapper;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.model.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    Employee getEmployeeById(int id);

    List<Employee> getEmployeeList(String name, String region, String district);

    int saveNewEmployee(UpdateRequest request);

    void updateEmployee(UpdateRequest request);

    void deleteEmployeeById(int id);

}
