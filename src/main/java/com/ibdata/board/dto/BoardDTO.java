package com.ibdata.board.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BoardDTO {

    private String boardId;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private LocalDateTime creationDate;
    private LocalDateTime dateOfVersion;
}
