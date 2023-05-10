package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class UserTest {

    @Test
    public void testUserSettersAndGetters() {
        String username = "testuser";
        String fullName = "Test User";
        String password = "password123";
        String email = "testuser@example.com";
        String phone = "1234567890";
        UserLevelEnum level = UserLevelEnum.EMPLOYEE;
        Set<Role> roles = new HashSet<>();
        Address address = new Address();
        address.setCity("City");
        address.setNeighborhood("Neighborhood");
        address.setStreet("Street");
        address.setStreetNumber(1);
        User user = new User();
        user.setUsername(username);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLevel(level);
        user.setRoles(roles);
        user.setAddress(address);
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(fullName, user.getFullName());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(phone, user.getPhone());
        Assertions.assertEquals(level, user.getLevel());
        Assertions.assertEquals(roles, user.getRoles());
        Assertions.assertEquals(address, user.getAddress());
    }

}
