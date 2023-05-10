package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.AuthService;
import bg.softuni.pizzashop.service.ApplicationUserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailServiceTest {

    private ApplicationUserDetailService toTest;

    private final String EXISTING_USERNAME = "manager";
    private final String EXISTING_PASSWORD = "manager";
    private final String NON_EXISTING_USERNAME = "non-username";

    //has all the methods of the userRepository but it returns empty methods
    @Mock
    private AuthService mockAuthService;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailService(
        mockAuthService);
    };

    @Test
    void testUserFound() {

        //here we create imitating roles
        Role testRoleManager = new Role();
        testRoleManager.setRole(RoleNameEnum.MANAGER);
        Role testRoleStaff = new Role();
        testRoleStaff.setRole(RoleNameEnum.STAFF);
        Role testRoleCustomer = new Role();
        testRoleCustomer.setRole(RoleNameEnum.CUSTOMER);

        //here we create imitating user and adding him the imitating roles
        User testUserEntity = new User();
        testUserEntity.setUsername(EXISTING_USERNAME);
        testUserEntity.setPassword(EXISTING_PASSWORD);
        testUserEntity.setRoles(Set.of(testRoleManager, testRoleStaff));

        //here we make the mockAuthService to return our imitating testUserEntity
        //when its getUserByUsername method is called with the name of our imitating testUserEntity
        when(mockAuthService.getUserByUsername(EXISTING_USERNAME))
                .thenReturn(testUserEntity);

        //here we imitate the calling of the method loadUserByUsername which is the target of our tests
        //we pass the EXISTING_USERNAME
        //and we expect it to return us the imitating user which
        UserDetails managerDetails = toTest.loadUserByUsername(EXISTING_USERNAME);
        Assertions.assertNotNull(managerDetails);
        Assertions.assertEquals(EXISTING_USERNAME, managerDetails.getUsername());
        Assertions.assertEquals(EXISTING_PASSWORD, managerDetails.getPassword());
        Assertions.assertEquals(2, managerDetails.getAuthorities().size());
    }

    @Test
    void testUserNotFound() {
        assertThrows(NullPointerException.class,
                () -> {
                    toTest.loadUserByUsername(NON_EXISTING_USERNAME);
                });
    }
}

