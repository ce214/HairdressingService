package demo.hairdresstudio;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.ManHaircut;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.impl.HairdressStudioImpl;

public class HairdressStudioTest {

	@Test
	public void testSuccessfulReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 9, 00);
		Reservation reservation = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result = test.addReservation(reservation);
		Assert.assertTrue(result);
	}

	@Test
	public void testUnsuccessfulReservationBeforeStarTime() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 8, 59);
		Reservation reservation = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result = test.addReservation(reservation);
		Assert.assertFalse(result);
	}

	@Test
	public void testUnsuccessfulReservationAfterEndTime() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 17, 31);
		Reservation reservation = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result = test.addReservation(reservation);
		Assert.assertFalse(result);
	}

	@Test
	public void testSuccessfulReservationAfterAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 15, 00);
		Reservation reservation1 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result1 = test.addReservation(reservation1);
		initDate.set(2013, 04, 19, 15, 30);
		Reservation reservation2 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result2 = test.addReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertFalse(result2);
	}

	@Test
	public void testUnsuccessfulReservationAfterAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 15, 00);
		Reservation reservation1 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result1 = test.addReservation(reservation1);
		initDate.set(2013, 04, 19, 15, 29);
		Reservation reservation2 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result2 = test.addReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertFalse(result2);
	}

	@Test
	public void testSuccessfulReservationBeforeAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 15, 00);
		Reservation reservation1 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result1 = test.addReservation(reservation1);
		initDate.set(2013, 04, 19, 14, 30);
		Reservation reservation2 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result2 = test.addReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertFalse(result2);
	}

	@Test
	public void testUnsuccessfulReservationBeforeAnotherReservation() {
		HairdressStudio test = new HairdressStudioImpl();
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 15, 00);
		Reservation reservation1 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result1 = test.addReservation(reservation1);
		initDate.set(2013, 04, 19, 14, 31);
		Reservation reservation2 = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		boolean result2 = test.addReservation(reservation2);
		Assert.assertTrue(result1);
		Assert.assertFalse(result2);
	}

	@Test
	public void testSuccessfulDeletionOfReservation() {
		Calendar initDate = Calendar.getInstance();
		initDate.set(2013, 04, 19, 15, 00);
		Reservation reservation = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
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

		Reservation reservation = new Reservation("Asd", "0823", initDate,
				new ManHaircut("Hans", 30, 5));
		test.addReservation(reservation);
		for (Reservation res : test.listReservations()) {
			System.out.print(res.getName() + " " + res.getPhoneNumber() + " "
					+ res.getInitialHour().getTime());
		}

		Assert.assertEquals("Asd 0823 " + initDate.getTime(), os.toString());
		System.setOut(originalOut);
	}
}
