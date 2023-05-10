package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.Address;
import bg.softuni.pizzashop.model.entity.Role;
import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private UserLevelEnum level;
    private Set<Role> roles;
    private Address address;
}
