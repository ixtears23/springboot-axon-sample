package com.ibdata.board.configs;

import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class SnapshotTriggerConfiguration {

/*
    @Bean
    public SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

    @Bean
    public SpringAggregateSnapshotter springAggregateSnapshotter(ParameterResolverFactory parameterResolverFactory, EventStore eventStore, TransactionManager transactionManager) {
        Executor executor = Executors.newFixedThreadPool(10);
        return new SpringAggregateSnapshotter(eventStore, parameterResolverFactory, executor, transactionManager);
    }
*/

    /*
        TODO 문제 있음.<br>
         snapshot_event_entry Table에 threshold 숫자만큼 Event 발생 시 데이터 생성됨.<br>
         snapshot data를 생성하고 난 뒤로는 Event 발생 시 계속해서오류 발생
     */
    @Bean(name = "boardSnapshotTrigger")
    public SnapshotTriggerDefinition boardSnapshotTrigger(Snapshotter snapshotter) {

        return new EventCountSnapshotTriggerDefinition(snapshotter, 8);
    }
}
