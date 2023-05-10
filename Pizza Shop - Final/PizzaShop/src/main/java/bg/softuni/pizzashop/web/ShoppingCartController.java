package bg.softuni.pizzashop.web;

import bg.softuni.pizzashop.model.binding.OrderAddBindingModel;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import bg.softuni.pizzashop.service.AuthService;
import bg.softuni.pizzashop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final AuthService authService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, AuthService authService) {
        this.shoppingCartService = shoppingCartService;
        this.authService = authService;
    }

    @PostMapping("/cart")
    public String orderConfirm(OrderAddBindingModel orderAddBindingModel, RedirectAttributes redirectAttributes) {
        try {
            shoppingCartService.buyCart(orderAddBindingModel.getDescription());
            redirectAttributes.addFlashAttribute("OrderSuccessful", "Order successfully added!");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("OrderMissingItem", ex.getMessage());
        }
        return "redirect:/order/cart";
    }

    @GetMapping("cart/remove/{id}")
    public String productRemove(@PathVariable(name = "id") Long id) {
        shoppingCartService.removeProduct(id);
        return "redirect:/order/cart";
    }

    @GetMapping("/cart")
    public String orderCart(Model model) {

        model.addAttribute("products", shoppingCartService.getProductsInCart());
        model.addAttribute("total", shoppingCartService.getTotal());

        if(!model.containsAttribute("OrderSuccessful")) {
            model.addAttribute("OrderSuccessful", "");
        }

        if(!model.containsAttribute("OrderMissingItem")) {
            model.addAttribute("OrderMissingItem", "");
        }
        return "order-user-cart";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }
}
