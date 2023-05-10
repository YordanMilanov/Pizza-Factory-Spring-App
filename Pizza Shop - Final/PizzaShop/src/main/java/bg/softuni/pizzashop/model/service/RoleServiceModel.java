package bg.softuni.pizzashop.model.service;

import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleServiceModel {

    private Long id;
    private RoleNameEnum role;
    private String description;
}
