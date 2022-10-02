package com.example.payroll2.Services;

import com.example.payroll2.Dto.RolesAssignmentRequest;
import com.example.payroll2.Entities.Roles;
import com.example.payroll2.Repositories.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles addRole(RolesAssignmentRequest rolesAssignmentRequest){
        var role = new Roles();
        role.setName(rolesAssignmentRequest.getName());
        rolesRepository.save(role);
        return role;
    }

    public List<Roles> getRoles(){
        var roles = rolesRepository.findAll();
        return roles;
    }

}
