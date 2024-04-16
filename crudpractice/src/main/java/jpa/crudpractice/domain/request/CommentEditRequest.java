package jpa.crudpractice.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentEditRequest implements Serializable {
    private Long postId;
    private Long commentId;
    private String commentContent;
}
