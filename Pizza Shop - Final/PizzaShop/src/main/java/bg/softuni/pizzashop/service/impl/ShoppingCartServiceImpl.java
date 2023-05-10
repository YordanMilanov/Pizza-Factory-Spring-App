package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.Order;
import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.OrderStatusEnum;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import bg.softuni.pizzashop.repository.IngredientRepository;
import bg.softuni.pizzashop.repository.OrderRepository;
import bg.softuni.pizzashop.repository.ProductRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final IngredientRepository ingredientRepository;

    private Map<Product, Integer> products = new TreeMap<>(Comparator.comparing(Product::getId));

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository, IngredientRepository ingredientRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
    }


//     If product is in the map just increment quantity by 1.
//     If product is not in the map with, add it with quantity 1

    @Override
    public void addProduct(Long id) {
        Product product = productRepository.findById(id).get();
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

//     If product is in the map with quantity > 1, just decrement quantity by 1.
//     If product is in the map with quantity 1, remove it from map

    @Override
    public void removeProduct(Long id) {

        Product product = productRepository.findById(id).get();
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }


    //      return unmodifiable copy of the map
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }


    public void buyCart(String description) throws IllegalArgumentException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).get();

        Set<String> outOfStockProducts = new TreeSet<>();
        Map<Product, Integer> canBeCookedProducts = new HashMap<>(products);

        checkWhichProductsCanBeCookedDependingOnIngredientStock(outOfStockProducts, canBeCookedProducts);

        //shopping cart updated with the products that can be cooked
        products = new HashMap<>(canBeCookedProducts);

        // product - quantity
        for (Map.Entry<Product, Integer> productEntry : products.entrySet()) {


            Integer orderedProductQuantity = productEntry.getValue();
            // product's -> ingredients-required quantity
            consumeIngredientsFromTheStorageDependingOnTheRequiredIngredients(productEntry, orderedProductQuantity);
        }

        Order order = new Order();
        order.setOrderStatus(OrderStatusEnum.IN_PROCESS);
        order.setOrderTime(LocalDateTime.now());
        order.setPrice(getTotal());
        order.setUser(user);
        order.setDescription(description);

        //could not find the products to save them that's why iterate again
        Map<Product, Integer> productQuantityToSave = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = productRepository.findByName(entry.getKey().getName()).get();
            productQuantityToSave.put(product, entry.getValue());
        }
        order.setProductQuantity(productQuantityToSave);

        // if none of the products can be prepared
        if (products.size() == 0) {
            String output = "The order is not accepted. " + String.join(", ", outOfStockProducts) + " are out of stock! We apologize for the \n" +
                    "inconvenience. Thank you for using our site.";
            throw new IllegalArgumentException(output);
        }

        orderRepository.save(order);
        products.clear();
        if (outOfStockProducts.size() > 0) {
            String output = "The order is accepted. " + String.join(", ", outOfStockProducts) + " are out of stock! We apologize for the \n" +
                    "inconvenience. Thank you for using our site.";
            throw new IllegalArgumentException(output);
        }

        List<Order> userOrders = orderRepository.findByUser_Id(user.getId()).get();


        //check if the user is lower rank the vip, so we don't lower his rank instead of promote him
        updateUserLevelByOrdersCount(user, userOrders);
    }



    @Override
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        //add the discount for user Level
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepository.findByUsername(username).get();

        switch (user.getLevel()) {
            case EMPLOYEE:
                total = total * 0.5;
                break;
            case VIP:
                total = total * 0.8;
                break;
            case REGULAR:
                total = total * 0.9;
                break;
        }

        return total;
    }

    private boolean productCanBeCooked(Product product, Integer quantity) {
        for (Map.Entry<Ingredient, Integer> entry : product.getRequiredProducts().entrySet()) {
            Ingredient ingredient = ingredientRepository.findByName(entry.getKey().getName()).get();
            if (ingredient.getStockInKg() < (entry.getValue() * 1.0 / 1000) * quantity) {
                return false;
            }
        }
        return true;
    }

    private void updateUserLevelByOrdersCount(User user, List<Order> userOrders) {
        if (user.getLevel() != UserLevelEnum.VIP) {
            //check if the user reached REGULAR or VIP Level
            if (userOrders.size() > 9) {
                user.setLevel(UserLevelEnum.VIP);
            } else if (userOrders.size() > 4) {
                user.setLevel(UserLevelEnum.REGULAR);
            }
            userRepository.save(user);
        }
    }
    private void consumeIngredientsFromTheStorageDependingOnTheRequiredIngredients(Map.Entry<Product, Integer> productEntry, Integer orderedProductQuantity) {
        for (Map.Entry<Ingredient, Integer> ingredientEntry : productEntry.getKey().getRequiredProducts().entrySet()) {

            Ingredient currentIngredientOfTheProduct = ingredientEntry.getKey();
            Integer requiredQuantityOfIngredientForTheProduct = ingredientEntry.getValue();

            Ingredient ingredient = ingredientRepository.findByName(currentIngredientOfTheProduct.getName()).get();

            double currentStock = ingredient.getStockInKg();
            double updateIngredientQuantity = currentStock - ((requiredQuantityOfIngredientForTheProduct * 1.000 / 1000) * orderedProductQuantity);

            ingredient.setStockInKg(0);
            ingredient.setStockInKg(updateIngredientQuantity);
            ingredientRepository.save(ingredient);
        }
    }

    private void checkWhichProductsCanBeCookedDependingOnIngredientStock(Set<String> outOfStockProducts, Map<Product, Integer> canBeCookedProducts) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            //check if there is enough products to complete the product
            boolean canBeCooked = productCanBeCooked(entry.getKey(), entry.getValue());
            if (!canBeCooked) {
                outOfStockProducts.add(entry.getKey().getName());

                String productToRemoveName = entry.getKey().getName();

                Product productToRemove = null;

                //taking the same product
                for (Map.Entry<Product, Integer> productIntegerEntry : canBeCookedProducts.entrySet()) {
                    if (productIntegerEntry.getKey().getName().equals(productToRemoveName)) {
                        productToRemove = productIntegerEntry.getKey();
                        break;
                    }
                }
                canBeCookedProducts.remove(productToRemove);
            }
        }
    }
}
