package jpa.crudpractice.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDeleteRequest implements Serializable {
    private Long postId;
    private Long commentId;
}
