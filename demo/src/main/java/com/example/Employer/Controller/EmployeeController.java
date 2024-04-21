package com.example.Employer.Controller;

import com.example.Employer.Domain.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class EmployeeController {
    static List<Employee> employeeRecords = new ArrayList<>();
    static{
        Employee emp1 = new Employee();
        emp1.setEmpName("Priyansh");
        emp1.setEmpAge(27);
        emp1.setEmpSalary(100000L);
        emp1.setId(1);
        Employee emp2 = new Employee();
        emp2.setEmpName("Shrishti");
        emp2.setEmpAge(27);
        emp2.setEmpSalary(100000L);
        emp2.setId(2);

        Employee emp3 = new Employee();
        emp3.setEmpName("Honey");
        emp3.setEmpAge(27);
        emp3.setEmpSalary(100000L);
        emp3.setId(3);


        employeeRecords.add(emp1);
        employeeRecords.add(emp2);
        employeeRecords.add(emp3);
    }


    @GetMapping(value = "/Employees")
    public ResponseEntity<List<Employee>> getEmployeeDetails()
    {
        return ResponseEntity.of(Optional.of(this.employeeRecords));
    }

    @GetMapping(value = "/Employees/{id}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable("id") int id) {
       Optional<Employee> emp=  this.employeeRecords.stream().filter(employee -> Objects.equals(employee.getId(), id)).findFirst();
        return ResponseEntity.of(Optional.of(emp));
    }

    @PostMapping(path = "/addEmployee")
    public ResponseEntity<List<Employee>> addEmployees(@RequestBody Employee employee) {
        this.employeeRecords.add(employee);
    return ResponseEntity.of(Optional.of(this.employeeRecords));
    }

    @PostMapping(path = "/updateEmployee")
    public ResponseEntity<List<Employee>> updateEmployees(@RequestBody Employee employee) {
        List<Employee> addonItems = this.employeeRecords;

        for (Employee addOn : addonItems) {
            if (employee.getId()==addOn.getId()) {
                addOn.setEmpName(employee.getEmpName());
                addOn.setEmpSalary(employee.getEmpSalary());
                addOn.setEmpAge(employee.getEmpAge());
            }
        }
        return ResponseEntity.of(Optional.of(addonItems));
        }
}
