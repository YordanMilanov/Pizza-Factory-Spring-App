package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(unique = true)
    private String name;

//    @Column(precision = 19, scale = 2)
    @Column
    private double price;

    @Column
    private double caloriesPer100;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToOne
    private Picture picture;

    @Column
    private Integer grams;

    @Enumerated(EnumType.STRING)
    private ProductTypeEnum productTypeEnum;

    //the relation table between the 2 entities
    @ElementCollection(fetch = FetchType.EAGER)
    //naming of the table
    @CollectionTable(name = "required_ingredients")
    //naming of the key-column
    @MapKeyJoinColumn(name = "ingredient_id")
    //naming of the value-column
    @Column(name = "grams")
    private Map<Ingredient, Integer> requiredProducts;
}
