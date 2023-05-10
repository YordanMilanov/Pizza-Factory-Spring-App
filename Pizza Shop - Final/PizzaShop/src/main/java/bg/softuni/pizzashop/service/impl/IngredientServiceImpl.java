package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.model.binding.IngredientRestockBindingModel;
import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import bg.softuni.pizzashop.model.service.IngredientServiceModel;
import bg.softuni.pizzashop.repository.IngredientRepository;
import bg.softuni.pizzashop.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    private final ModelMapper modelMapper;

    private Logger LOGGER = LoggerFactory.getLogger(IngredientServiceImpl.class);

    public IngredientServiceImpl(IngredientRepository ingredientRepository, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IngredientServiceModel saveIngredient(IngredientServiceModel ingredientServiceModel) {

        Ingredient ingredientToSave = modelMapper.map(ingredientServiceModel, Ingredient.class);

        ingredientToSave.setCalories(ingredientToSave.getProtein() * 4 + ingredientToSave.getFat() * 9  + ingredientToSave.getCarbohydrates() * 4);

       return modelMapper.map(ingredientRepository.save(ingredientToSave), IngredientServiceModel.class);
    }

    @Override
    public List<IngredientServiceModel> findAll() {
        List<Ingredient> ingredients = ingredientRepository.findAllMainIngredientsOrderByName(IngredientTypeEnum.MAIN);
        return ingredients.stream().map(ingredient -> modelMapper.map(ingredient, IngredientServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void updateStock(IngredientRestockBindingModel ingredientRestockBindingModel) {
        Ingredient ingredient = ingredientRepository.findByName(ingredientRestockBindingModel.getName()).get();
        double stockInKg = ingredient.getStockInKg();
        double toAdd = ingredientRestockBindingModel.getStockInKg().doubleValue();
        double total = stockInKg + toAdd;
        ingredient.setStockInKg(total);
        ingredient.setPrice(ingredientRestockBindingModel.getPrice().doubleValue());
        ingredientRepository.save(ingredient);
    }

    @Override
    public IngredientServiceModel findById(Long id) {
        return modelMapper.map(ingredientRepository.findById(id).get(), IngredientServiceModel.class);
    }


    //auto update the ingredient quantity by 100grams each 10 minutes
    @Scheduled(fixedRate = 60000)
//    @Scheduled(cron = "0 0 11 ? 4 *", cron = "0 0 23 ? 4 *") --> Scheduled to be done twice a day every april at 11am / 11pm
    public void updateStockEveryOneMinute() {
        List<Ingredient> all = ingredientRepository.findAll();
        for (Ingredient ingredient : all) {
            ingredient.setStockInKg(ingredient.getStockInKg() + 0.100);
            ingredientRepository.save(ingredient);
        }
        LOGGER.info("All Ingredients Updated!");
    }
}
