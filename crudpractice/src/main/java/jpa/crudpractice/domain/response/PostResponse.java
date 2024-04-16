package jpa.crudpractice.domain.response;

import jpa.crudpractice.domain.entity.Post;
import jpa.crudpractice.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private PostStatus postStatus;
    private List<CommentResponse> comments;

    static public PostResponse from(Post post) {
        return new PostResponse(
            post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getPostStatus(),
                post.getComments().stream().map(CommentResponse::from).collect(Collectors.toList())
        );
    }
}
