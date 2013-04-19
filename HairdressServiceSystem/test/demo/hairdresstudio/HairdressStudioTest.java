package demo.hairdresstudio;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.impl.HairdressStudioImpl;

public class HairdressStudioTest {
	
	@Test
	public void testSuccessfulReservation(){
		HairdressStudio test = new HairdressStudioImpl();
		Reservation reservation = new Reservation("Asd", "0823", new Date(2013, 04, 19, 13, 00), new Date(2013, 04, 19, 13, 30));
		
		boolean result = test.addReservation(reservation);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testUnsuccessfulReservationBeforeStarTime(){
		HairdressStudio test = new HairdressStudioImpl();
		Reservation reservation = new Reservation("Asd", "0823", new Date(2013, 04, 19, 8, 59), new Date(2013, 04, 19, 13, 30));
		
		boolean result = test.addReservation(reservation);
		
		Assert.assertFalse(result);
	}
	
	@Test
	public void testUnsuccessfulReservationAfterEndTime(){
		HairdressStudio test = new HairdressStudioImpl();
		Reservation reservation = new Reservation("Asd", "0823", new Date(2013, 04, 19, 15, 00), new Date(2013, 04, 19, 18, 01));
		
		boolean result = test.addReservation(reservation);
		
		Assert.assertFalse(result);
	}

}
