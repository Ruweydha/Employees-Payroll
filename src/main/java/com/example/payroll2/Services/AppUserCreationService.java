package com.example.payroll2.Services;

import com.example.payroll2.Dto.AppUserCreateRequest;
import com.example.payroll2.Dto.RolesAssignmentRequest;
import com.example.payroll2.Dto.UserRolesAssignment;
import com.example.payroll2.Entities.AppUser;
import com.example.payroll2.Repositories.AppUserRepository;
import com.example.payroll2.Repositories.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserCreationService {
    private final AppUserRepository appUserRepository;
    private final RolesRepository rolesRepository;


    public AppUserCreationService(AppUserRepository appUserRepository, RolesRepository rolesRepository) {
        this.appUserRepository = appUserRepository;
        this.rolesRepository = rolesRepository;
    }

    public AppUser createUser(AppUserCreateRequest appUserCreateRequest){
        AppUser user = new AppUser();
        user.setFirstName(appUserCreateRequest.getFirstName());
        user.setLastName(appUserCreateRequest.getLastName());
        user.setUsername(appUserCreateRequest.getUsername());
        user.setPassword(appUserCreateRequest.getPassword());
        appUserRepository.save(user);
        return user;
    }

    public Optional< AppUser> addRole(UserRolesAssignment newRole){
        var role = rolesRepository.findByName(newRole.getRole());
        var user = appUserRepository.findById(newRole.getUserId());
        user.ifPresent(u->{
            u.addRoleToUSer(role);
            appUserRepository.save(u);
        });
        return user;
    }

}
