package jpa.crudpractice.service;

import jakarta.persistence.EntityManager;
import jpa.crudpractice.domain.*;
import jpa.crudpractice.domain.entity.Comment;
import jpa.crudpractice.domain.entity.Post;
import jpa.crudpractice.domain.repository.CommentRepository;
import jpa.crudpractice.domain.repository.CommentRepositoryCustom;
import jpa.crudpractice.domain.repository.PostRepository;
import jpa.crudpractice.domain.repository.PostRepositoryCustom;
import jpa.crudpractice.domain.response.CommentResponse;
import jpa.crudpractice.domain.response.PostResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(value = false)
class CommentServiceTest {

    @Autowired private CommentService commentService;
    @Autowired private PostService postService;
    @Autowired private CommentRepository commentRepository;
    @Autowired private CommentRepositoryCustom commentRepositoryCustom;

    @Autowired
    EntityManager em;

    @BeforeEach
    void addpost() {

    }

    @Test
    void addComment1() {
        Post postex = new Post();
        postex.setTitle("title");
        postex.setContent("content");
        postex.setPostStatus(PostStatus.ACTIVE);
        postService.addPost(postex);
        Post postex2 = new Post();
        postex2.setTitle("title");
        postex2.setContent("content");
        postex2.setPostStatus(PostStatus.ACTIVE);
        postService.addPost(postex2);

        PostResponse postResponse = commentService.addComment(postex2.getId(), "comment1");
        log.info("postResponse={}", postResponse);

        List<Comment> all = commentRepository.findAll();
        for (Comment comment : all) {
            log.info("comment={}", comment.getContent());
            log.info("comment={}", comment.getPost());
        }
    }
    @Test
    void addComment() {
        Post post = new Post();
        post.setTitle("title1");
        post.setContent("content1");
        postService.addPost(post);

        Post post2 = new Post();
        post2.setTitle("title1");
        post2.setContent("content1");
        postService.addPost(post2);
        Post foundPost = postService.findById(post.getId());

        PostResponse savedPostAndComment = commentService.addComment(foundPost.getId(), "댓글1");
        PostResponse savedPostAndComment2 = commentService.addComment(foundPost.getId(), "댓글2");
        assertThat(savedPostAndComment2.getComments().size()).isEqualTo(2);
        log.info("savedPostAndComment={}", savedPostAndComment.getComments());
        for (CommentResponse c : savedPostAndComment2.getComments()) {
            log.info("c={}", c.getContent());
        }
//        assertThat(savedPostAndComment.getComments().get(0).getContent()).isEqualTo("댓글1");
    }

    @Test
    void addCommentDomo() {
        Post post = new Post();
        post.setTitle("title1");
        post.setContent("content1");
        postService.addPost(post);

        Post post2 = new Post();
        post2.setTitle("title1");
        post2.setContent("content1");
        postService.addPost(post2);

        log.info("postid={}", post.getId());

        PostResponse postResponse = commentService.addComment1(post.getId(), "content");
        assertThat(postResponse.getComments().get(0).getContent()).isEqualTo("content");
        log.info("content={}", postResponse.getComments().get(0).getContent());
    }

    @Test
    void editComment() {
        Post post = new Post();
        post.setTitle("title1");
        post.setContent("content1");
        postService.addPost(post);

        Post post2 = new Post();
        post2.setTitle("title1");
        post2.setContent("content1");
        postService.addPost(post2);

        PostResponse postResponse = commentService.addComment(post.getId(), "content");
        CommentResponse commentResponse = postResponse.getComments().get(0);
        commentService.editComment(postResponse.getPostId(), commentResponse.getCommentId(), "수정");
        Comment comment = commentRepositoryCustom.find(postResponse.getPostId(), commentResponse.getCommentId(), CommentStatus.ACTIVE);
        assertThat(comment.getContent()).isEqualTo("수정");
    }

    @Test
    void deleteComment() {
        Post post = new Post();
        post.setTitle("title1");
        post.setContent("content1");
        postService.addPost(post);

        Post post2 = new Post();
        post2.setTitle("title1");
        post2.setContent("content1");
        postService.addPost(post2);

        Optional<CommentResponse> commentResponse = commentService.addComment(post.getId(), "content").getComments().stream().findAny();
        CommentResponse commentResponse1 = commentResponse.get();

        Comment comment = commentRepositoryCustom.find(post.getId(), commentResponse1.getCommentId(), CommentStatus.ACTIVE);
        comment.setCommentStatus(CommentStatus.DELETE);

        Comment comment1 = em.find(Comment.class, comment.getId());

    }
}