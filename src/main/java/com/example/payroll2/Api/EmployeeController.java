package com.example.payroll2.Api;

import com.example.payroll2.Entities.Employee;
import com.example.payroll2.Services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    Getting all workers
    @GetMapping
    public ResponseEntity<List<Employee>>all(){
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees) ;
    }

    //    Getting a specific Employee
    @GetMapping("/{id}")
    public ResponseEntity<Employee>specific(@PathVariable Long id){
        var employee = employeeService.getEmployeeById(id);
        return  ResponseEntity.ok(employee);
    }

    // Adding a new Employee
    @PostMapping
    public ResponseEntity<Employee>  newEmployee(@RequestBody Employee newEmployee){
        var employee = employeeService.createNewEmployee(newEmployee);
        return ResponseEntity.ok(employee);
    }

    //    Deleting an Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        var employeeDeleted = employeeService.deleteEmployee(id);
        return employeeDeleted;
    }

    //    Put Method
    @PutMapping("/{id}")
   public Employee replaceEmployee(@RequestBody Employee updateEmployee, @PathVariable Long id){
        var employee = employeeService.updateEmployee(id, updateEmployee);
        return employee;
    }

}
