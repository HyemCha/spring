package jpa.crudpractice.domain.repository;

import jakarta.persistence.EntityManager;
import jpa.crudpractice.domain.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PostRepositoryCustom {

    private final EntityManager em;

    public Optional<Post> findPost(Long postId) {
        Post post = em.createQuery("select p from Post p left join p.comments where p.id=:postId", Post.class)
                .setParameter("postId", postId)
                .getSingleResult();
        return Optional.of(post);
    }
    public Optional<Post> findPost1(Long postId) {
        Post post = em.createQuery("select p from Post p join fetch p.comments where p.id=:postId", Post.class)
                .setParameter("postId", postId)
                .getSingleResult();
        return Optional.of(post);
    }
}
