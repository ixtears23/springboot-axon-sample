package com.ibdata.board.projection;

import com.ibdata.board.dao.mapper.xml.BoardMapper;
import com.ibdata.board.dto.BoardDTO;
import com.ibdata.board.events.BoardEditedEvent;
import com.ibdata.board.events.BoardRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.MessageHandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;

/*
    Projection은 언제 사용해야 하나..
 */
@Component
@RequiredArgsConstructor
public class BoardProjection {

    private final BoardMapper boardMapper;
    private static final Logger log = LoggerFactory.getLogger(BoardProjection.class);

    /*
        성공!!!
        @EventSourcingHandler
        -> @MessageHandlerInterceptor
        -> @EventHandler
     */
    @MessageHandlerInterceptor
    public void interceptor(Message<?> message) {
        log.debug("============BoardProjection.interceptor()============");
        log.debug("================identifier================");
        log.debug(message.getIdentifier());
        log.debug("================payload================");
//        log.debug(message.getPayload());
        log.debug("================payloadType================");
//        Class<String> payloadType = message.getPayloadType();
//        log.debug(payloadType.getName());

        log.debug("================decalreFiedls================");
//        Field[] declaredFields = payloadType.getDeclaredFields();
//        Arrays.stream(declaredFields).forEach(field -> log.debug(field.getName()));

        log.debug("================simpleName================");
//        log.debug(payloadType.getSimpleName());
    }

    @EventHandler
    public void on(BoardRegisteredEvent boardRegisteredEvent) {
        log.debug("============BoardProjection:BoardRegisteredEvent============");
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
        log.debug("============BoardProjection:BoardEditedEvent============");
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
