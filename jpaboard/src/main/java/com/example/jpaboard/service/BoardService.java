package com.example.jpaboard.service;

import com.example.jpaboard.model.entity.Board;
import com.example.jpaboard.model.enums.BoardStatus;
import com.example.jpaboard.model.response.BoardWriteReponse;
import com.example.jpaboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository repository;

    public List<BoardWriteReponse> searchBoardList(int page, int pageSize) {
        return repository.findAllByBoardStatus(BoardStatus.ACTIVE, PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardBo")))
                .map(BoardWriteReponse::from)
                .toList();
    }

    public List<BoardWriteReponse> searchBoardList2(Long boardNo) {
        return (boardNo == 0L ?
                repository.findTop10ByBoardStatusOrderByBoardNoDesc(BoardStatus.ACTIVE)
                : repository.findTopBoardStatusAndBoardNoLessThanOrderByBoardNoDesc(BoardStatus.ACTIVE, boardNo)
                ).stream().map(BoardWriteReponse::from).collect(Collectors.toList());
    }

    public BoardWriteReponse getBoard(Long boardNo) {
        return repository.findById(boardNo)
                .filter(Board::isActive)
                .map(BoardWriteReponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글"));
    }

    public String writeBoard(String title, String body) {
        Board board = new Board();
        board.setTitle(title);
        board.setBody(body);
        board.setBoardStatus(BoardStatus.ACTIVE);

        BoardWriteReponse.from(repository.save(board));

        return "ok";
    }

    public BoardWriteReponse editBoard(Long boardNo, String body) {
        return repository.findById(boardNo)
                .filter(Board::isActive)
                .map(board -> {
                            board.setBody(body);
                            return board;
                        }
                ).map(BoardWriteReponse::from)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시물"));
    }
}
