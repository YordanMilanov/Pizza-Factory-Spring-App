package bg.softuni.pizzashop.model.view;

import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderViewTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        OrderView orderView = new OrderView();
        Long id = 1L;
        OrderStatusEnum orderStatus = OrderStatusEnum.IN_PROCESS;
        String description = "Test order";
        double price = 10.0;
        LocalDateTime orderTime = LocalDateTime.now();
        Map<Product, Integer> productQuantity = new HashMap<>();
        User user = new User();
        orderView.setId(id);
        orderView.setOrderStatus(orderStatus);
        orderView.setDescription(description);
        orderView.setPrice(price);
        orderView.setOrderTime(orderTime);
        orderView.setProductQuantity(productQuantity);
        orderView.setUser(user);

        // Assert
        assertEquals(id, orderView.getId());
        assertEquals(orderStatus, orderView.getOrderStatus());
        assertEquals(description, orderView.getDescription());
        assertEquals(price, orderView.getPrice());
        assertEquals(orderTime, orderView.getOrderTime());
        assertEquals(productQuantity, orderView.getProductQuantity());
        assertEquals(user, orderView.getUser());
    }
}
