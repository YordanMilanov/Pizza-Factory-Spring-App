package bg.softuni.pizzashop.service;

import bg.softuni.pizzashop.model.entity.Comment;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.service.CommentDto;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();

    public Comment createdComment (CommentDto commentDto, User author);

    Comment getCommentById(Long commentId);

    Comment deleteComment(Long id);
}
