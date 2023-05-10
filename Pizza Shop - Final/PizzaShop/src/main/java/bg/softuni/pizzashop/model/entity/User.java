package bg.softuni.pizzashop.model.entity;

import bg.softuni.pizzashop.model.entity.enums.UserLevelEnum;
import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(unique = true)
    private String username;

    @Column
    private String fullName;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserLevelEnum level;


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

}
