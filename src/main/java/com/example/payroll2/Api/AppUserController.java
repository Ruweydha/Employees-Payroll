package com.example.payroll2.Api;

import com.example.payroll2.Dto.AppUserCreateRequest;
import com.example.payroll2.Dto.RolesAssignmentRequest;
import com.example.payroll2.Dto.UserRolesAssignment;
import com.example.payroll2.Entities.AppUser;
import com.example.payroll2.Services.AppUserCreationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Create, update, read, delete")
public class AppUserController {
    private final AppUserCreationService appUserCreationService;

    public AppUserController(AppUserCreationService appUserCreationService) {
        this.appUserCreationService = appUserCreationService;
    }

    @PostMapping
    public AppUser createUser(@RequestBody @Valid AppUserCreateRequest appUserCreateRequest){
        var user = appUserCreationService.createUser(appUserCreateRequest);
        return user;
    }

    @PutMapping("/add-role")
    public ResponseEntity<AppUser> addRoleToUser(@RequestBody UserRolesAssignment newUser){
        var user = appUserCreationService.addRole(newUser);
        return ResponseEntity.of(user);
    }
}
