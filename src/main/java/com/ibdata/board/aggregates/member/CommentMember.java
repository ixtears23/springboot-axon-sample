package com.ibdata.board.aggregates.member;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.EntityId;

// TODO AggregateMember를 만들어보려다가 멈춤.
public class CommentMember {

    @EntityId
    private String commentId;

    public CommentMember(String commentId) {
        this.commentId = commentId;
    }


    @CommandHandler
    public void handle() {

    }



}
