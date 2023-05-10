package bg.softuni.pizzashop.web;

import bg.softuni.pizzashop.errorHandling.ObjectNotFoundException;
import bg.softuni.pizzashop.model.binding.OrderAddBindingModel;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import bg.softuni.pizzashop.model.view.OrderView;
import bg.softuni.pizzashop.repository.ProductRepository;
import bg.softuni.pizzashop.service.OrderService;
import bg.softuni.pizzashop.service.ShoppingCartService;
import bg.softuni.pizzashop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductRepository productRepository;

    private final UserService userService;

    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ProductRepository productRepository, UserService userService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }


    @GetMapping("/active")
    public String orderActive(Model model) {
       model.addAttribute("allOrders", orderService.allOrdersByOrderStatus(OrderStatusEnum.IN_PROCESS));
        return "order-staff-active";
    }

    @GetMapping("/completed")
    public String orderCompleted(Model model) {
        model.addAttribute("allOrders", orderService.allOrdersByOrderStatus(OrderStatusEnum.FINISHED));
        return "order-staff-completed";
    }

    @GetMapping("/view/{id}")
    public String orderView(@PathVariable(name = "id")Long id, Model model) {
        //sort the products by id
        OrderView orderView = orderService.findByIdViewModel(id);
        if(orderView.getId() == null) {
        throw new ObjectNotFoundException(id, "Order");
        }
        orderService.sortProductsInOrder(orderView);
        model.addAttribute("order", orderView);
        return "order-view";
    }

    @GetMapping("active/finish/{id}")
    public String finishOrder(@PathVariable(name = "id")Long id) {

        orderService.finishOrder(orderService.findById(id));

        return "redirect:/order/active";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }
}
