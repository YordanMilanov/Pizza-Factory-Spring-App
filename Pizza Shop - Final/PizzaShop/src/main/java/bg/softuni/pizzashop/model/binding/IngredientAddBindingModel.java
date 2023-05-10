package bg.softuni.pizzashop.model.binding;

import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientAddBindingModel {

    @Size(min = 1, max = 20)
    @NotNull(message = "Field Cannot be empty")
    private String name;

    @Positive(message = "Price must be greater than 0€")
    @NotNull(message = "Field Cannot be empty")
    private BigDecimal price;

    @Positive(message = "Stock must be greater than 0")
    @NotNull(message = "Field Cannot be empty")
    private BigDecimal stockInKg;

    @Min(value=0, message="must be equal or greater than 0")
    @NotNull(message = "Field Cannot be empty")
    private int carbohydrates;

    @Min(value=0, message="must be equal or greater than 18")
    @NotNull(message = "Field Cannot be empty")
    private int fat;

    @Min(value=0, message="must be equal or greater than 0")
    @NotNull(message = "Field Cannot be empty")
    private int protein;

    @NotNull(message = "Type must be selected")
    private IngredientTypeEnum ingredientType;
}
