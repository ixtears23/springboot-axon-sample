package com.ibdata.board.services;

import com.ibdata.board.commands.RegistrationPostCommand;
import com.ibdata.board.dto.PostCommandDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PostCommandService {

    private CommandGateway commandGateway;

    public PostCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture registrationPost(PostCommandDTO postCommandDTO) {
        return commandGateway.send(
                new RegistrationPostCommand(
                        UUID.randomUUID().toString(),
                        postCommandDTO.getTitle(),
                        postCommandDTO.getContents(),
                        postCommandDTO.getWriter(),
                        postCommandDTO.getPassword()));
    }
}
