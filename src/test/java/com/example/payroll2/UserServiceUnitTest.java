package com.example.payroll2;

import com.example.payroll2.Dto.AppUserCreateRequest;
import com.example.payroll2.Entities.AppUser;
import com.example.payroll2.Repositories.AppUserRepository;
import com.example.payroll2.Repositories.RolesRepository;
import com.example.payroll2.Services.AppUserCreationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

//import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {
    @InjectMocks
    private AppUserCreationService appUserCreationService;

    @Mock
    private AppUserRepository appUserRepositoryMock;

    @Mock
    private RolesRepository rolesRepositoryMock;
    @Captor
    ArgumentCaptor<AppUser> userArgumentCaptor;

    @Test
    public void testGetAllUsers(){
        when(appUserRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        var result = appUserCreationService.getAllUsers();
        verify(appUserRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testGetUserById(){
        var user = new AppUser();
        user.setUsername("Rue");
        user.setPassword("test1");
        user.setFirstName("Ruweydha");
        user.setLastName("Abdinoor");

        when(appUserRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(user)).thenReturn(Optional.of(new AppUser()));
        var result = appUserCreationService.getUserById(1L);
        verify(appUserRepositoryMock).findById(anyLong());
        Assertions.assertEquals("Abdinoor",result.getLastName());
    }

    @Test
    public void testAddUser(){
        AppUserCreateRequest appUserCreateRequest = new AppUserCreateRequest();
        appUserCreateRequest.setUsername("Test");
        appUserCreateRequest.setPassword("test1");
        appUserCreateRequest.setFirstName("Ruweydha");
        appUserCreateRequest.setLastName("Abdinoor");

        when(appUserRepositoryMock.save(any(AppUser.class))).thenReturn(any(AppUser.class));
        appUserCreationService.createUser(appUserCreateRequest);
        verify(appUserRepositoryMock).save(userArgumentCaptor.capture());
        var savedUser = userArgumentCaptor.getValue();
        Assertions.assertEquals(savedUser.getUsername(), appUserCreateRequest.getUsername());
    }

}
