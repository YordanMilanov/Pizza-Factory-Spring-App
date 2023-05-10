package bg.softuni.pizzashop.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class IpAddressTest {

    @Test
    public void testIpAddress() {
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIpAddress("192.168.1.1");
        ipAddress.setBanned(true);

        Assertions.assertEquals("192.168.1.1", ipAddress.getIpAddress());
        Assertions.assertTrue(ipAddress.isBanned());
    }

}
