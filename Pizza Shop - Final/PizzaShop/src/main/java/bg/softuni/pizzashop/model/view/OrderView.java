package bg.softuni.pizzashop.model.view;

import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class OrderView {

    private Long id;

    private OrderStatusEnum orderStatus;

    private String description;

    private double price;

    private LocalDateTime orderTime;

    private Map<Product, Integer> productQuantity;

    private User user;
}
