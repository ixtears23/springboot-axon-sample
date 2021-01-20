package com.ibdata.board.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class PostEventService {

    private CommandGateway commandGateway;
}
