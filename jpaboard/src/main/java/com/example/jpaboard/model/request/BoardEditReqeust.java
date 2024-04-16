package com.example.jpaboard.model.request;

import lombok.Data;

@Data
public class BoardEditReqeust {
    private Long boardBo;
    private String title;
    private String body;
}
