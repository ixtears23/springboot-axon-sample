package com.ibdata.board.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class DeletePostCommand {

    @TargetAggregateIdentifier
    private String postId;
    private String password;
}
