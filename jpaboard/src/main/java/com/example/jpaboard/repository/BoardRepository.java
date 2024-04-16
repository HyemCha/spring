package com.example.jpaboard.repository;

import com.example.jpaboard.model.entity.Board;
import com.example.jpaboard.model.enums.BoardStatus;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByBoardStatus(BoardStatus boardStatus, Pageable pageable);

    List<Board> findTop10ByBoardStatusOrderByBoardNoDesc(BoardStatus boardStatus);

    List<Board> findTopBoardStatusAndBoardNoLessThanOrderByBoardNoDesc(BoardStatus boardStatus, Long id);

}
