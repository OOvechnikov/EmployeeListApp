package ru.ovechnikov.emplist.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setSecondName(rs.getString("second_name"));
        String name = employee.getFirstName() + " "
                + employee.getLastName() + " "
                + employee.getSecondName();
        employee.setName(name);
        employee.setAge(rs.getInt("age"));
        employee.setAddress(rs.getString("address"));
        employee.setRegion(rs.getString("region"));
        employee.setDistrict(rs.getString("district"));
        employee.setStart(rs.getTime("start"));
        employee.setFinish(rs.getTime("finish"));
        return employee;
    }
}
