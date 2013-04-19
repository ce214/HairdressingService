package demo.hairdresstudio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.ManHaircut;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.impl.HairdressStudioImpl;

public class HairdressStudioTest {
	
	@Test
	public void testSuccessfulReservation(){
		HairdressStudio test = new HairdressStudioImpl();
		Reservation reservation = new Reservation("Asd", "0823", new Date(2013, 04, 19, 13, 00), new Date(2013, 04, 19, 13, 30), new ManHaircut("Hans", 30, 5));		
		boolean result = test.addReservation(reservation);		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testUnsuccessfulReservationBeforeStarTime(){
		HairdressStudio test = new HairdressStudioImpl();
		Reservation reservation = new Reservation("Asd", "0823", new Date(2013, 04, 19, 8, 59), new Date(2013, 04, 19, 13, 30), new ManHaircut("Hans", 30, 5));		
		boolean result = test.addReservation(reservation);		
		Assert.assertFalse(result);
	}
	
	@Test
	public void testUnsuccessfulReservationAfterEndTime(){
		HairdressStudio test = new HairdressStudioImpl();
		Reservation reservation = new Reservation("Asd", "0823", new Date(2013, 04, 19, 15, 00), new Date(2013, 04, 19, 18, 01), new ManHaircut("Hans", 30, 5));	
		boolean result = test.addReservation(reservation);	
		Assert.assertFalse(result);
	}

	@Test
	public void testSuccessfulReservationAfterAnotherReservation(){
		List<Reservation> list = new ArrayList<Reservation>();
		@SuppressWarnings("unused")
		Reservation reservation1 = new Reservation("Asd", "0823", new Date(2013, 04, 19, 15, 00), new Date(2013, 04, 19, 15, 30), new ManHaircut("Hans", 30, 5));
		Reservation reservation2 = new Reservation("Asd", "0823", new Date(2013, 04, 19, 15, 30), new Date(2013, 04, 19, 16, 00), new ManHaircut("Hans", 30, 5));
		list.add(reservation1);
		HairdressStudio test = new HairdressStudioImpl(list);
		boolean result = test.addReservation(reservation2);	
		Assert.assertTrue(result);
	}
	
	@Test
	public void testUnsuccessfulReservationAfterAnotherReservation(){
		List<Reservation> list = new ArrayList<Reservation>();
		@SuppressWarnings("unused")
		Reservation reservation1 = new Reservation("Asd", "0823", new Date(2013, 04, 19, 15, 00), new Date(2013, 04, 19, 15, 30), new ManHaircut("Hans", 30, 5));
		Reservation reservation2 = new Reservation("Asd", "0823", new Date(2013, 04, 19, 15, 29), new Date(2013, 04, 19, 15, 59), new ManHaircut("Hans", 30, 5));
		list.add(reservation1);
		HairdressStudio test = new HairdressStudioImpl(list);
		boolean result = test.addReservation(reservation2);	
		Assert.assertFalse(result);
	}
}
