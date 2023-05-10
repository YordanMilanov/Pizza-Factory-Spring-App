package bg.softuni.pizzashop.services;

import bg.softuni.pizzashop.model.entity.Comment;
import bg.softuni.pizzashop.repository.CommentRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void getAllComments_ShouldReturnAllComments() {
        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setText("This is a comment");
        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setText("This is another comment");
        comments.add(comment1);
        comments.add(comment2);

        when(commentRepositoryMock.findAll()).thenReturn(comments);

        List<Comment> actualComments = commentService.getAllComments();

        Assertions.assertEquals(comments, actualComments);
    }

    @Test
    public void getCommentById_ShouldReturnCommentWithMatchingId() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("This is a comment");

        when(commentRepositoryMock.findById(1L)).thenReturn(Optional.of(comment));

        Comment actualComment = commentService.getCommentById(1L);

        Assertions.assertEquals(comment, actualComment);
    }

    @Test
    public void deleteComment_ShouldDeleteCommentWithMatchingId() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setText("This is a comment");

        when(commentRepositoryMock.findById(1L)).thenReturn(Optional.of(comment));

        Comment deletedComment = commentService.deleteComment(1L);

        verify(commentRepositoryMock).delete(comment);

        Assertions.assertEquals(comment, deletedComment);
    }

}

