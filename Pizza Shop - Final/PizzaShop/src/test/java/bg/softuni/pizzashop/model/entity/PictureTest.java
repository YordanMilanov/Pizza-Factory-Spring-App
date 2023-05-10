package bg.softuni.pizzashop.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PictureTest {

    @Test
    public void testPictureCreation() {
        Picture picture = new Picture();
        picture.setTitle("Test Picture");
        picture.setURL("https://example.com/test.jpg");

        Assertions.assertEquals("Test Picture", picture.getTitle());
        Assertions.assertEquals("https://example.com/test.jpg", picture.getURL());
    }
}
