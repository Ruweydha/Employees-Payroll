package com.example.payroll2.Services;

import com.example.payroll2.Dto.ProfileCreate;
import com.example.payroll2.Entities.Employee;
import com.example.payroll2.Entities.Profile;
import com.example.payroll2.Repositories.EmployeeRepository;
import com.example.payroll2.Repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final EmployeeRepository employeeRepository;

    public ProfileService(ProfileRepository profileRepository, EmployeeRepository employeeRepository){
        this.profileRepository = profileRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Profile> getAllProfiles(){
        var employees = profileRepository.findAll();
        return employees;
    }

    public Profile getProfileById(Long id){
        var profile = profileRepository.findById(id).get();
        return profile;
    }

    public Profile createNewProfile(ProfileCreate profileCreate){
        Employee employee = employeeRepository.getReferenceById(profileCreate.getEmployeeId());
        Profile profile = new Profile();
        profile.setBio(profileCreate.getBio());
        profile.setUsername(profileCreate.getUsername());
        profile.setEmployee(employee);
        profileRepository.saveAndFlush(profile);
        return profile;
    }
    public String  deleteProfile(Long id){
        profileRepository.deleteById(id);
        return "Employee Deleted successfully";
    }


}
