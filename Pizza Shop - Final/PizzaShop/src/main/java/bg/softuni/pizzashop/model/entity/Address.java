package bg.softuni.pizzashop.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@Getter
@Setter
public class Address extends BaseEntity {

    @Column
    private String city;

    @Column
    private String neighborhood;

    @Column
    private String street;

    @Column
    private int streetNumber;

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s",this.city, this.neighborhood, this.street, this.streetNumber);
    }
}
