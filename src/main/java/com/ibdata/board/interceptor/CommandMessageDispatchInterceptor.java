package com.ibdata.board.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

@Slf4j
public class CommandMessageDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        log.debug("==========MyCommandDispatchInterceptor:CommandMessage");
        return message;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        log.debug("==========MyCommandDispatchInterceptor:BiFunction");
        return (integer, commandMessage) -> {
            return commandMessage;
        };
    }
}
