package bg.softuni.pizzashop.web;

import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import bg.softuni.pizzashop.service.OrderService;
import bg.softuni.pizzashop.service.ProductService;
import bg.softuni.pizzashop.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService productService;
    private final OrderService orderService;

    private final ShoppingCartService shoppingCartService;

    public MenuController(ProductService productService, OrderService orderService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/{productName}")
    public String pizza(@PathVariable(name = "productName")String productName, Model model) {
        model.addAttribute("allProducts", productService.allProductsByType(ProductTypeEnum.valueOf(productName.toUpperCase())));
        return "menu-items";
    }

    @GetMapping("/{type}/{id}")
    public String addProductToCart(@PathVariable String type,@PathVariable Long id) {

        shoppingCartService.addProduct(id);
        return "redirect:/menu/" + type;
    }
}
