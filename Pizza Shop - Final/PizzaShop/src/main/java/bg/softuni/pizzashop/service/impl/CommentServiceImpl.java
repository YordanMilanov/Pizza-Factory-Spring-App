package bg.softuni.pizzashop.service.impl;

import bg.softuni.pizzashop.model.entity.Comment;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.service.CommentDto;
import bg.softuni.pizzashop.repository.CommentRepository;
import bg.softuni.pizzashop.repository.UserRepository;
import bg.softuni.pizzashop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
 public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Comment> getAllComments() {
       return commentRepository.findAll();
    }

    @Override
    public Comment createdComment (CommentDto commentDto, User author) {
       Comment comment = new Comment();
       comment.setCreateTime(LocalDateTime.now());
       comment.setAuthor(author);
       comment.setText(commentDto.getText());
       commentRepository.save(comment);

       return comment;
    }

   @Override
   public Comment getCommentById(Long commentId) {
      return commentRepository.findById(commentId).get();
   }

    @Override
    public Comment deleteComment(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
        return comment;
    }

}
