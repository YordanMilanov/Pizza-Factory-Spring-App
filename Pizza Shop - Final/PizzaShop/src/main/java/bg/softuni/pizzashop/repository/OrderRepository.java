package bg.softuni.pizzashop.repository;

import bg.softuni.pizzashop.model.entity.Order;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<List<Order>> findAllByOrderStatus(OrderStatusEnum orderStatus);

    Optional<List<Order>> findByUser_Id(Long id);
}
