package jpa.crudpractice.domain.response;

import jpa.crudpractice.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostListResponse {
    private Long postId;
    private String title;

    static public PostListResponse from(Post post) {
        return new PostListResponse(
                post.getId(), post.getTitle()
        );
    }
}
