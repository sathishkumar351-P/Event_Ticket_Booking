package com.event.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.event.bean.Event;
import com.event.util.DBUtil;

public class EventDAO {

	public Event findEvent(String eventID) throws Exception {
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * FROM EVENT_TBL WHERE EVENT_ID=?");
		ps.setString(1, eventID);

		ResultSet rs = ps.executeQuery();
		Event e = null;

		if (rs.next()) {
			e = new Event();
			e.setEventID(rs.getString(1));
			e.setTitle(rs.getString(2));
			e.setVenue(rs.getString(3));
			e.setTotalSeats(rs.getInt(4));
			e.setAvailableSeats(rs.getInt(5));
			e.setEventDate(rs.getDate(6));
		}
		con.close();
		return e;
	}

	public List<Event> viewAllEvents() throws Exception {
		List<Event> list = new ArrayList<>();
		Connection con = DBUtil.getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM EVENT_TBL");

		while (rs.next()) {
			Event e = new Event();
			e.setEventID(rs.getString(1));
			e.setTitle(rs.getString(2));
			e.setVenue(rs.getString(3));
			e.setTotalSeats(rs.getInt(4));
			e.setAvailableSeats(rs.getInt(5));
			e.setEventDate(rs.getDate(6));
			list.add(e);
		}
		con.close();
		return list;
	}

	public boolean insertEvent(Event e) throws Exception {
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps = con.prepareStatement("INSERT INTO EVENT_TBL VALUES (?,?,?,?,?,?)");
		ps.setString(1, e.getEventID());
		ps.setString(2, e.getTitle());
		ps.setString(3, e.getVenue());
		ps.setInt(4, e.getTotalSeats());
		ps.setInt(5, e.getAvailableSeats());
		ps.setDate(6, new java.sql.Date(e.getEventDate().getTime()));

		boolean status = ps.executeUpdate() > 0;
		con.commit();
		con.close();
		return status;
	}

	public boolean updateAvailableSeats(String eventID, int count, Connection con) throws Exception {
		PreparedStatement ps = con.prepareStatement("UPDATE EVENT_TBL SET AVAILABLE_SEATS=? WHERE EVENT_ID=?");
		ps.setInt(1, count);
		ps.setString(2, eventID);
		return ps.executeUpdate() > 0;
	}

	public boolean deleteEvent(String eventID) throws Exception {
		Connection con = DBUtil.getDBConnection();
		PreparedStatement ps = con.prepareStatement("DELETE FROM EVENT_TBL WHERE EVENT_ID=?");
		ps.setString(1, eventID);
		boolean status = ps.executeUpdate() > 0;
		con.commit();
		con.close();
		return status;
	}
}
