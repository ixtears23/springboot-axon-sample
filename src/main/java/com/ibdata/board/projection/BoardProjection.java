package com.ibdata.board.projection;

import com.ibdata.board.dao.mapper.xml.BoardMapper;
import com.ibdata.board.dto.BoardDTO;
import com.ibdata.board.events.BoardEditedEvent;
import com.ibdata.board.events.BoardRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BoardProjection {

    private final BoardMapper boardMapper;

//    public BoardProjection(BoardMapper boardMapper) {
//        this.boardMapper = boardMapper;
//    }

    @EventHandler
    public void on(BoardRegisteredEvent boardRegisteredEvent) {
        boardMapper.insertBoard(new BoardDTO(
                boardRegisteredEvent.getBoardId(),
                boardRegisteredEvent.getTitle(),
                boardRegisteredEvent.getContents(),
                boardRegisteredEvent.getWriter(),
                boardRegisteredEvent.getPassword(),
                LocalDateTime.now(),
                LocalDateTime.now()));
    }

    @EventHandler
    public void on(BoardEditedEvent boardEditedEvent) {
        boardMapper.updateBoard(new BoardDTO(
                boardEditedEvent.getBoardId(),
                boardEditedEvent.getTitle(),
                boardEditedEvent.getContents(),
                boardEditedEvent.getWriter(),
                boardEditedEvent.getPassword(),
                null,
                LocalDateTime.now()));
    }
}
