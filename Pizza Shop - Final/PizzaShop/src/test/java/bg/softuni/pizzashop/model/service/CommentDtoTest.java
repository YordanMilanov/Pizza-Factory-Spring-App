package bg.softuni.pizzashop.model.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentDtoTest {

    @Test
    void testGetSetText() {
        CommentDto commentDto = new CommentDto();
        String expectedText = "This is a comment.";
        commentDto.setText(expectedText);
        String actualText = commentDto.getText();
        assertEquals(expectedText, actualText);
    }

    @Test
    public void testAllArgsConstructor() {
        String text = "Test comment";
        CommentDto commentDto = new CommentDto(text);
        assertEquals(text, commentDto.getText());
    }
}
