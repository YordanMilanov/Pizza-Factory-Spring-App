package bg.softuni.pizzashop.model.entity;


import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @Test
    public void testGettersAndSetters() {

        // Create a new Product instance
        Product product = new Product();

        // Set the values of the properties using the setters
        product.setId(1L);
        product.setName("Pizza Margherita");
        product.setPrice(10.99);
        product.setCaloriesPer100(200.0);
        product.setDescription("Freshly baked pizza with mozzarella and tomato sauce");
        product.setPicture(new Picture());
        product.setGrams(500);
        product.setProductTypeEnum(ProductTypeEnum.PIZZA);

        // Create a map of ingredients required for this product
        Map<Ingredient, Integer> requiredIngredients = new HashMap<>();
        requiredIngredients.put(new Ingredient(), 200);
        requiredIngredients.put(new Ingredient(), 100);
        product.setRequiredProducts(requiredIngredients);

        // Verify that the values of the properties can be retrieved using the getters
        Assertions.assertEquals(1L, product.getId());
        Assertions.assertEquals("Pizza Margherita", product.getName());
        Assertions.assertEquals(10.99, product.getPrice());
        Assertions.assertEquals(200.0, product.getCaloriesPer100());
        Assertions.assertEquals("Freshly baked pizza with mozzarella and tomato sauce", product.getDescription());
        Assertions.assertNotNull(product.getPicture());
        Assertions.assertEquals(500, product.getGrams());
        Assertions.assertEquals(ProductTypeEnum.PIZZA, product.getProductTypeEnum());
        Assertions.assertEquals(requiredIngredients, product.getRequiredProducts());
    }
}
