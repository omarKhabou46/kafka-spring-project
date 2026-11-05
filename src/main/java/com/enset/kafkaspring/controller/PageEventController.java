package com.enset.kafkaspring.controller;

import com.enset.kafkaspring.events.PageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class PageEventController {

    private final StreamBridge streamBridge;

    @GetMapping("/publish")
    public PageEvent publish(String name, String topic) {
        PageEvent pageEvent = new PageEvent(name, Math.random() < 0.5? "U1": "U2",
                LocalDate.now(),
                new Random().nextLong() * 10000);
        streamBridge.send(topic, pageEvent);
        return pageEvent;

    }

}
