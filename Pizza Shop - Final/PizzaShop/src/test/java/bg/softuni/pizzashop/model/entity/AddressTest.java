package bg.softuni.pizzashop.model.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AddressTest {

    @Test
    public void testToString() {
        Address address = new Address();
        address.setCity("City");
        address.setNeighborhood("Center");
        address.setStreet("Street");
        address.setStreetNumber(4);
        String expected = "City, Center, Street, 4";
        String actual = address.toString();
        assertEquals(expected, actual);
        assertEquals("City", address.getCity());
        assertEquals("Center", address.getNeighborhood());
        assertEquals("Street",address.getStreet());
        assertEquals(4,address.getStreetNumber());
    }
}
