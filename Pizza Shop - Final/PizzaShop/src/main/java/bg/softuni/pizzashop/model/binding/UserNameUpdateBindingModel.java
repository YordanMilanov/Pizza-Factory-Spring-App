package bg.softuni.pizzashop.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserNameUpdateBindingModel {

    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    @NotBlank(message = "Name cannot be empty!")
    private String username;
}
