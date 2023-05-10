package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class OrderServiceModel {

    private Long id;

    private OrderStatusEnum orderStatus;

    private String description;

    private BigDecimal price;

    private LocalDateTime orderTime;

    private List<Product> products;

    private Map<String, Integer> productNameQuantity;

    private User user;
}
