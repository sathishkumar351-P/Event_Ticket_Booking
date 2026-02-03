package com.event.service;

import java.sql.Connection;
import java.util.Date;

import com.event.bean.Booking;
import com.event.bean.Event;
import com.event.dao.BookingDAO;
import com.event.dao.EventDAO;
import com.event.util.*;

public class BookingService {

    private EventDAO eventDAO = new EventDAO();
    private BookingDAO bookingDAO = new BookingDAO();

    public boolean bookTicket(String eventID, String customerName, int tickets)
            throws Exception {

        
        if (eventID == null || eventID.isEmpty() ||
            customerName == null || customerName.isEmpty() ||
            tickets <= 0)
            throw new ValidationException("Invalid input");

        
        Event e = eventDAO.findEvent(eventID);
        if (e == null) return false;

        
        if (tickets > e.getAvailableSeats())
            throw new EventSoldOutException("Requested " + tickets +
                                            " tickets but only " + e.getAvailableSeats() + " available");

        
        Connection con = DBUtil.getDBConnection();
        con.setAutoCommit(false); 

        try {
           
            int newSeats = e.getAvailableSeats() - tickets;
            eventDAO.updateAvailableSeats(eventID, newSeats, con);

           
            int bookingID = bookingDAO.generateBookingID(con);
            Booking b = new Booking();
            b.setBookingID(bookingID);
            b.setEventID(eventID);
            b.setCustomerName(customerName);
            b.setTicketsBooked(tickets);
            b.setBookingDate(new Date());

            bookingDAO.recordBooking(b, con);

           
            con.commit();
            return true;

        } catch (Exception ex) {
            con.rollback();
            throw ex;  
        } finally {
            con.close();
        }
    }

    public boolean cancelBooking(int bookingID) throws Exception {
        if (bookingID <= 0)
            throw new ValidationException("Invalid booking ID");

        Connection con = DBUtil.getDBConnection();
        con.setAutoCommit(false);

        try {
            boolean removed = bookingDAO.removeBooking(bookingID, con);
            con.commit();
            return removed;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }
}
