package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;

    @Column(columnDefinition = "TEXT")
    private String description;
}
