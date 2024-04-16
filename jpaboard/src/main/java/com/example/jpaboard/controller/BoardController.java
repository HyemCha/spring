package com.example.jpaboard.controller;

import com.example.jpaboard.model.request.BoardEditReqeust;
import com.example.jpaboard.model.request.BoardWriteRequest;
import com.example.jpaboard.model.response.BoardWriteReponse;
import com.example.jpaboard.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/boarList")
    public List<BoardWriteReponse> searchBoardList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
    ) {
        return service.searchBoardList(page, pageSize);
    }

    @GetMapping("/boarList2")
    public List<BoardWriteReponse> searchBoardList2(
            @RequestParam Long boardNo
    ) {
        return service.searchBoardList2(boardNo);
    }

    @GetMapping("/board")
    public BoardWriteReponse searchBoard(
            @RequestParam("boardNo") Long boardNo
    ) {
        return service.getBoard(boardNo);
    }

    @PutMapping("/board")
    public BoardWriteReponse editBoard(
            @RequestBody BoardEditReqeust reqeust
            ) {
        return service.editBoard(reqeust.getBoardBo(), reqeust.getBody());
    }

    @PostMapping("/board")
    public String writeBoard(
            @RequestBody BoardWriteRequest request
    ) {
        return service.writeBoard(request.getTitle(), request.getBody());
    }
}
