package com.example.payroll2;

import com.example.payroll2.Api.AppUserController;
import com.example.payroll2.Services.AppUserCreationService;
import com.example.payroll2.Services.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {AppUserController.class})
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    AppUserService appUserService;
    @MockBean
    AppUserCreationService appUserCreationService;
//
//    @Test
//    @WithMockUser
}
