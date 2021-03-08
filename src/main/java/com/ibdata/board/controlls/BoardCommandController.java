package com.ibdata.board.controlls;

import com.ibdata.board.commands.EditBoardCommand;
import com.ibdata.board.dto.BoardChangeDTO;
import com.ibdata.board.dto.BoardEditDTO;
import com.ibdata.board.dto.BoardRegistDTO;
import com.ibdata.board.services.BoardCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/board")
public class BoardCommandController {

    private BoardCommandService boardCommandService;

    public BoardCommandController(BoardCommandService boardCommandService) {
        this.boardCommandService = boardCommandService;
    }

    @PostMapping("/regist")
    public CompletableFuture regist(@RequestBody BoardRegistDTO boardRegistDTO) {
        return boardCommandService.registBoard(boardRegistDTO);
    }


    @PostMapping("/edit")
    public CompletableFuture edit(@RequestBody BoardEditDTO boardEditDTO) {
        return boardCommandService.editBoard(boardEditDTO);
    }

    @PostMapping("/change")
    public CompletableFuture change(@RequestBody BoardChangeDTO boardChangeDTO) {
        return boardCommandService.changeBoard(boardChangeDTO);
    }
}
