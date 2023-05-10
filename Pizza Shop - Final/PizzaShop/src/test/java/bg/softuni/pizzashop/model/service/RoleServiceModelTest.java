package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleServiceModelTest {

    @Test
    public void testGetId() {
        RoleServiceModel role = new RoleServiceModel();
        role.setId(1L);
        Assertions.assertEquals(1L, role.getId());
    }

    @Test
    public void testGetRole() {
        RoleServiceModel role = new RoleServiceModel();
        role.setRole(RoleNameEnum.MANAGER);
        Assertions.assertEquals(RoleNameEnum.MANAGER, role.getRole());
    }

    @Test
    public void testGetDescription() {
        RoleServiceModel role = new RoleServiceModel();
        role.setDescription("Administrator role");
        Assertions.assertEquals("Administrator role", role.getDescription());
    }

}