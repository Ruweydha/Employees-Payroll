package com.example.payroll2.Repositories;

import com.example.payroll2.Entities.AppUser;
import com.example.payroll2.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);

}
