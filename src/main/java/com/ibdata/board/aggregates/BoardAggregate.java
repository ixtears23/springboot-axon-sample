package com.ibdata.board.aggregates;

import com.ibdata.board.commands.EditBoardCommand;
import com.ibdata.board.commands.RegistBoardCommand;
import com.ibdata.board.dao.mapper.xml.BoardMapper;
import com.ibdata.board.events.BoardEditedEvent;
import com.ibdata.board.events.BoardRegisteredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Aggregate
public class BoardAggregate {

    private static final Logger logger = LoggerFactory.getLogger(BoardAggregate.class);

    public BoardAggregate() {
    }

    @AggregateIdentifier
    private String boardId;

    @Autowired
    private ApplicationContext applicationContext;
    private BoardMapper boardMapper;

    @Autowired
    public BoardAggregate(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @CommandHandler
    public BoardAggregate(RegistBoardCommand registBoardCommand) {
        AggregateLifecycle.apply(new BoardRegisteredEvent(registBoardCommand.getBoardId(),
                registBoardCommand.getTitle(),
                registBoardCommand.getContents(),
                registBoardCommand.getWriter(),
                registBoardCommand.getPassword(),
                LocalDate.now()));

        /*BoardMapper mapper = applicationContext.getBean(BoardMapper.class);
        if (mapper == null) logger.debug("ApplicationContext에서 불러온 BoardMapper는 Null이다.");
        if (boardMapper == null) logger.debug("@Autowired로 생성자 주입한 BoardMapper는 Null이다.");
        if (mapper != null && boardMapper != null) logger.debug("Mapper모두 Null이 아니다.");*/
    }

    @EventSourcingHandler
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

    @EventSourcingHandler
    public void on(BoardEditedEvent boardEditedEvent) {
        this.boardId = boardEditedEvent.getBoardId();
    }
}
