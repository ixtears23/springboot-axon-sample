package com.ibdata.board.dto;

import lombok.Value;

@Value
public class BoardRegistDTO {

    private String title;
    private String contents;
    private String writer;
    private String password;
}
