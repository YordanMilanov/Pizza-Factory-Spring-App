package bg.softuni.pizzashop.model.binding;

import javax.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class ProductIngredientsAddBindingModel {

    @NotNull(message = "Please Select Ingredient")
    @NotBlank(message = "Please Select Ingredient")
    private String ingredientName;

    @Positive(message = "ingredient grams must be positive")
    private Integer ingredientGram;

}
