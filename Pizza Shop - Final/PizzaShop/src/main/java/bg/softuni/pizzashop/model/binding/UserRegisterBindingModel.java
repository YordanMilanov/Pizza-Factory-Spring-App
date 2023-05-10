package bg.softuni.pizzashop.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @Size(min = 5, max = 20, message = "Username name must between 5 and 20 symbols")
    private String username;

    @Size(min = 5, max = 50, message = "Name name must between 5 and 50 symbols")
    private String fullName;

    @Email
    @NotNull
    @Size(min = 5, max = 50, message = "Email name must between 5 and 50 symbols")
    private String email;

    @Size(min = 5, max = 20, message = "Email name must between 5 and 50 symbols")
    private String phone;

    @Size(min = 5, message = "City name must be at least 5 or more symbols")
    private String password;

    @Size(min = 5, message = "City name must be at least 5 or more symbols")
    private String confirmPassword;

    @Size(min = 2, message = "City name must be at least 2 or more symbols")
    private String city;

    @Size(min = 2, message = "Neighborhood name must be at least 2 or more symbols")
    private String neighborhood;

    @Size(min = 2, message = "Street name must be at least 2 or more symbols")
    private String street;

    @Positive(message = "Street number must be positive")
    private int streetNumber;
}
