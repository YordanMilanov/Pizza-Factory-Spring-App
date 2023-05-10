package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.binding.IngredientRestockBindingModel;
import bg.softuni.pizzashop.model.service.IngredientServiceModel;

import java.util.List;

public interface IngredientService {
    IngredientServiceModel saveIngredient(IngredientServiceModel ingredientServiceModel);

    List<IngredientServiceModel> findAll();

    void updateStock(IngredientRestockBindingModel ingredientRestockBindingModel);

    IngredientServiceModel findById(Long id);
}
