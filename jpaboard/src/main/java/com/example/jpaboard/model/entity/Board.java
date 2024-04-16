package com.example.jpaboard.model.entity;

import com.example.jpaboard.model.enums.BoardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String title;
    private String body;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

    public boolean isActive() {
        return getBoardStatus() == BoardStatus.ACTIVE;
    }
}
