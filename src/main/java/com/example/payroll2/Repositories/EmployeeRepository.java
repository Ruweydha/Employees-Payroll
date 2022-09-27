package com.example.payroll2.Repositories;

import com.example.payroll2.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EmployeeRepository  extends JpaRepository<Employee, Long> {
}
