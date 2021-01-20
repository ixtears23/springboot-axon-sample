package com.ibdata.board.services;

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

    public BoardQueryService(EventStore eventStore, BoardMapper boardMapperXML) {
        this.eventStore = eventStore;
        this.boardMapperXML = boardMapperXML;
    }

    public List<Object> listEventsForAccount(String postId) {
        return eventStore.readEvents(postId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
    }

    public BoardDTO findBoard(String postId) {
        return boardMapperXML.findById(postId);
    }

}
