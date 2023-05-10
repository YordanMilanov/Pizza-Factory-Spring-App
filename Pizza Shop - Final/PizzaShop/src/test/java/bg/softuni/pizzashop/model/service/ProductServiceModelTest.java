package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.Picture;
import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ProductServiceModelTest {

    @Test
    public void testSetAndGetId() {
        ProductServiceModel product = new ProductServiceModel();
        product.setId(1L);
        Assertions.assertEquals(1L, product.getId());
    }

    @Test
    public void testSetAndGetName() {
        ProductServiceModel product = new ProductServiceModel();
        product.setName("Test Product");
        Assertions.assertEquals("Test Product", product.getName());
    }

    @Test
    public void testSetAndGetPrice() {
        ProductServiceModel product = new ProductServiceModel();
        product.setPrice(BigDecimal.valueOf(9.99));
        Assertions.assertEquals(BigDecimal.valueOf(9.99), product.getPrice());
    }

    @Test
    public void testSetAndGetDescription() {
        ProductServiceModel product = new ProductServiceModel();
        product.setDescription("This is a test product");
        Assertions.assertEquals("This is a test product", product.getDescription());
    }

    @Test
    public void testSetAndGetPicture() {
        ProductServiceModel product = new ProductServiceModel();
        Picture picture = new Picture();
        picture.setURL("https://example.com/picture.jpg");
        product.setPicture(picture);
        Assertions.assertEquals(picture, product.getPicture());
    }

    @Test
    public void testSetAndGetGrams() {
        ProductServiceModel product = new ProductServiceModel();
        product.setGrams(100);
        Assertions.assertEquals(100, product.getGrams());
    }

    @Test
    public void testSetAndGetCaloriesPer100() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCaloriesPer100(250.0);
        Assertions.assertEquals(250.0, product.getCaloriesPer100());
    }

    @Test
    public void testSetAndGetProductTypeEnum() {
        ProductServiceModel product = new ProductServiceModel();
        product.setProductTypeEnum(ProductTypeEnum.DRINK);
        Assertions.assertEquals(ProductTypeEnum.DRINK, product.getProductTypeEnum());
    }

    @Test
    public void testSetAndGetRequiredProducts() {
        ProductServiceModel product = new ProductServiceModel();
        Map<Ingredient, Integer> requiredProducts = new HashMap<>();
        requiredProducts.put(new Ingredient(), 10);
        product.setRequiredProducts(requiredProducts);
        Assertions.assertEquals(requiredProducts, product.getRequiredProducts());
    }
}

