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

    // TODO 해당 메서드는 동작하지 않음. 왜 동작하지 않는지 해결해야 함.
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        log.debug("==========MyCommandDispatchInterceptor:BiFunction");
        return (integer, commandMessage) -> {
            return commandMessage;
        };
    }
}
