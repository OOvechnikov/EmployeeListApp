package ru.ovechnikov.emplist.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ovechnikov.emplist.api.request.UpdateRequest;
import ru.ovechnikov.emplist.domain.Employee;
import ru.ovechnikov.emplist.domain.EmployeeRowMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class ApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Optional<Employee> getEmployeeById(Integer id) {
        String query =
                "SELECT e.id, e.first_name, e.last_name, e.second_name, e.age, a.address, a.region, a.district, wt.start, wt.finish from employees e " +
                "JOIN addresses a on e.id = a.employee_id " +
                "JOIN work_time wt ON e.id = wt.employee_id " +
                "WHERE e.id = ?";
        return jdbcTemplate.query(query, new EmployeeRowMapper(), id)
                            .stream().findFirst();
    }

    public List<Employee> getEmployeeList(String name, String region, String district) {
        String query = "" +
                "SELECT e.id, e.first_name, e.last_name, e.second_name, e.age, a.address, a.region, a.district, wt.start, wt.finish from employees e " +
                "JOIN addresses a on e.id = a.employee_id " +
                "JOIN work_time wt ON e.id = wt.employee_id " +
                "WHERE (first_name ILIKE CONCAT('%', ?, '%') " +
                    "OR last_name ILIKE CONCAT('%', ?, '%') " +
                    "OR second_name ILIKE CONCAT('%', ?, '%')) " +
                    "AND a.region LIKE CONCAT('%', ?, '%') " +
                    "AND a.district LIKE CONCAT('%', ?, '%') " +
                "ORDER BY id";
        return jdbcTemplate.query(
                query,
                new EmployeeRowMapper(),
                name, name, name, region, district);
    }

    public Integer saveNewEmployee(UpdateRequest request) {
        String sql =
                "INSERT INTO employees (first_name, last_name, second_name, age) " +
                "VALUES ('" + request.getFirstName() + "', '" + request.getLastName() + "', '"
                        + request.getSecondName() + "', " + request.getAge() + ") RETURNING id";
        Integer id = jdbcTemplate.query(sql, rse -> {
            rse.next();
            return rse.getInt("id");
        });

        sql =
                "INSERT INTO addresses (employee_id, address, region, district) " +
                "VALUES (?, ?, ?, ?); " +
                "INSERT INTO work_time (employee_id, start, finish) " +
                "VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                id, request.getAddress(), request.getRegion(), request.getDistrict(),
                id, request.getStart(), request.getFinish());

        return id;
    }

    public Integer updateEmployee(UpdateRequest request) {
        String sql =
                "UPDATE employees SET " +
                "first_name = (?), " +
                "last_name = (?), " +
                "second_name = (?), " +
                "age = (?) " +
                "WHERE id = (?); " +
                "UPDATE addresses SET " +
                "address = (?), " +
                "region = (?), " +
                "district = (?) " +
                "WHERE employee_id = (?); " +
                "UPDATE work_time SET " +
                "start = (?), " +
                "finish = (?) " +
                "WHERE employee_id = (?)";
        return jdbcTemplate.update(sql,
                request.getFirstName(), request.getLastName(), request.getSecondName(), request.getAge(), request.getId(),
                request.getAddress(), request.getRegion(), request.getDistrict(), request.getId(),
                request.getStart(), request.getFinish(), request.getId());
    }

    public Integer deleteEmployeeById(Integer id) {
        String sql =
                "DELETE FROM employees e " +
                "WHERE e.id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<String> getRegionList() {
        String query = "SELECT DISTINCT(a.region) FROM addresses a";
        return jdbcTemplate.query(query,
                (ResultSet rs, int rowNum) -> rs.getString("region"));
    }

    public List<String> getDistrictList() {
        String query = "SELECT DISTINCT(a.district) FROM addresses a";
        return jdbcTemplate.query(query,
                (ResultSet rs, int rowNum) -> rs.getString("district"));
    }
}
