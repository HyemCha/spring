package jpa.crudpractice.domain.repository;

import jakarta.persistence.EntityManager;
import jpa.crudpractice.domain.CommentStatus;
import jpa.crudpractice.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustom {
    private final EntityManager em;

    public Comment find(Long postId, Long commentId, CommentStatus commentStatus) {
        return em.createQuery("select c from Comment c where c.id=:commentId and c.commentStatus=:commentStatus and c.post.id=:postId", Comment.class)
                .setParameter("commentId", commentId)
                .setParameter("postId", postId)
                .setParameter("commentStatus", commentStatus)
                .getSingleResult();
    }


}
