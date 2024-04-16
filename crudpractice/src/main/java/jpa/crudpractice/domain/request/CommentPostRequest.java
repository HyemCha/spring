package jpa.crudpractice.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentPostRequest implements Serializable {
    private Long postId;
    private String content;
}
