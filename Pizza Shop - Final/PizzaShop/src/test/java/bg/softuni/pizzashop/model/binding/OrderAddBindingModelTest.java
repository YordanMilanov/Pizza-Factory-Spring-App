package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderAddBindingModelTest {

    @Test
    public void testSetAndGetDescription() {
        // Arrange
        String description = "Test description";
        OrderAddBindingModel order = new OrderAddBindingModel();

        // Act
        order.setDescription(description);

        // Assert
       Assertions.assertEquals(description, order.getDescription());
    }
}
