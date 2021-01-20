package com.ibdata.board.aggregates;

import com.ibdata.board.commands.EditBoardCommand;
import com.ibdata.board.commands.RegistBoardCommand;
import com.ibdata.board.events.BoardEditedEvent;
import com.ibdata.board.events.BoardRegisteredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;

@Aggregate
public class BoardAggregate {

    public BoardAggregate() {
    }

    @AggregateIdentifier
    private String boardId;

    @CommandHandler
    public BoardAggregate(RegistBoardCommand registBoardCommand) {
        AggregateLifecycle.apply(new BoardRegisteredEvent(registBoardCommand.getBoardId(),
                registBoardCommand.getTitle(),
                registBoardCommand.getContents(),
                registBoardCommand.getWriter(),
                registBoardCommand.getPassword(),
                LocalDate.now()));
    }

    @EventHandler
    public void on(BoardRegisteredEvent boardRegisteredEvent) {
        this.boardId = boardRegisteredEvent.getBoardId();
    }

    @CommandHandler
    public void handle(EditBoardCommand editBoardCommand) {
        AggregateLifecycle.apply(new BoardEditedEvent(
                editBoardCommand.getBoardId(),
                editBoardCommand.getTitle(),
                editBoardCommand.getContents(),
                editBoardCommand.getWriter(),
                editBoardCommand.getPassword(),
                LocalDate.now()));
    }

    @EventHandler
    public void on(BoardEditedEvent boardEditedEvent) {
        this.boardId = boardEditedEvent.getBoardId();
    }
}