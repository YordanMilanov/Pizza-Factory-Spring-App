package bg.softuni.pizzashop.model.binding;

import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductAddBindingModelTest {

    @Test
    public void testSetNameAndGetPrice() {
        // Arrange
        String name = "Test product";
        BigDecimal price = new BigDecimal("10.00");
        ProductAddBindingModel product = new ProductAddBindingModel();

        // Act
        product.setName(name);
        product.setPrice(price);

        // Assert
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void testSetAndGetDescription() {
        // Arrange
        String description = "Test description";
        ProductAddBindingModel product = new ProductAddBindingModel();

        // Act
        product.setDescription(description);

        // Assert
        assertEquals(description, product.getDescription());
    }

    @Test
    public void testSetAndGetProductTypeEnum() {
        // Arrange
        ProductTypeEnum type = ProductTypeEnum.PIZZA;
        ProductAddBindingModel product = new ProductAddBindingModel();

        // Act
        product.setProductTypeEnum(type);

        // Assert
        assertEquals(type, product.getProductTypeEnum());
    }

    @Test
    public void testSetAndGetPicture() {
        // Arrange
        MockMultipartFile file = new MockMultipartFile("picture", "filename.jpg", "image/jpeg", new byte[10]);
        ProductAddBindingModel product = new ProductAddBindingModel();

        // Act
        product.setPicture(file);

        // Assert
        assertEquals(file, product.getPicture());
    }
}
