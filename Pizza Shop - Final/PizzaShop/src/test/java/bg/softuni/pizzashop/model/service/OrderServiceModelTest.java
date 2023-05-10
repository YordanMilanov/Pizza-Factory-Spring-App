package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceModelTest {

    @Test
    public void testIdGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        Long id = 1L;

        // Act
        order.setId(id);

        // Assert
        assertEquals(id, order.getId());
    }

    @Test
    public void testOrderStatusGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        OrderStatusEnum status = OrderStatusEnum.IN_PROCESS;

        // Act
        order.setOrderStatus(status);

        // Assert
        assertEquals(status, order.getOrderStatus());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        String description = "Test order";

        // Act
        order.setDescription(description);

        // Assert
        assertEquals(description, order.getDescription());
    }

    @Test
    public void testPriceGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        BigDecimal price = new BigDecimal("10.00");

        // Act
        order.setPrice(price);

        // Assert
        assertEquals(price, order.getPrice());
    }

    @Test
    public void testOrderTimeGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        LocalDateTime time = LocalDateTime.now();

        // Act
        order.setOrderTime(time);

        // Assert
        assertEquals(time, order.getOrderTime());
    }

    @Test
    public void testProductsGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        products.add(product1);
        products.add(product2);

        // Act
        order.setProducts(products);

        // Assert
        assertEquals(products, order.getProducts());
    }

    @Test
    public void testProductNameQuantityGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        Map<String, Integer> productNameQuantity = new HashMap<>();
        productNameQuantity.put("Product 1", 2);
        productNameQuantity.put("Product 2", 1);

        // Act
        order.setProductNameQuantity(productNameQuantity);

        // Assert
        assertEquals(productNameQuantity, order.getProductNameQuantity());
    }

    @Test
    public void testUserGetterAndSetter() {
        // Arrange
        OrderServiceModel order = new OrderServiceModel();
        User user = new User();

        // Act
        order.setUser(user);

        // Assert
        assertEquals(user, order.getUser());
    }
}
