package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleTest {


    @Test
    public void testRoleSettersAndGetters() {
        RoleNameEnum roleName = RoleNameEnum.CUSTOMER;
        String description = "Regular user role";
        Role role = new Role();
        role.setRole(roleName);
        role.setDescription(description);
        Assertions.assertEquals(roleName, role.getRole());
        Assertions.assertEquals(description, role.getDescription());
    }
}
