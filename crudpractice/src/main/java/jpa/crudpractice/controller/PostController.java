package jpa.crudpractice.controller;

import jpa.crudpractice.domain.entity.Post;
import jpa.crudpractice.domain.request.PostDeleteRequest;
import jpa.crudpractice.domain.response.PostListResponse;
import jpa.crudpractice.domain.response.PostResponse;
import jpa.crudpractice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public PostResponse getPost(@RequestParam("postId") Long postId) {
        return postService.getPost(postId);
    }


    @PostMapping
    public Post addPost(@RequestBody Post post) {
        return postService.addPost(post);
    }

    @PostMapping
    public PostResponse updatePost(@RequestBody PostResponse postResponse) {
        return postService.updatePost(postResponse.getPostId(), postResponse.getTitle(), postResponse.getContent());
    }

    @DeleteMapping
    public PostResponse deletePost(@RequestBody PostDeleteRequest postDeleteRequest) {
        return postService.deletePost(postDeleteRequest.getPostId());
    }

    /**
     * 오프셋 기반
     * SELECT * FROM post
     * LIMIT offset, pageSize
     * @return
     */
    @GetMapping("/list")
    public List<PostListResponse> getPostList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ) {
        return postService.getPostList(page, pageSize);
    }

    /**
     * 커서 기반 - 더보기 방식을 기능을 사용할 때 많이 사용하는 기능. 처음 요청시에는 0에서 시작
     * SELECT * FROM post
     * WHERE post_id > ${postId}
     * LIMIT 3
     */
    @GetMapping("/list2")
    public List<PostListResponse> getPostList2(
            @RequestParam Long postId
    ) {
        return postService.getPostList2(postId);
    }

}
