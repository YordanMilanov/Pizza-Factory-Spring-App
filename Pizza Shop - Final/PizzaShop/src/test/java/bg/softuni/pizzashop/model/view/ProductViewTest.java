package bg.softuni.pizzashop.model.view;

import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductViewTest {

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String name = "Test Product";
        BigDecimal price = new BigDecimal("10.00");
        String description = "This is a test product";
        String pictureURL = "https://example.com/image.jpg";
        Integer grams = 100;
        double caloriesPer100 = 100.0;
        ProductTypeEnum productTypeEnum = ProductTypeEnum.PIZZA;

        ProductView productView = new ProductView();
        productView.setId(id);
        productView.setName(name);
        productView.setPrice(price);
        productView.setDescription(description);
        productView.setPictureURL(pictureURL);
        productView.setGrams(grams);
        productView.setCaloriesPer100(caloriesPer100);
        productView.setProductTypeEnum(productTypeEnum);

        assertEquals(id, productView.getId());
        assertEquals(name, productView.getName());
        assertEquals(price, productView.getPrice());
        assertEquals(description, productView.getDescription());
        assertEquals(pictureURL, productView.getPictureURL());
        assertEquals(grams, productView.getGrams());
        assertEquals(caloriesPer100, productView.getCaloriesPer100(), 0.001);
        assertEquals(productTypeEnum, productView.getProductTypeEnum());
    }
}
