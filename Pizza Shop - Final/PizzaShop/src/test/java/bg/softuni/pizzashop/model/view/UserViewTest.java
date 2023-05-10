package bg.softuni.pizzashop.model.view;

import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserViewTest {

    @Test
    public void testSetAndGetId() {
        UserView userView = new UserView();
        Long id = 123L;
        userView.setId(id);
        assertEquals(id, userView.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        UserView userView = new UserView();
        String username = "johndoe";
        userView.setUsername(username);
        assertEquals(username, userView.getUsername());
    }

    @Test
    public void testSetAndGetFullName() {
        UserView userView = new UserView();
        String fullName = "John Doe";
        userView.setFullName(fullName);
        assertEquals(fullName, userView.getFullName());
    }

    @Test
    public void testSetAndGetPassword() {
        UserView userView = new UserView();
        String password = "password";
        userView.setPassword(password);
        assertEquals(password, userView.getPassword());
    }

    @Test
    public void testSetAndGetEmail() {
        UserView userView = new UserView();
        String email = "johndoe@example.com";
        userView.setEmail(email);
        assertEquals(email, userView.getEmail());
    }

    @Test
    public void testSetAndGetPhone() {
        UserView userView = new UserView();
        String phone = "123-456-7890";
        userView.setPhone(phone);
        assertEquals(phone, userView.getPhone());
    }

    @Test
    public void testSetAndGetNewRole() {
        UserView userView = new UserView();
        Role newRole = new Role();
        userView.setNewRole(newRole);
        assertEquals(newRole, userView.getNewRole());
    }

    @Test
    public void testSetAndGetLevel() {
        UserView userView = new UserView();
        UserLevelEnum level = UserLevelEnum.REGULAR;
        userView.setLevel(level);
        assertEquals(level, userView.getLevel());
    }

    @Test
    public void testSetAndGetRoles() {
        UserView userView = new UserView();
        Set<Role> roles = new HashSet<>();
        roles.add(new Role());
        userView.setRoles(roles);
        assertEquals(roles, userView.getRoles());
    }

    @Test
    public void testSetAndGetAddress() {
        UserView userView = new UserView();
        Address address = new Address();
        userView.setAddress(address);
        assertEquals(address, userView.getAddress());
    }
}
