package bg.softuni.pizzashop.model.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentViewTest {

    @Test
    public void testConstructorAndGetters() {
        Long id = 1L;
        String text = "Test comment";
        String authorName = "John Doe";
        LocalDateTime createTime = LocalDateTime.now();

        CommentView commentView = new CommentView(id, text, authorName, createTime.toString());
        CommentView commentView2 = new CommentView();
        commentView2.setId(1L);
        commentView2.setText("testtext");
        commentView2.setCreateTime("string");
        commentView2.setAuthorName("author");

        assertEquals(id, commentView.getId());
        assertEquals(text, commentView.getText());
        assertEquals(authorName, commentView.getAuthorName());
        assertEquals(createTime.toString(), commentView.getCreateTime());
        assertEquals(1, commentView2.getId());
        assertEquals("testtext", commentView2.getText());
        assertEquals("string",commentView2.getCreateTime());
        assertEquals("author",commentView2.getAuthorName());

    }
}
