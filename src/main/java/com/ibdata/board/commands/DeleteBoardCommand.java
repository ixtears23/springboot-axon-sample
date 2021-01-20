package com.ibdata.board.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class DeleteBoardCommand {

    @TargetAggregateIdentifier
    private String boardId;
    private String password;
}
