package bg.softuni.pizzashop.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ip_addresses")
@NoArgsConstructor
@Getter
@Setter
public class IpAddress extends BaseEntity{

    private String ipAddress;
    private boolean banned;
}
