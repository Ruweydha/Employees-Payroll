package com.example.payroll2;

import com.example.payroll2.Entities.AppUser;
import com.example.payroll2.Repositories.AppUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;
    @ParameterizedTest
    @CsvSource(textBlock = """
            test,test,Rue,Abdinoor,
            test2,test2,Ali,Noor
            """)
    public void testSaveUser(String username, String password, String firstname, String lastname){
        AppUser user= new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        appUserRepository.save(user);
        Assertions.assertEquals(1,user.getId());

    }

}
