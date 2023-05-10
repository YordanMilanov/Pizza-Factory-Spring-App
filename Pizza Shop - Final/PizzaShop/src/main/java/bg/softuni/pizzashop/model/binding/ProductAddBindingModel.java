package bg.softuni.pizzashop.model.binding;

import bg.softuni.pizzashop.model.entity.Ingredient;
import bg.softuni.pizzashop.model.entity.Picture;
import bg.softuni.pizzashop.model.entity.enums.ProductTypeEnum;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddBindingModel {

    @Size(min = 3, max = 20, message = "Product name must be between 3 and 20 characters!")
    private String name;

    @Positive(message = "Product name must be between 3 and 20 characters!")
    private BigDecimal price;

    @Size(min = 3, max = 50, message = "Description must be between 3 and 50 characters!")
    private String description;

    @NotNull(message = "Type must be selected!")
    private ProductTypeEnum productTypeEnum;

    @NotNull(message = "Image must be uploaded!")
    private MultipartFile picture;
}
