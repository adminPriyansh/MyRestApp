package com.example.Employer.Controller;

import com.example.Employer.Domain.Employee;
import com.example.Employer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/rest/employer/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/Employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        return ResponseEntity.of(emp);
    }

    @GetMapping(value = "/Employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employeeRecords = new ArrayList<Employee>();
        employeeService.getEmployees().forEach(employeeRecords::add);
        return ResponseEntity.of(Optional.of(employeeRecords));
    }

    @PostMapping(path = "/addEmployee")
    public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee) {
        Employee emp = employeeService.addEmployees(employee);
        return ResponseEntity.of(Optional.of(emp));
    }

    @PostMapping(path = "/updateEmployee")
    public ResponseEntity<Employee> updateEmployees(@RequestBody Employee employee) {
        Employee emp = employeeService.updateEmployee(employee);
        return ResponseEntity.of(Optional.of(emp));
    }
}
