package jpa.crudpractice.domain.entity;

import jakarta.persistence.*;
import jpa.crudpractice.domain.CommentStatus;
import jpa.crudpractice.domain.PostStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    @BatchSize(size = 10)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    public Post addComment(String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(this);
        comment.setCommentStatus(CommentStatus.ACTIVE);
        this.comments.add(comment);
        return this;
    }

    public boolean isActive() {
        return this.getPostStatus() == PostStatus.ACTIVE;
    }

    public void doClose() {
        this.setPostStatus(PostStatus.DELETE);
    }

    public Post addComment1(Comment comment) {
        comment.setPost(this);
        comment.setCommentStatus(CommentStatus.ACTIVE);
        this.comments.add(comment);
        return this;
    }

    public Post() {

    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
