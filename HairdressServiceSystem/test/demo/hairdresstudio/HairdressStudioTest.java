package demo.hairdresstudio;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import demo.hairdressstudio.HaircutFactory;
import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.ManHaircut;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.impl.HairdressStudioImpl;

public class HairdressStudioTest {

	@Test
	public void testSuccessfulReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 1, 9, 00, 00);
		Reservation reservation = new Reservation("Test1", "0823", initDate,
				HaircutFactory.constructManHaircut());
		boolean result = test.addReservation(reservation);
		
		test.delReservation(reservation);
		Assert.assertTrue(result);		
	}

	@Test
	public void testUnsuccessfulReservationBeforeStarTime() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 2, 8, 59, 00);
		Reservation reservation = new Reservation("Test2", "0823", initDate,
				new ManHaircut(30, 5));
		boolean result = test.addReservation(reservation);
		
		test.delReservation(reservation);
		Assert.assertFalse(result);
	}

	@Test
	public void testUnsuccessfulReservationAfterEndTime() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 3, 17, 31, 00);
		Reservation reservation = new Reservation("Test3", "0823", initDate,
				new ManHaircut(30, 5));
		boolean result = test.addReservation(reservation);
		
		test.delReservation(reservation);
		Assert.assertFalse(result);
	}

	@Test
	public void testSuccessfulReservationAfterAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 4, 15, 00, 00);
		Reservation reservation1 = new Reservation("Test4", "0823", initDate,
				new ManHaircut(30, 5));
		boolean result1 = test.addReservation(reservation1);
		Calendar initDate2 = Calendar.getInstance();
		initDate2.set(2013, 04, 4, 15, 30, 00);
		Reservation reservation2 = new Reservation("Test4", "0823", initDate2,
				new ManHaircut(30, 5));
		boolean result2 = test.addReservation(reservation2);
		
		test.delReservation(reservation1);
		test.delReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
	}

	@Test
	public void testUnsuccessfulReservationAfterAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 5, 15, 00, 00);
		Reservation reservation1 = new Reservation("Test5", "0823", initDate,
				new ManHaircut(30, 5));
		boolean result1 = test.addReservation(reservation1);
		Calendar initDate2 = Calendar.getInstance();
		initDate2.set(2013, 04, 5, 15, 29, 00);
		Reservation reservation2 = new Reservation("Test5", "0823", initDate2,
				new ManHaircut(30, 5));
		boolean result2 = test.addReservation(reservation2);
		
		test.delReservation(reservation1);
		test.delReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertFalse(result2);
	}

	@Test
	public void testSuccessfulReservationBeforeAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 6, 15, 00, 00);
		Reservation reservation1 = new Reservation("Test6", "0823", initDate,
				new ManHaircut(30, 5));
		boolean result1 = test.addReservation(reservation1);
		Calendar initDate2 = Calendar.getInstance();
		initDate2.set(2013, 04, 6, 14, 30, 00);
		Reservation reservation2 = new Reservation("Test6", "0823", initDate2,
				new ManHaircut(30, 5));
		boolean result2 = test.addReservation(reservation2);
		
		test.delReservation(reservation1);
		test.delReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertTrue(result2);
	}

	@Test
	public void testUnsuccessfulReservationBeforeAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 7, 15, 00, 00);
		Reservation reservation1 = new Reservation("Test7", "0823", initDate,
				new ManHaircut(30, 5));
		boolean result1 = test.addReservation(reservation1);
		Calendar initDate2 = Calendar.getInstance();
		initDate2.set(2013, 04, 7, 14, 31, 00);
		Reservation reservation2 = new Reservation("Test7", "0823", initDate2,
				new ManHaircut(30, 5));
		boolean result2 = test.addReservation(reservation2);
		
		test.delReservation(reservation1);
		test.delReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertFalse(result2);
	}

	@Test
	public void testSuccessfulDeletionOfReservation() {
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 8, 15, 00, 00);
		Reservation reservation = new Reservation("Test8", "0823", initDate,
				new ManHaircut(30, 5));
		HairdressStudio test = new HairdressStudioImpl();
		test.addReservation(reservation);
		boolean result = test.delReservation(reservation);
		Assert.assertTrue(result);
	}

	@Test
	public void testSuccesfulListOfReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, initDate.get(Calendar.MONTH),
				initDate.get(Calendar.DAY_OF_MONTH) + 1, 15, 00, 00);
		PrintStream originalOut = System.out;
		OutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		System.setOut(ps);

		Reservation reservation = new Reservation("Test9", "0823", initDate,
				new ManHaircut(30, 5));
		test.addReservation(reservation);
		for (Reservation res : test.listReservations()) {
			System.out.print(res.getName() + " " + res.getPhoneNumber() + " "
					+ res.getInitialHour().getTime());
		}
		
		test.delReservation(reservation);
		Assert.assertEquals("Test9 0823 " + initDate.getTime(), os.toString());
		System.setOut(originalOut);		
	}

}


