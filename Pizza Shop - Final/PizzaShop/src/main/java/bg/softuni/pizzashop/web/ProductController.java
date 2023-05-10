package bg.softuni.pizzashop.web;

import bg.softuni.pizzashop.errorHandling.ObjectNotFoundException;
import bg.softuni.pizzashop.model.binding.ProductAddBindingModel;
import bg.softuni.pizzashop.model.binding.ProductIngredientsAddBindingModel;
import bg.softuni.pizzashop.model.entity.Picture;
import bg.softuni.pizzashop.model.service.IngredientServiceModel;
import bg.softuni.pizzashop.model.service.ProductServiceModel;
import bg.softuni.pizzashop.service.IngredientService;
import bg.softuni.pizzashop.service.PictureService;
import bg.softuni.pizzashop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final PictureService pictureService;
    private final IngredientService ingredientService;

    public ProductController(ModelMapper modelMapper, ProductService productService, PictureService pictureService, IngredientService ingredientService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.pictureService = pictureService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/add")
    public String addProduct() {
        return "add-product";
    }

    @GetMapping("/add/ingredients/{id}")
    public String addProductIngredients(@PathVariable Long id, Model model) {
        //to use the image from the db first we need to convert it from byte[] to Base64 String and then pass it to the html as an attribute
        model.addAttribute("imageURL", productService.findProductById(id).getPicture().getURL());
        model.addAttribute("allIngredients", allIngredients());
        model.addAttribute("product", productService.findProductById(id));
        return "add-product-ingredients";
    }

    @PostMapping("/add")
   public String addProductConfirm(@Valid ProductAddBindingModel productAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);

            return "redirect:/product/add";
        }

        Picture picture = pictureService.uploadPicture(productAddBindingModel.getPicture(), productAddBindingModel.getName());
        ProductServiceModel productServiceModel = modelMapper.map(productAddBindingModel, ProductServiceModel.class);
        productServiceModel.setPicture(picture);
        productService.saveProduct(productServiceModel);

        try {
        Long productId = productService.findLastAddedProduct().getId();
        return "redirect:/product/add/ingredients/" + productId;
        } catch (ObjectNotFoundException o) {
            return "error";
        }


    }

    @PostMapping("/add/ingredients/{id}")
    public String addProductIngredientsConfirm(@PathVariable("id")Long id, @Valid ProductIngredientsAddBindingModel productIngredientsAddBindingModel,
                                               BindingResult bindingResult,
                                               RedirectAttributes redirectAttributes, Model model) throws IOException {


        if (bindingResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("productIngredientsAddBindingModel", productIngredientsAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productIngredientsAddBindingModel", bindingResult);
            return "redirect:/product/add/ingredients/" + id.toString();
        }

        productService.UpdateIngredientToProductById(id, productIngredientsAddBindingModel.getIngredientName(), productIngredientsAddBindingModel.getIngredientGram());
        return "redirect:/product/add/ingredients/" + id.toString();
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }

    @ModelAttribute
    public ProductIngredientsAddBindingModel productIngredientsAddBindingModel() {
        return new ProductIngredientsAddBindingModel();
    }

    @ModelAttribute
    public List<String> allIngredients() {
       return ingredientService.findAll().stream().map(IngredientServiceModel::getName).collect(Collectors.toList());
    }
}
