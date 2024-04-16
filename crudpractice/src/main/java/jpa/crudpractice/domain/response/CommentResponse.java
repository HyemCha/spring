package jpa.crudpractice.domain.response;

import jpa.crudpractice.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {

    private Long commentId;
    private String content;

    static public CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent()
        );
    }
}
