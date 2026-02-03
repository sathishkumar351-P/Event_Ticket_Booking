package com.event.app;

import java.util.Scanner;

import com.event.service.BookingService;

public class EventMain {
	private static BookingService bookingService; 
	public static void main(String[] args) { 
	bookingService = new BookingService(); 
	Scanner sc = new Scanner(System.in); 
	
	
	System.out.println("--- Event Booking Console ---");
	
	try { 
	boolean r = bookingService.bookTicket("EV2001","Spring Music Fest",2); 
	System.out.println(r ? "BOOKED" : "FAILED"); 
	} catch(Exception e) { System.out.println(e); } 

	try { 
	boolean r = bookingService.bookTicket("EV1001","Tech Conference",3); 
	System.out.println(r ? "BOOKED" : "FAILED"); 
	} catch(Exception e) { System.out.println(e); } 
	
	try { 
	boolean r = bookingService.cancelBooking(70001); 
	System.out.println(r ? " BOOKING CANCELLED" : "FAILED"); 
	} catch(Exception e) { System.out.println(e); } 
	sc.close(); 
	} 
	}


