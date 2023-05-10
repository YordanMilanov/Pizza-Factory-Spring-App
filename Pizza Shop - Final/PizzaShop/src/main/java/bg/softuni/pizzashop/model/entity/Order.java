package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @Column(columnDefinition = "TEXT")
    private String description;

//    @Column(precision = 19, scale = 2)
    @Column
    private double price;

    @Column
    private LocalDateTime orderTime;

    //the relation table between the 2 entities
    @ElementCollection(fetch = FetchType.EAGER)
    //naming of the table
    @CollectionTable(name = "order_product_quantity")
    //naming of the key-column
    @MapKeyJoinColumn(name = "product")
    //naming of the value-column
    @Column(name = "quantity")
    private Map<Product, Integer> productQuantity;

    @ManyToOne
    private User user;
}
