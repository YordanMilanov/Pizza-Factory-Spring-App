package bg.softuni.pizzashop.model.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentTest {

    private Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new Comment();
    }

    @Test
    public void setText_setsTextCorrectly() {
        String text = "This is a comment.";
        comment.setText(text);
        assertEquals(text, comment.getText());
    }

    @Test
    public void setCreateTime_setsCreateTimeCorrectly() {
        LocalDateTime createTime = LocalDateTime.now();
        comment.setCreateTime(createTime);
        assertEquals(createTime, comment.getCreateTime());
    }

    @Test
    public void setAuthor_setsAuthorCorrectly() {
        User author = new User();
        comment.setAuthor(author);
        assertEquals(author, comment.getAuthor());
    }
}
