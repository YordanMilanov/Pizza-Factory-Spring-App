package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.errorHandling.ObjectNotFoundException;
import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.Product;
import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import bg.softuni.pizzashop.model.service.ProductServiceModel;
import bg.softuni.pizzashop.model.view.ProductView;
import bg.softuni.pizzashop.repository.IngredientRepository;
import bg.softuni.pizzashop.repository.ProductRepository;
import bg.softuni.pizzashop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    private final IngredientRepository ingredientRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public ProductServiceModel saveProduct(ProductServiceModel productServiceModel) {
        Product productToSave = modelMapper.map(productServiceModel, Product.class);
        productToSave.setGrams(0);
        productToSave.setCaloriesPer100(0);
        productRepository.save(productToSave);
        return modelMapper.map(productToSave, ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel findProductById(Long id) {
        ProductServiceModel productServiceModel = productRepository
                .findById(id)
                .map(product -> modelMapper.map(product, ProductServiceModel.class))
                .orElse(null);

        //sort the required products
        Map<Ingredient, Integer> unsortedMap = productServiceModel.getRequiredProducts();
        Map<Ingredient, Integer> sortedMap = new TreeMap<>(Comparator.comparingInt(ingredient -> Math.toIntExact(ingredient.getId())));
        sortedMap.putAll(unsortedMap);

        //save the required products
        productServiceModel.setRequiredProducts(sortedMap);
        return productServiceModel;
    }

    @Override
    public ProductServiceModel findLastAddedProduct() {
        return productRepository
                .findTopByOrderByIdDesc()
                .map(product -> modelMapper.map(product, ProductServiceModel.class))
                .orElseThrow();
    }

    @Override
    public void UpdateIngredientToProductById(Long id, String ingredientName, Integer ingredientGrams) {
        Product product = productRepository.findById(id).get();

        Ingredient ingredient = ingredientRepository.findByName(ingredientName).orElse(null);



       boolean isTheIngredientInTheList = isTheIngredientInTheList(ingredientName, ingredientGrams, product);

        //add it if it is not in the list
        if(!isTheIngredientInTheList) {
        product.getRequiredProducts().put(ingredient, ingredientGrams);
        }
        //logic to calculate the grams and the calories
        calculateGramsAndCaloriesOfTheProduct(product);

        productRepository.save(product);
    }



    @Override
    public List<ProductView> allProductsByType(ProductTypeEnum productTypeEnum) {

        List<Product> products = productRepository.findAllByProductTypeEnumOrderById(productTypeEnum.toString()).get();
        List<ProductView> collect = products.stream()
                .map(product -> {
                    ProductView map = modelMapper.map(product, ProductView.class);
                    map.setPictureURL(product.getPicture().getURL());
                    return map;
                        }
                )
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<ProductView> allProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(p -> modelMapper.map(p, ProductView.class)).collect(Collectors.toList());
    }


        //check if the ingredient is in the list and overwrite it if it is in the list
    private static boolean isTheIngredientInTheList(String ingredientName, Integer ingredientGrams, Product product) {
        for (Map.Entry<Ingredient, Integer> entry : product.getRequiredProducts().entrySet()) {
            if (entry.getKey().getName().equals(ingredientName)) {
                entry.setValue(ingredientGrams);
                return true;
            }
        }
        return false;
    }

        // logic to calculate the grams and the calories
    private static void calculateGramsAndCaloriesOfTheProduct(Product product) {
        int gramsToSave = 0;
        int totalCalories = 0;
        for (Map.Entry<Ingredient, Integer> entry : product.getRequiredProducts().entrySet()) {
          gramsToSave += entry.getValue();
          totalCalories += entry.getKey().getCalories() * entry.getValue() / 100;
        }
        double caloriesPer100 = Math.round(totalCalories / (gramsToSave / 100.0));
        product.setCaloriesPer100(caloriesPer100);
        product.setGrams(gramsToSave);
    }
}
