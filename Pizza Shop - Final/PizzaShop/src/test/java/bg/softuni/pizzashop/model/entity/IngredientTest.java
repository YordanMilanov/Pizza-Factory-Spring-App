package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IngredientTest {

    @Test
    public void testIngredientProperties() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("MAIN");
        ingredient.setPrice(2.0);
        ingredient.setStockInKg(10.0);
        ingredient.setCarbohydrates(30);
        ingredient.setFat(5);
        ingredient.setProtein(5);
        ingredient.setCalories((ingredient.getFat() * 9) + (ingredient.getProtein() * 5) + (ingredient.getCarbohydrates() * 5));
        ingredient.setIngredientType(IngredientTypeEnum.MAIN);

        assertEquals("MAIN", ingredient.getName());
        assertEquals(2.0, ingredient.getPrice());
        assertEquals(10.0, ingredient.getStockInKg());
        assertEquals(30, ingredient.getCarbohydrates());
        assertEquals(5, ingredient.getFat());
        assertEquals(5, ingredient.getProtein());
        assertEquals(220, ingredient.getCalories()); // (30 * 4) + (5 * 9) + (5 * 4)
        assertEquals(IngredientTypeEnum.MAIN, ingredient.getIngredientType());
    }
}
