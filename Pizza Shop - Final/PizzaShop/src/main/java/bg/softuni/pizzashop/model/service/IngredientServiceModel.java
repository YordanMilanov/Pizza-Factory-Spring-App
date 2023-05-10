package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientServiceModel {

    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal stockInKg;

    private int carbohydrates;

    private int fat;

    private int protein;

    private int calories; // = (carbohydrates * 4) + (fat * 9) + (protein * 4);

    private IngredientTypeEnum ingredientType;
}
