package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceModelTest {

    private IngredientServiceModel ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new IngredientServiceModel();
        ingredient.setId(1L);
        ingredient.setName("Ingredient");
        ingredient.setPrice(BigDecimal.TEN);
        ingredient.setStockInKg(new BigDecimal("5.0"));
        ingredient.setCarbohydrates(10);
        ingredient.setFat(5);
        ingredient.setProtein(20);
        ingredient.setCalories(125); // (10 * 4) + (5 * 9) + (20 * 4) = 125
        ingredient.setIngredientType(IngredientTypeEnum.TOPPING);
    }

    @Test
    public void testId() {
        Assertions.assertEquals(1L, ingredient.getId());
    }

    @Test
    public void testName() {
        Assertions.assertEquals("Ingredient", ingredient.getName());
    }

    @Test
    public void testPrice() {
        Assertions.assertEquals(BigDecimal.TEN, ingredient.getPrice());
    }

    @Test
    public void testStockInKg() {
        Assertions.assertEquals(new BigDecimal("5.0"), ingredient.getStockInKg());
    }

    @Test
    public void testCarbohydrates() {
        Assertions.assertEquals(10, ingredient.getCarbohydrates());
    }

    @Test
    public void testFat() {
        Assertions.assertEquals(5, ingredient.getFat());
    }

    @Test
    public void testProtein() {
        Assertions.assertEquals(20, ingredient.getProtein());
    }

    @Test
    public void testCalories() {
        Assertions.assertEquals(125, ingredient.getCalories());
    }

    @Test
    public void testIngredientType() {
        Assertions.assertEquals(IngredientTypeEnum.TOPPING, ingredient.getIngredientType());
    }

}
