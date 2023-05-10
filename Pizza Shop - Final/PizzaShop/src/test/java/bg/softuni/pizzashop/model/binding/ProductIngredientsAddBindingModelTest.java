package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductIngredientsAddBindingModelTest {

    private ProductIngredientsAddBindingModel productIngredientsAddBindingModel;

    @BeforeEach
    public void setUp() {
        productIngredientsAddBindingModel = new ProductIngredientsAddBindingModel();
    }

    @Test
    public void testSetAndGetIngredientName() {
        String ingredientName = "salt";
        productIngredientsAddBindingModel.setIngredientName(ingredientName);
        assertEquals(ingredientName, productIngredientsAddBindingModel.getIngredientName());
    }

    @Test
    public void testSetAndGetIngredientGram() {
        Integer ingredientGram = 100;
        productIngredientsAddBindingModel.setIngredientGram(ingredientGram);
        assertEquals(ingredientGram, productIngredientsAddBindingModel.getIngredientGram());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(productIngredientsAddBindingModel);
    }
}
