package com.ibdata.board.aggregates;

import com.ibdata.board.commands.RegistrationPostCommand;
import com.ibdata.board.events.PostRegisteredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;

@Aggregate
public class BoardPostAggregate {


    // 왜 생성자가 Command 핸들러를 담당하지?
    // 생성자가 Command 핸들러 역할도 할 수 있고
    // 생성이라는 뭔가 라이프사이클의 시작이라는 Command 느낌 떄문에 생성자에 넣은 건가?
    // 꼭 생성이라는 것이 처음이라 말할 수 있는 것인가
    // 그렇다면 생성자가 Command 핸들러 역할 인정
    // 하지만 그것이 아니라면 생성자는 생성자의 역할만 Command Hanlder 인정 못함.
    // 생각해보니 생성자는 객체가 생성되었을 때 무언가 하는 것인데 왜.. 이상하군 안넣어야 될 것 같음

    public BoardPostAggregate() {
    }

    @AggregateIdentifier
    private String postId;

    @CommandHandler
    public BoardPostAggregate(RegistrationPostCommand registrationPostCommand) {
        AggregateLifecycle.apply(new PostRegisteredEvent(registrationPostCommand.getPostId(),
                registrationPostCommand.getTitle(),
                registrationPostCommand.getContents(),
                registrationPostCommand.getWriter(),
                registrationPostCommand.getPassword(),
                LocalDate.now()));
    }

    @EventHandler
    public void on(PostRegisteredEvent postRegisteredEvent) {
        this.postId = postRegisteredEvent.getPostId();
    }
}
