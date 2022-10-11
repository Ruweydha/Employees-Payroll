package com.example.payroll2.Api;


import com.example.payroll2.Dto.RolesAssignmentRequest;
import com.example.payroll2.Entities.Roles;
import com.example.payroll2.Services.RolesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "Roles", description = "Create, update, delete, Read")

public class RolesController {

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    private ResponseEntity<Roles> addRole(@RequestBody RolesAssignmentRequest rolesAssignmentRequest){
        var role = rolesService.addRole(rolesAssignmentRequest);
        return ResponseEntity.ok(role);
    }
    @GetMapping
    private ResponseEntity<List<Roles>> getRoles(){
        List<Roles> roles = rolesService.getRoles();
        return ResponseEntity.ok(roles);

    }

}
