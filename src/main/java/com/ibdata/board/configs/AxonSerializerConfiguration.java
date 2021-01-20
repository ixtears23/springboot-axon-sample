package com.ibdata.board.configs;

import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AxonSerializerConfiguration {

    @Bean
    @Qualifier("eventSerializer")
    public JacksonSerializer eventSerializer() {
        return JacksonSerializer.builder().build();
    }

    @Bean
    @Qualifier("messageSerializer")
    public JacksonSerializer messageSerializer() {
        return JacksonSerializer.builder().build();
    }
}
