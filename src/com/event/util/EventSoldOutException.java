package com.event.util;

public class EventSoldOutException extends Exception {
    public EventSoldOutException() {
        super("Event sold out");
    }

    public EventSoldOutException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Event sold out: " + getMessage();
    }
}
