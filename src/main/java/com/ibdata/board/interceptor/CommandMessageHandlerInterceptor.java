package com.ibdata.board.interceptor;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;

public class CommandMessageHandlerInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {
    @Override
    public Object handle(UnitOfWork<? extends CommandMessage<?>> unitOfWork, InterceptorChain interceptorChain) throws Exception {
//        return interceptorChain.proceed();
        return null;
    }
}
