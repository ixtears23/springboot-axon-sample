package com.ibdata.board.configs;

import com.ibdata.board.interceptor.MyCommandDispatchInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfiguration {

    // TODO 안탐...
    public CommandBus configureCommandBus() {
        SimpleCommandBus commandBus = SimpleCommandBus.builder().build();
        commandBus.registerDispatchInterceptor(new MyCommandDispatchInterceptor());
        return commandBus;
    }
}
