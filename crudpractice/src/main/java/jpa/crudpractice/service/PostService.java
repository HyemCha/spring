package jpa.crudpractice.service;

import jpa.crudpractice.domain.PostStatus;
import jpa.crudpractice.domain.entity.Post;
import jpa.crudpractice.domain.repository.PostRepository;
import jpa.crudpractice.domain.response.PostListResponse;
import jpa.crudpractice.domain.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostResponse getPost(Long postId) {
        return postRepository.findById(postId)
                .filter(post -> post.isActive())
                .map(PostResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물이비낟."));
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public PostResponse updatePost(Long postId, String title, String content) {
        return postRepository.findById(postId)
                .filter(post -> post.isActive())
                .map(post -> {
                    post.setTitle(title);
                    post.setContent(content);
                    return post;
                })
                .map(post -> PostResponse.from((Post) post))
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

    }

    public PostResponse deletePost(Long postId) {
        return postRepository.findById(postId)
                .filter(post -> post.isActive())
                .map(post -> {
                    post.doClose();
                    return post;
                })
                .map(PostResponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다."));
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).get();
    }

    /**
     * 오프셋 기반
     * @param page
     * @param pageSize
     * @return
     */
    public List<PostListResponse> getPostList(int page, int pageSize) {
        return postRepository.findAllByPostStatus(PostStatus.ACTIVE, PageRequest.of(page, pageSize, Sort.Direction.DESC, "postId"))
                .map(PostListResponse::from)
                .toList();


    }

    public List<PostListResponse> getPostList2(Long postId) {
        return (postId == 0L ?
                postRepository.findTop10ByPostStatusOrderByIdDesc(PostStatus.ACTIVE)
                : postRepository.findTop10ByPostStatusAndIdLessThanOrderByIdDesc(PostStatus.ACTIVE, postId))
                .stream().map(PostListResponse::from).collect(Collectors.toList());


    }
}
