package com.ibdata.board.services;

import com.ibdata.board.commands.EditBoardCommand;
import com.ibdata.board.commands.RegistBoardCommand;
import com.ibdata.board.dto.BoardEditDTO;
import com.ibdata.board.dto.BoardRegistDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BoardCommandService {

    private CommandGateway commandGateway;

    public BoardCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture registBoard(BoardRegistDTO boardRegistDTO) {
        return commandGateway.send(
                new RegistBoardCommand(
                        UUID.randomUUID().toString(),
                        boardRegistDTO.getTitle(),
                        boardRegistDTO.getContents(),
                        boardRegistDTO.getWriter(),
                        boardRegistDTO.getPassword()));
    }

    public CompletableFuture editBoard(BoardEditDTO boardEditDTO) {
        return commandGateway.send(
                new EditBoardCommand(
                        boardEditDTO.getBoardId(),
                        boardEditDTO.getTitle(),
                        boardEditDTO.getContents(),
                        boardEditDTO.getPassword(),
                        boardEditDTO.getWriter()));
    }
}
