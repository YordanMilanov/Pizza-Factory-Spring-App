package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredient extends BaseEntity{

    @Column(unique = true)
    private String name;

//    @Column(precision = 19, scale = 2)
    @Column
    private double price;

//    @Column(precision = 19, scale = 3)
    @Column
    @PositiveOrZero
    private double stockInKg;

    @Column
    private int carbohydrates;

    @Column
    private int fat;

    @Column
    private int protein;

    @Column
    private int calories; // = (carbohydrates * 4) + (fat * 9) + (protein * 4);

    @Enumerated(EnumType.STRING)
    private IngredientTypeEnum ingredientType;
}
