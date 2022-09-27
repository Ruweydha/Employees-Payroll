package com.example.payroll2.Services;


import com.example.payroll2.Entities.Employee;
import com.example.payroll2.Repositories.EmployeeRepository;
import com.example.payroll2.Repositories.PostsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository ;
    private final PostsRepository postsRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PostsRepository postsRepository){

        this.employeeRepository = employeeRepository;
        this.postsRepository = postsRepository;
    }
    //     Getting all employees
    public List<Employee> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    //    Getting specific Employeedha
    public Employee getEmployeeById(Long id){
        var employee = employeeRepository.findById(id).get();
        return employee;
    }

    //    Adding new employee
    public Employee createNewEmployee(Employee newEmployee){
        var employee = employeeRepository.save(newEmployee);
        return employee;
    }

    //        Deleting new employee

    public String  deleteEmployee(Long id){
       var posts = postsRepository.findAllByEmployeeId(id);
        postsRepository.deleteAll(posts);
        employeeRepository.deleteById(id);
        return "Employee Deleted successfully";
    }

    //    Updating an employee
    public Employee updateEmployee(Long id, Employee employee){
        var employeeToUpdate = employeeRepository.findById(id).get();
        employeeToUpdate.setName((employee.getName()));
        employeeToUpdate.setRole(employee.getRole());
        employeeRepository.save(employeeToUpdate);
        return employeeToUpdate;
    }
}
