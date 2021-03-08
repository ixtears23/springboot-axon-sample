package com.ibdata.board.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class ChangeBoardCommand {

    @TargetAggregateIdentifier
    private String boardId;
    private String title;
    private String contents;
    private String password;
    private String writer;
    private int amount;
}
