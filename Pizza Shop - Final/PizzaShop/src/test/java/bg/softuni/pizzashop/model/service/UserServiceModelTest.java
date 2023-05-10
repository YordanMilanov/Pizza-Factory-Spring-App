package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserServiceModelTest {

    @Test
    public void testGettersAndSetters() {
        UserServiceModel userModel = new UserServiceModel();
        userModel.setId(1L);
        userModel.setUsername("testuser");
        userModel.setFullName("Test User");
        userModel.setPassword("testpassword");
        userModel.setEmail("testuser@example.com");
        userModel.setLevel(UserLevelEnum.REGULAR);

        Role role = new Role();
        Role role1 = new Role();
        userModel.setRoles(Set.of(role, role1));
        Address address = new Address();
        address.setCity("Test City");
        address.setNeighborhood("Test Neighborhood");
        address.setStreet("Test Street");
        address.setStreetNumber(123);
        userModel.setAddress(address);

        Assertions.assertEquals(1L, userModel.getId());
        Assertions.assertEquals("testuser", userModel.getUsername());
        Assertions.assertEquals("Test User", userModel.getFullName());
        Assertions.assertEquals("testpassword", userModel.getPassword());
        Assertions.assertEquals("testuser@example.com", userModel.getEmail());
        Assertions.assertEquals(UserLevelEnum.REGULAR, userModel.getLevel());
        Assertions.assertEquals(2, userModel.getRoles().size());
        Assertions.assertEquals(address, userModel.getAddress());
    }
}
