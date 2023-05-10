package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.enums.IngredientTypeEnum;
import bg.softuni.pizzashop.model.service.IngredientServiceModel;
import bg.softuni.pizzashop.repository.IngredientRepository;
import bg.softuni.pizzashop.service.impl.IngredientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    public void saveIngredient_ShouldSaveIngredientAndReturnServiceModel() {
        // Arrange
        Ingredient ingredientToSave = new Ingredient();
        ingredientToSave.setName("Tomato");
        ingredientToSave.setProtein(1);
        ingredientToSave.setFat(0);
        ingredientToSave.setCarbohydrates(5);

        IngredientServiceModel ingredientServiceModel = new IngredientServiceModel();
        ingredientServiceModel.setName("Tomato");
        ingredientServiceModel.setProtein(1);
        ingredientServiceModel.setFat(0);
        ingredientServiceModel.setCarbohydrates(5);

        when(modelMapper.map(ingredientServiceModel, Ingredient.class)).thenReturn(ingredientToSave);
        when(ingredientRepository.save(ingredientToSave)).thenReturn(ingredientToSave);
        when(modelMapper.map(ingredientToSave, IngredientServiceModel.class)).thenReturn(ingredientServiceModel);

        // Act
        IngredientServiceModel result = ingredientService.saveIngredient(ingredientServiceModel);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getName(), ingredientServiceModel.getName());
        Assertions.assertEquals(result.getProtein(), ingredientServiceModel.getProtein(), 0.001);
        Assertions.assertEquals(result.getFat(), ingredientServiceModel.getFat(), 0.001);
        Assertions.assertEquals(result.getCarbohydrates(), ingredientServiceModel.getCarbohydrates(), 0.001);
        verify(modelMapper, times(1)).map(ingredientServiceModel, Ingredient.class);
        verify(ingredientRepository, times(1)).save(ingredientToSave);
        verify(modelMapper, times(1)).map(ingredientToSave, IngredientServiceModel.class);
    }

    @Test
    public void findAll_ShouldReturnAllIngredients() {
        // Arrange
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient tomato = new Ingredient();
        tomato.setId(1L);
        tomato.setName("Tomato");
        Ingredient lettuce = new Ingredient();
        lettuce.setId(2L);
        lettuce.setName("Lettuce");
        ingredients.add(tomato);
        ingredients.add(lettuce);

        when(ingredientRepository.findAllMainIngredientsOrderByName(IngredientTypeEnum.MAIN)).thenReturn(ingredients);
        when(modelMapper.map(tomato, IngredientServiceModel.class)).thenReturn(new IngredientServiceModel());
        when(modelMapper.map(lettuce, IngredientServiceModel.class)).thenReturn(new IngredientServiceModel());

        // Act
        List<IngredientServiceModel> result = ingredientService.findAll();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        verify(ingredientRepository, times(1)).findAllMainIngredientsOrderByName(IngredientTypeEnum.MAIN);
        verify(modelMapper, times(1)).map(tomato, IngredientServiceModel.class);
        verify(modelMapper, times(1)).map(lettuce, IngredientServiceModel.class);
    }

    @Test
    public void testSaveIngredient() {
        // given
        IngredientServiceModel ingredientServiceModel = new IngredientServiceModel();
        ingredientServiceModel.setName("Test Ingredient");
        ingredientServiceModel.setProtein(10);
        ingredientServiceModel.setFat(20);
        ingredientServiceModel.setCarbohydrates(30);
        Ingredient ingredientToSave = new Ingredient();
        ingredientToSave.setName("Test Ingredient");
        ingredientToSave.setProtein(10);
        ingredientToSave.setFat(20);
        ingredientToSave.setCarbohydrates(30);

        when(modelMapper.map(ingredientServiceModel, Ingredient.class)).thenReturn(ingredientToSave);
        when(ingredientRepository.save(ingredientToSave)).thenReturn(ingredientToSave);

        IngredientServiceModel savedIngredient = ingredientService.saveIngredient(ingredientServiceModel);

        verify(modelMapper).map(ingredientServiceModel, Ingredient.class);
        verify(ingredientRepository).save(ingredientToSave);
        Assertions.assertEquals("Test Ingredient", savedIngredient.getName());
        Assertions.assertEquals(10, savedIngredient.getProtein() );
        Assertions.assertEquals(20, savedIngredient.getFat());
        Assertions.assertEquals(30, savedIngredient.getCarbohydrates());
    }

    @Test
    public void testFindAll() {
        // given
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Ingredient 1");
        ingredient1.setIngredientType(IngredientTypeEnum.MAIN);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Ingredient 2");
        ingredient2.setIngredientType(IngredientTypeEnum.MAIN);
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        when(ingredientRepository.findAllMainIngredientsOrderByName(IngredientTypeEnum.MAIN)).thenReturn(ingredients);

        List<IngredientServiceModel> expected = new ArrayList<>();

        IngredientServiceModel expected1 = new IngredientServiceModel();
        expected1.setName("Ingredient 1");
        expected1.setIngredientType(IngredientTypeEnum.MAIN);

        IngredientServiceModel expected2 = new IngredientServiceModel();
        expected2.setName("Ingredient 2");
        expected2.setIngredientType(IngredientTypeEnum.MAIN);

        expected.add(expected1);
        expected.add(expected2);

        when(modelMapper.map(ingredient1, IngredientServiceModel.class)).thenReturn(expected1);
        when(modelMapper.map(ingredient2, IngredientServiceModel.class)).thenReturn(expected2);



        // when
        List<IngredientServiceModel> actual = ingredientService.findAll();

        // then
        verify(ingredientRepository).findAllMainIngredientsOrderByName(IngredientTypeEnum.MAIN);
        Assertions.assertEquals(expected, actual);
    }
}