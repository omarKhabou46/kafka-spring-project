package com.enset.kafkaspring.events;

import java.time.LocalDate;

public record PageEvent(String id, String user, LocalDate date, long duration) {
}
