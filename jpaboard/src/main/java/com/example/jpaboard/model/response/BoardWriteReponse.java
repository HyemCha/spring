package com.example.jpaboard.model.response;

import com.example.jpaboard.model.entity.Board;
import com.example.jpaboard.model.enums.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BoardWriteReponse {

    private Long boardNo;
    private String title;
    private String body;
    private BoardStatus boardStatus;

    static public BoardWriteReponse from(Board board) {
        return BoardWriteReponse.builder()
                .boardNo(board.getBoardNo())
                .title(board.getTitle())
                .body(board.getBody())
                .boardStatus(board.getBoardStatus())
                .build();
    }
}
