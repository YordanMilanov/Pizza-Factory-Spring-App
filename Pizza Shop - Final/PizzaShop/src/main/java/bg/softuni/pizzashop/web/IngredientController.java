package bg.softuni.pizzashop.web;

import bg.softuni.pizzashop.model.binding.IngredientAddBindingModel;
import bg.softuni.pizzashop.model.binding.IngredientRestockBindingModel;
import bg.softuni.pizzashop.model.service.IngredientServiceModel;
import bg.softuni.pizzashop.service.IngredientService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final ModelMapper modelMapper;
    private final IngredientService ingredientService;

    public IngredientController(ModelMapper modelMapper, IngredientService ingredientService) {
        this.modelMapper = modelMapper;
        this.ingredientService = ingredientService;
    }


    @GetMapping("/add")
    public String addIngredient() {
        return "add-ingredient";
    }

    @PostMapping("/add")
    public String addIngredientConfirm(@Valid IngredientAddBindingModel ingredientAddBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ingredientAddBindingModel", ingredientAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddBindingModel", bindingResult);

            return "redirect:/ingredient/add";
        }

        ingredientService.saveIngredient(modelMapper.map(ingredientAddBindingModel, IngredientServiceModel.class));

        return "redirect:/ingredient/list";
    }

    @PostMapping("/restock/{id}")
    public String restockIngredientConfirm(@Valid IngredientRestockBindingModel ingredientRestockBindingModel,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes,
                                           @PathVariable(name = "id") Long id) throws IOException {

        IngredientServiceModel ingredientToUpdate = ingredientService.findById(id);
        ingredientRestockBindingModel.setId(ingredientToUpdate.getId());
        ingredientRestockBindingModel.setName(ingredientToUpdate.getName());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ingredientRestockBindingModel", ingredientRestockBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientRestockBindingModel", bindingResult);

            return "redirect:/ingredient/restock/" + id;
        }

        ingredientService.updateStock(ingredientRestockBindingModel);

        return "redirect:/ingredient/list";
    }

    @GetMapping("/list")
    public String ingredientList(Model model) {
        model.addAttribute("allIngredient", ingredientService.findAll());
        return "list-ingredient";
    }

    @GetMapping("/restock/{id}")
    public String ingredientRestock(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.findById(id));
        return "restock-ingredient";
    }

    @ModelAttribute
    public IngredientRestockBindingModel ingredientRestockBindingModel() {
        return new IngredientRestockBindingModel();
    }

    @ModelAttribute
    public IngredientAddBindingModel ingredientAddBindingModel() {
        return new IngredientAddBindingModel();
    }
}
