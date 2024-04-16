package jpa.crudpractice.controller;

import jpa.crudpractice.domain.request.CommentDeleteRequest;
import jpa.crudpractice.domain.request.CommentEditRequest;
import jpa.crudpractice.domain.request.CommentPostRequest;
import jpa.crudpractice.domain.response.PostResponse;
import jpa.crudpractice.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public PostResponse addComment(@PathVariable Long postId, @RequestBody CommentPostRequest commentPostRequest) {
        return commentService.addComment(commentPostRequest.getPostId(), commentPostRequest.getContent());
    }

    @PutMapping
    public String editComment(@RequestBody CommentEditRequest commentEditRequest) {
        commentService.editComment(commentEditRequest.getPostId(), commentEditRequest.getCommentId(), commentEditRequest.getCommentContent());
        return "OK";
    }

    @DeleteMapping
    public String deleteComment(@RequestBody CommentDeleteRequest commentDeleteRequest) {

        return "OK";
    }
}
