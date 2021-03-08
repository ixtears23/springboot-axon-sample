package com.ibdata.board.dto;

import lombok.Value;

@Value
public class BoardChangeDTO {

    private String boardId;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private int amount;
}
