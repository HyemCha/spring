package jpa.crudpractice.domain.repository;

import jpa.crudpractice.domain.PostStatus;
import jpa.crudpractice.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByPostStatus(PostStatus postStatus, Pageable pageable);

    // 커서 기반
    List<Post> findTop10ByPostStatusOrderByIdDesc(PostStatus postStatus);

    List<Post> findTop10ByPostStatusAndIdLessThanOrderByIdDesc(PostStatus postStatus, Long Id);
}
