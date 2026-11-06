package com.enset.kafkaspring.handler;

import com.enset.kafkaspring.events.PageEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
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

    @Bean
    public Function<KStream<String, PageEvent>, KStream<String, Long>> kStreamFunction() {
        return (input) ->
                input
                        .filter((k, v) -> v.duration() > 100)
                        .map((k, v) -> new KeyValue<>(v.id(), v.duration()))
                        .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                        .count()
                        .toStream();
    }

}
