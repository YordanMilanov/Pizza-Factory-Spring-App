package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.errorHandling.ObjectNotFoundException;
import bg.softuni.pizzashop.model.entity.Order;
import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import bg.softuni.pizzashop.model.view.OrderView;
import bg.softuni.pizzashop.repository.OrderRepository;
import bg.softuni.pizzashop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderView> allOrdersByOrderStatus(OrderStatusEnum orderStatusEnum) {
        return orderRepository.findAllByOrderStatus(orderStatusEnum).get()
                .stream()
                .map(o -> modelMapper.map(o, OrderView.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderView findByIdViewModel(Long id) {
        boolean present = orderRepository.findById(id).isPresent();
        if(!present) {
            throw new ObjectNotFoundException(id, "Order");
        }
        return modelMapper.map(orderRepository.findById(id).get(), OrderView.class);
    }

    @Override
    public void sortProductsInOrder(OrderView orderView) {
        Map<Product, Integer> sortedMap = new TreeMap<>(Comparator.comparing(Product::getName));
        sortedMap.putAll(orderView.getProductQuantity());
        orderView.setProductQuantity(sortedMap);
    }

    //finish order at set finish time
    @Override
    public void finishOrder(Order byId) {
        byId.setOrderStatus(OrderStatusEnum.FINISHED);
        byId.setOrderTime(LocalDateTime.now());
        orderRepository.save(byId);
    }

    @Override
    public Order findById(Long id) {
        if(orderRepository.findById(id).isPresent()) {
        return orderRepository.findById(id).get();
        } else {
            throw new ObjectNotFoundException(id, "Order");
        }
    }


}
