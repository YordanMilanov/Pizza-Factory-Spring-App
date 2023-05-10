package bg.softuni.pizzashop.model.view;

import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductView {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private String pictureURL;

    private Integer grams;

    private double caloriesPer100;

    private ProductTypeEnum productTypeEnum;
}
