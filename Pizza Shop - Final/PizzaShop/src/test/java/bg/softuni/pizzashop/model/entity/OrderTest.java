package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class OrderTest {

    @Test
    public void testGettersAndSetters() {
        Order order = new Order();

        order.setId(1L);
        Assertions.assertEquals(1L, order.getId());

        order.setOrderStatus(OrderStatusEnum.IN_PROCESS);
        Assertions.assertEquals(OrderStatusEnum.IN_PROCESS, order.getOrderStatus());

        order.setDescription("Test order");
        Assertions.assertEquals("Test order", order.getDescription());

        order.setPrice(20.0);
        Assertions.assertEquals(20.0, order.getPrice());

        LocalDateTime time = LocalDateTime.now();
        order.setOrderTime(time);
        Assertions.assertEquals(time, order.getOrderTime());

        User user = new User();
        user.setId(1L);
        order.setUser(user);
        Assertions.assertEquals(user, order.getUser());

        Map<Product, Integer> productQuantity = new HashMap<>();
        Product product = new Product();
        product.setId(1L);
        productQuantity.put(product, 2);
        order.setProductQuantity(productQuantity);
        Assertions.assertEquals(productQuantity, order.getProductQuantity());
    }
}
