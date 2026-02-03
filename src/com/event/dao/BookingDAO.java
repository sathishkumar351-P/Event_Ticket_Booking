package com.event.dao;

import java.sql.*;

import com.event.bean.Booking;

public class BookingDAO {

    public int generateBookingID(Connection con) throws Exception {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT NVL(MAX(BOOKING_ID),70000)+1 FROM BOOKING_TBL");
        rs.next();
        return rs.getInt(1);
    }

    public boolean recordBooking(Booking b, Connection con) throws Exception {
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO BOOKING_TBL VALUES (?,?,?,?,?)");
        ps.setInt(1, b.getBookingID());
        ps.setString(2, b.getEventID());
        ps.setString(3, b.getCustomerName());
        ps.setInt(4, b.getTicketsBooked());
        ps.setDate(5, new java.sql.Date(b.getBookingDate().getTime()));
        return ps.executeUpdate() > 0;
    }

    public boolean removeBooking(int bookingID, Connection con) throws Exception {
        PreparedStatement ps = con.prepareStatement(
                "DELETE FROM BOOKING_TBL WHERE BOOKING_ID=?");
        ps.setInt(1, bookingID);
        return ps.executeUpdate() > 0;
    }
}
