package jpa.crudpractice.service;

import jpa.crudpractice.domain.CommentStatus;
import jpa.crudpractice.domain.entity.Comment;
import jpa.crudpractice.domain.repository.CommentRepository;
import jpa.crudpractice.domain.repository.CommentRepositoryCustom;
import jpa.crudpractice.domain.repository.PostRepository;
import jpa.crudpractice.domain.repository.PostRepositoryCustom;
import jpa.crudpractice.domain.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static jpa.crudpractice.domain.CommentStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentRepositoryCustom commentRepositoryCustom;
    private final PostRepositoryCustom postRepositoryCustom;
    private final PostRepository postRepository;

    public PostResponse addComment(Long postId, String content) {
        return postRepositoryCustom.findPost(postId)
                .map(post -> post.addComment(content)) // 포스트에 댓글 달기
                .map(postRepository::save) // 댓글과 함께 포스트 저장하고 포스트 반환
                .map(PostResponse::from) // 포스트가 저장되고 반환된 객체로 응답 객체 생성해서 반환
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다"));
    }
    public PostResponse addComment1(Long postId, String content) {
        return postRepositoryCustom.findPost1(postId)
                .map(post -> {
                    log.info("content={}", content);
                    Comment comment = new Comment();
                    comment.setContent(content);
                    commentRepository.save(comment);
                    return post.addComment1(comment);
                }) // 포스트에 댓글 달기
                .map(postRepository::save) // 댓글과 함께 포스트 저장하고 포스트 반환
                .map(PostResponse::from) // 포스트가 저장되고 반환된 객체로 응답 객체 생성해서 반환
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물입니다"));
    }

    public void editComment(Long postId, Long commentId, String commentContent) {
        Comment comment = commentRepositoryCustom.find(postId, commentId, ACTIVE);
        comment.setContent(commentContent);
    }

    public void deleteComment(Long postId, Long commentId) {
        Comment comment = commentRepositoryCustom.find(postId, commentId, ACTIVE);
        comment.setCommentStatus(DELETE);
    }
}
