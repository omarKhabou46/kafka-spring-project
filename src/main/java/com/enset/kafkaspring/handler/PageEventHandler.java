package com.enset.kafkaspring.handler;

import com.enset.kafkaspring.events.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Component
public class PageEventHandler {

    @Bean
    public Consumer<PageEvent> pageEventConsumer() {
        return (input) ->  {
            System.out.println("****************************");
            System.out.println(input);
            System.out.println("****************************");
        };
    }

    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> {
            return new PageEvent(Math.random() < 0.5? "P1": "P2", Math.random() < 0.5? "U1": "U2",
                    LocalDate.now(),
                    new Random().nextLong(10000));
        };
    }

}
