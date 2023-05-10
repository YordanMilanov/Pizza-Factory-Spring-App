package bg.softuni.pizzashop.model.view;

import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserView {

    private Long id;

    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    @NotBlank(message = "Name cannot be empty!")
    private String username;

    private String fullName;
    private String password;
    private String email;
    private String phone;
    private Role newRole;
    private UserLevelEnum level;
    private Set<Role> roles;
    private Address address;
}
