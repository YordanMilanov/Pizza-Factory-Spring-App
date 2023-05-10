package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.entity.Product;

import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Long id);

    void removeProduct(Long id);

    Map<Product, Integer> getProductsInCart();

    double getTotal();

    void buyCart(String description);
}
