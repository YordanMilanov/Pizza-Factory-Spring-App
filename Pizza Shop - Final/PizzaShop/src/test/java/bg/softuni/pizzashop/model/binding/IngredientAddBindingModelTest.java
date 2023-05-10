package bg.softuni.pizzashop.model.binding;

import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class IngredientAddBindingModelTest {

    @Test
    public void testIngredientAddBindingModelSettersAndGetters() {
        String name = "Test Ingredient";
        BigDecimal price = BigDecimal.valueOf(2.50);
        BigDecimal stockInKg = BigDecimal.valueOf(10);
        int carbohydrates = 10;
        int fat = 5;
        int protein = 3;
        IngredientTypeEnum ingredientType = IngredientTypeEnum.TOPPING;
        IngredientAddBindingModel model = new IngredientAddBindingModel();
        model.setName(name);
        model.setPrice(price);
        model.setStockInKg(stockInKg);
        model.setCarbohydrates(carbohydrates);
        model.setFat(fat);
        model.setProtein(protein);
        model.setIngredientType(ingredientType);
        Assertions.assertEquals(name, model.getName());
        Assertions.assertEquals(price, model.getPrice());
        Assertions.assertEquals(stockInKg, model.getStockInKg());
        Assertions.assertEquals(carbohydrates, model.getCarbohydrates());
        Assertions.assertEquals(fat, model.getFat());
        Assertions.assertEquals(protein, model.getProtein());
        Assertions.assertEquals(ingredientType, model.getIngredientType());
    }
}
