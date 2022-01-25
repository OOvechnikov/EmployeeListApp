package ru.ovechnikov.emplist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.domain.Employee;

import java.sql.ResultSet;
import java.util.List;

@Component
public class ApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Employee getEmployeeById(String id) {
        String query =
                "SELECT e.id, e.first_name, e.last_name, e.second_name, e.age, a.address, a.region, a.district, wt.start, wt.finish from employees e " +
                "JOIN addresses a on e.id = a.employee_id " +
                "JOIN work_time wt ON e.id = wt.employee_id " +
                "WHERE e.id = '" + id + "'";
        Employee employee = jdbcTemplate.query(query, rse -> {
            if (!rse.next()) {
                return null;
            }
            Employee emp = new Employee();
            emp.setId(rse.getInt("id"));
            emp.setFirstName(rse.getString("first_name"));
            emp.setLastName(rse.getString("last_name"));
            emp.setSecName(rse.getString("second_name"));
            String name = emp.getFirstName() + " "
                    + emp.getLastName() + " "
                    + emp.getSecName();
            emp.setName(name);
            emp.setAge(rse.getInt("age"));
            emp.setAddress(rse.getString("address"));
            emp.setRegion(rse.getString("region"));
            emp.setDistrict(rse.getString("district"));
            emp.setStart(rse.getTime("start"));
            emp.setFinish(rse.getTime("finish"));
            return emp;
        });
        return employee;
    }

    public List<Employee> getEmployeeList() {
        String query = "" +
                "SELECT * FROM employees e " +
                "ORDER BY id";
        List<Employee> list = jdbcTemplate.query(query, (ResultSet rs, int rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            String name = rs.getString("last_name") + " "
                        + rs.getString("first_name") + " "
                        + rs.getString("second_name");
            employee.setName(name);
            employee.setAge(rs.getInt("age"));
            return employee;
        });
        return list;
    }

    public void saveNewEmployee(UpdateRequest request) {
        String sql =
                "INSERT INTO employees (first_name, last_name, second_name, age) " +
                "VALUES ('" + request.getFirstName() + "', '" + request.getLastName() + "', '"
                        + request.getSecName() + "', " + request.getAge() + ") RETURNING id";
        Integer id = jdbcTemplate.query(sql, rse -> {
            rse.next();
            return rse.getInt("id");
        });

        sql =
                "INSERT INTO addresses (employee_id, address, region, district) " +
                "VALUES (" + id + ", '" + request.getAddress() + "', '"
                        + request.getRegion() + "', '" + request.getDistrict() + "');" +
                "INSERT INTO work_time (employee_id, start, finish) " +
                "VALUES (" + id + ", '" + request.getStart() + "', '" + request.getFinish() + "')";
        jdbcTemplate.execute(sql);
    }

    public void updateEmployee(UpdateRequest request) {
        String sql =
                "UPDATE employees SET " +
                "first_name=\'" + request.getFirstName() + "\', " +
                "last_name=\'" + request.getLastName() + "\', " +
                "second_name=\'" + request.getSecName() + "\', " +
                "age=" + request.getAge() +  " " +
                "WHERE id=" + request.getId() + ";" +
                "UPDATE addresses SET " +
                "address=\'" + request.getAddress() + "\', " +
                "region=\'" + request.getRegion() + "\', " +
                "district=\'" + request.getDistrict() + "\' " +
                "WHERE employee_id=" + request.getId() + ";" +
                "UPDATE work_time SET " +
                "start=\'" + request.getStart() + "\', " +
                "finish=\'" + request.getFinish() + "\' " +
                "WHERE employee_id=" + request.getId() + ";";
        jdbcTemplate.execute(sql);
    }

    public void deleteEmployeeById(String id) {
        String sql =
                "DELETE FROM employees e " +
                "WHERE e.id = '" + id + "'";
        jdbcTemplate.execute(sql);
    }

    public List<String> getRegionList() {
        String query = "SELECT DISTINCT(a.region) FROM addresses a";
        List<String> regionList = jdbcTemplate.query(query, 
                (ResultSet rs, int rowNum) -> rs.getString("region"));
        return regionList;
    }

    public List<String> getDistrictList() {
        String query = "SELECT DISTINCT(a.district) FROM addresses a";
        List<String> districtList = jdbcTemplate.query(query,
                (ResultSet rs, int rowNum) -> rs.getString("district"));
        return districtList;
    }
}
