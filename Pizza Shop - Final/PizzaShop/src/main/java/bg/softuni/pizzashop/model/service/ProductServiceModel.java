package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.Picture;
import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Picture picture;
    private Integer grams;
    private double caloriesPer100;
    private ProductTypeEnum productTypeEnum;
    private Map<Ingredient, Integer> requiredProducts;
}
