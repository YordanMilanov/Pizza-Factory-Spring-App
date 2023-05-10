package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class UserNameUpdateBindingModelTest {

    private UserNameUpdateBindingModel userNameUpdateBindingModel;

    @BeforeEach
    public void setUp() {
        userNameUpdateBindingModel = new UserNameUpdateBindingModel();
    }

    @Test
    public void testSetAndGetUsername() {
        String username = "newusername";
        userNameUpdateBindingModel.setUsername(username);
        assertEquals(username, userNameUpdateBindingModel.getUsername());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(userNameUpdateBindingModel);
    }
}