package com.ibdata.board.configs;

import com.ibdata.board.interceptor.CommandMessageDispatchInterceptor;
import com.ibdata.board.interceptor.CommandMessageHandlerInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommandBusConfiguration {

    @Bean
    public CommandBus configureDispatchCommandBus() {
        SimpleCommandBus commandBus = SimpleCommandBus.builder().build();
        commandBus.registerDispatchInterceptor(new CommandMessageDispatchInterceptor());
        commandBus.registerHandlerInterceptor(new CommandMessageHandlerInterceptor());
        return commandBus;
    }
}
