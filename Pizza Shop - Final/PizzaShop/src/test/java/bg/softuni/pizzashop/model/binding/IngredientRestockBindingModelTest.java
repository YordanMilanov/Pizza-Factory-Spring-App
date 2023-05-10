package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class IngredientRestockBindingModelTest {

    private IngredientRestockBindingModel ingredientRestockBindingModel;

    @BeforeEach
    public void setUp() {
        ingredientRestockBindingModel = new IngredientRestockBindingModel();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        ingredientRestockBindingModel.setId(id);
        assertEquals(id, ingredientRestockBindingModel.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "New Ingredient";
        ingredientRestockBindingModel.setName(name);
        assertEquals(name, ingredientRestockBindingModel.getName());
    }

    @Test
    public void testSetAndGetPrice() {
        BigDecimal price = new BigDecimal("10.50");
        ingredientRestockBindingModel.setPrice(price);
        assertEquals(price, ingredientRestockBindingModel.getPrice());
    }

    @Test
    public void testSetAndGetStockInKg() {
        BigDecimal stockInKg = new BigDecimal("25.00");
        ingredientRestockBindingModel.setStockInKg(stockInKg);
        assertEquals(stockInKg, ingredientRestockBindingModel.getStockInKg());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(ingredientRestockBindingModel);
    }
}

