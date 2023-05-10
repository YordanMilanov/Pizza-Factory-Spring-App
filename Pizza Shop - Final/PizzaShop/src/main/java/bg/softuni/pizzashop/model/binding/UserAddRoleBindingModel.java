package bg.softuni.pizzashop.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAddRoleBindingModel {
    private Long id;
    private String roleName;
}
