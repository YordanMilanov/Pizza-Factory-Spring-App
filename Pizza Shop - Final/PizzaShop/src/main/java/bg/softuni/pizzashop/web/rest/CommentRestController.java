package bg.softuni.pizzashop.web.rest;

import bg.softuni.pizzashop.model.entity.Comment;
import bg.softuni.pizzashop.model.entity.User;
import bg.softuni.pizzashop.model.entity.enums.RoleNameEnum;
import bg.softuni.pizzashop.model.service.CommentDto;
import bg.softuni.pizzashop.model.view.CommentView;
import bg.softuni.pizzashop.service.AuthService;
import bg.softuni.pizzashop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentRestController {

    private final CommentService commentService;

    private final AuthService authService;

    @Autowired
    public CommentRestController(CommentService commentService, AuthService authService) {
        this.commentService = commentService;
        this.authService = authService;
    }

    @GetMapping("/api/comments")
    public ResponseEntity<List<CommentView>> getAllComments() {

        List<Comment> comments = commentService.getAllComments();
        List<CommentView> collect = comments.stream().map(comment -> mapToCommentView(comment))
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @GetMapping("/api/comments/{commentId}")
    private ResponseEntity<CommentView> getComment(@PathVariable(name = "commentId") Long commentId) {
        Comment commentById = commentService.getCommentById(commentId);
        CommentView commentView = mapToCommentView(commentById);
        CommentView ok = ResponseEntity.ok(commentView).getBody();
        return ResponseEntity.ok(commentView);
    }

    @PostMapping(value = "/api/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentView> createComment(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CommentDto commentDto
    ) {
        User author = authService.getUserByUsername(userDetails.getUsername());
        Comment comment = commentService.createdComment(commentDto, author);

        if(commentDto.getText().length() == 0 || commentDto.getText() == null) {
            return null;
        }

        CommentView commentView = mapToCommentView(comment);

        return ResponseEntity.
                created(URI.create("/api/comments/" + comment.getId()))
                .body(commentView);
    }

    private static CommentView mapToCommentView(Comment comment) {
        return new CommentView(
                comment.getId(), comment.getText(), comment.getAuthor().getUsername(), comment.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentView> deleteComment(@PathVariable(name = "commentId") Long commentId,
                                                     @AuthenticationPrincipal UserDetails principal) {
        User user = authService.getUserByUsername(principal.getUsername());
        Comment comment = commentService.getCommentById(commentId);

        if (user.getRoles()
                .stream()
                .anyMatch(r -> r.getRole() == RoleNameEnum.MANAGER
                        || r.getRole() == RoleNameEnum.STAFF)
                || user.getId() == comment.getAuthor().getId()) {
            Comment deleted = commentService.deleteComment(commentId);
            return ResponseEntity.ok(mapToCommentView(deleted));
        }
        return ResponseEntity.status(403).build();
    }
}
