package com.example.payroll2.Api;


import com.example.payroll2.Dto.ProfileCreate;
import com.example.payroll2.Services.ProfileService;
import com.example.payroll2.Entities.Profile;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profile")
@Tag(name = "Profile", description = "Create, update, delete, Read")

public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles(){
        var profiles = profileService.getAllProfiles();
        return  ResponseEntity.ok(profiles);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@Valid  @RequestBody ProfileCreate profileCreate){
        var profile = profileService.createNewProfile(profileCreate);
        return  ResponseEntity.ok(profile);
    }

}
