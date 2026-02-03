package com.event.util;

public class ActiveBookingException extends Exception {

    public ActiveBookingException() {
        super("Active bookings exist, cannot remove event");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
