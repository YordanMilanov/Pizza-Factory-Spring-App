package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.entity.Order;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import bg.softuni.pizzashop.model.view.OrderView;

import java.util.List;

public interface OrderService {

    List<OrderView> allOrdersByOrderStatus(OrderStatusEnum inProcess);


    OrderView findByIdViewModel(Long id);

    void sortProductsInOrder(OrderView orderView);

    void finishOrder(Order byId);

    Order findById(Long id);
}
