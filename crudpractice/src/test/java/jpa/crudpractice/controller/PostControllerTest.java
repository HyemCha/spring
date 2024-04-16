package jpa.crudpractice.controller;

import jpa.crudpractice.domain.entity.Post;
import jpa.crudpractice.domain.repository.PostRepository;
import jpa.crudpractice.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class PostControllerTest {

    @Autowired private PostRepository postRepository;
    @Autowired private PostService postService;

    @Test
    void addPost() {
        Post post = new Post("title1", "content1");

        postService.addPost(post);
        Optional<Post> savedPost = postRepository.findById(post.getId());

        assertThat(savedPost.get().getTitle()).isEqualTo(post.getTitle());
    }

    @Test
    void deletePost() {
        Post post = new Post("title1", "content1");

        postService.addPost(post);
        Optional<Post> savedPost = postRepository.findById(post.getId());

        postRepository.deleteById(savedPost.get().getId());

        assertThatThrownBy(() -> postRepository.findById(post.getId()))
                .isInstanceOf(NoSuchFieldException.class);
    }
}