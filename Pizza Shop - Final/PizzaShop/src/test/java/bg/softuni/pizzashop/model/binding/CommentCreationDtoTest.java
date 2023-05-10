package bg.softuni.pizzashop.model.binding;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentCreationDtoTest {

    @Test
    public void testCommentCreationDtoConstructor() {
        String username = "testuser";
        String message = "This is a test comment.";
        CommentCreationDto dto = new CommentCreationDto(username, message);
        Assertions.assertEquals(username, dto.getUsername());
        Assertions.assertEquals(message, dto.getMessage());
    }

    @Test
    public void testCommentCreationDtoSettersAndGetters() {
        String username = "testuser";
        String message = "This is a test comment.";
        CommentCreationDto dto = new CommentCreationDto();
        dto.setUsername(username);
        dto.setMessage(message);
        Assertions.assertEquals(username, dto.getUsername());
        Assertions.assertEquals(message, dto.getMessage());
    }
}
