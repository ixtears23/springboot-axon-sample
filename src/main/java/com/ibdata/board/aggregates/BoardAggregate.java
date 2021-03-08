package com.ibdata.board.aggregates;

import com.ibdata.board.aggregates.member.CommentMember;
import com.ibdata.board.commands.ChangeBoardCommand;
import com.ibdata.board.commands.EditBoardCommand;
import com.ibdata.board.commands.RegistBoardCommand;
import com.ibdata.board.dao.mapper.xml.BoardMapper;
import com.ibdata.board.events.BoardChangedEvent;
import com.ibdata.board.events.BoardEditedEvent;
import com.ibdata.board.events.BoardRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.CommandHandlerInterceptor;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aggregate
//        (snapshotTriggerDefinition = "boardSnapshotTrigger")
public class BoardAggregate {

    private static final Logger logger = LoggerFactory.getLogger(BoardAggregate.class);
    private static int callCount = 0;
    private static int amount = 0;

    public BoardAggregate() {
    }

    @AggregateIdentifier
    private String boardId;

//    @AggregateMember
    private List<CommentMember> commentMemberList = new ArrayList<>();

    @CommandHandler
    public BoardAggregate(RegistBoardCommand registBoardCommand, BoardMapper boardMapper) {
        log.debug("handling RegistBoardCommand {}", registBoardCommand);
        AggregateLifecycle.apply(new BoardRegisteredEvent(registBoardCommand.getBoardId(),
                registBoardCommand.getTitle(),
                registBoardCommand.getContents(),
                registBoardCommand.getWriter(),
                registBoardCommand.getPassword(),
                LocalDate.now(),
                registBoardCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(BoardRegisteredEvent boardRegisteredEvent) {
        log.debug("applying BoardRegisteredEvent {}", boardRegisteredEvent);
        this.boardId = boardRegisteredEvent.getBoardId();
        this.amount = boardRegisteredEvent.getAmount();
        log.debug("REGISTRATION!!! amount ::: {}", this.amount);
    }

    @CommandHandler
    public void handle(EditBoardCommand editBoardCommand, BoardMapper boardMapper) {
        log.debug("handling EditBoardCommand {}", editBoardCommand);
        AggregateLifecycle.apply(new BoardEditedEvent(
                editBoardCommand.getBoardId(),
                editBoardCommand.getTitle(),
                editBoardCommand.getContents(),
                editBoardCommand.getWriter(),
                editBoardCommand.getPassword(),
                LocalDate.now(),
                editBoardCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(BoardEditedEvent boardEditedEvent) {
        log.debug("applying BoardEditedEvent {}", boardEditedEvent);
        this.boardId = boardEditedEvent.getBoardId();
        this.amount = this.amount += boardEditedEvent.getAmount();
        log.debug("PLUS!!! amount ::: {}", this.amount);
    }

    @CommandHandler
    public void handle(ChangeBoardCommand changeBoardCommand, BoardMapper boardMapper) {
        log.debug("handling ChangeBoardCommand {}", changeBoardCommand);
        AggregateLifecycle.apply(new BoardChangedEvent(
                changeBoardCommand.getBoardId(),
                changeBoardCommand.getTitle(),
                changeBoardCommand.getContents(),
                changeBoardCommand.getWriter(),
                changeBoardCommand.getPassword(),
                LocalDate.now(),
                changeBoardCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(BoardChangedEvent boardChangedEvent) {
        log.debug("applying BoardChangedEvent {}", boardChangedEvent);
        this.boardId = boardChangedEvent.getBoardId();
        this.amount = this.amount -= boardChangedEvent.getAmount();
        log.debug("MINUS!!! amount ::: {}", this.amount);
    }

    // TODO `@CommandHandlerInterceptor` 안탐...
    @CommandHandlerInterceptor
    public void intercept(RegistBoardCommand command, InterceptorChain interceptorChain) throws Exception {
        logger.debug("==============@CommandHandlerInterceptor==============");
        if (this.callCount % 2 == 0) {
            interceptorChain.proceed();
        }
    }
}
