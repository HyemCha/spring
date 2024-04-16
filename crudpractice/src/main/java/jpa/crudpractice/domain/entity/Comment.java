package jpa.crudpractice.domain.entity;

import jakarta.persistence.*;
import jpa.crudpractice.domain.CommentStatus;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private String content;
    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;
}
