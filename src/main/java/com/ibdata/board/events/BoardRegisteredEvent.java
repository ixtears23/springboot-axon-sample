package com.ibdata.board.events;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@AllArgsConstructor
@Value
public class BoardRegisteredEvent {

    private String boardId;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private LocalDate creationDate;
}
