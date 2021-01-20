package com.ibdata.board.controlls;

import com.ibdata.board.dto.BoardDTO;
import com.ibdata.board.services.BoardQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    public BoardQueryController(BoardQueryService boardQueryService) {
        this.boardQueryService = boardQueryService;
    }

    @GetMapping("/{boardId}/events")
    public List<Object> listEventsForBoard(@PathVariable(value = "boardId") String boardId) {
        return boardQueryService.listEventsForAccount(boardId);
    }

    @GetMapping("/{boardId}/list")
    public BoardDTO listForBoard(@PathVariable(value = "boardId") String boardId) {
        return boardQueryService.findBoard(boardId);
    }

}
