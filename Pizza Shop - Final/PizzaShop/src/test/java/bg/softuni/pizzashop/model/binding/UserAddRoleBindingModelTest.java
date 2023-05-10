package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserAddRoleBindingModelTest {

    private UserAddRoleBindingModel userAddRoleBindingModel;

    @BeforeEach
    public void setUp() {
        userAddRoleBindingModel = new UserAddRoleBindingModel();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 123L;
        userAddRoleBindingModel.setId(id);
        assertEquals(id, userAddRoleBindingModel.getId());
    }

    @Test
    public void testSetAndGetRoleName() {
        String roleName = "ADMIN";
        userAddRoleBindingModel.setRoleName(roleName);
        assertEquals(roleName, userAddRoleBindingModel.getRoleName());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(userAddRoleBindingModel);
    }
}
