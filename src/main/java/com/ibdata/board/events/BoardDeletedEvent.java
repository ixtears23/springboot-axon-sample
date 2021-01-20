package com.ibdata.board.events;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class BoardDeletedEvent {

    private String boardId;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private LocalDate dateOfVersion;
}
