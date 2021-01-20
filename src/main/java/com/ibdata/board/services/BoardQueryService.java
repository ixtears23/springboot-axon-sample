package com.ibdata.board.services;

import com.ibdata.board.dao.mapper.annotation.AnnotationBoardMapper;
import com.ibdata.board.dao.mapper.xml.BoardMapper;
import com.ibdata.board.dto.BoardDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardQueryService {

    private final EventStore eventStore;
    private final BoardMapper boardMapperXML;
    private final AnnotationBoardMapper annotationBoardMapper;

    public BoardQueryService(EventStore eventStore, BoardMapper boardMapperXML, AnnotationBoardMapper annotationBoardMapper) {
        this.eventStore = eventStore;
        this.boardMapperXML = boardMapperXML;
        this.annotationBoardMapper = annotationBoardMapper;
    }

    public List<Object> listEventsForAccount(String boardId) {
        return eventStore.readEvents(boardId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }

    public BoardDTO findBoard(String boardId) {
        return boardMapperXML.findById(boardId);
    }

    public BoardDTO findAnnotationBoard(String boardId) {
        return annotationBoardMapper.findById(boardId);
    }

}
