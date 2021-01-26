package com.ibdata.board.aggregates;

import com.ibdata.board.commands.EditBoardCommand;
import com.ibdata.board.commands.RegistBoardCommand;
import com.ibdata.board.dao.mapper.xml.BoardMapper;
import com.ibdata.board.events.BoardEditedEvent;
import com.ibdata.board.events.BoardRegisteredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CommandHandlerInterceptor;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Aggregate
public class BoardAggregate {

    private static final Logger logger = LoggerFactory.getLogger(BoardAggregate.class);
    private static int callCount = 0;

    public BoardAggregate() {
    }

    @AggregateIdentifier
    private String boardId;

    @CommandHandler
    public BoardAggregate(RegistBoardCommand registBoardCommand, BoardMapper boardMapper) {
        AggregateLifecycle.apply(new BoardRegisteredEvent(registBoardCommand.getBoardId(),
                registBoardCommand.getTitle(),
                registBoardCommand.getContents(),
                registBoardCommand.getWriter(),
                registBoardCommand.getPassword(),
                LocalDate.now()));
    }

    @EventSourcingHandler
    public void on(BoardRegisteredEvent boardRegisteredEvent) {
        this.boardId = boardRegisteredEvent.getBoardId();
    }

    @CommandHandler
    public void handle(EditBoardCommand editBoardCommand, BoardMapper boardMapper) {
        AggregateLifecycle.apply(new BoardEditedEvent(
                editBoardCommand.getBoardId(),
                editBoardCommand.getTitle(),
                editBoardCommand.getContents(),
                editBoardCommand.getWriter(),
                editBoardCommand.getPassword(),
                LocalDate.now()));
    }

    @EventSourcingHandler
    public void on(BoardEditedEvent boardEditedEvent) {
        this.boardId = boardEditedEvent.getBoardId();
    }

    // TODO 안탐...
    @CommandHandlerInterceptor
    public void intercept(RegistBoardCommand command, InterceptorChain interceptorChain) throws Exception {
        logger.debug("==============@CommandHandlerInterceptor==============");
        if (this.callCount % 2 == 0) {
            interceptorChain.proceed();
        }
    }
}
