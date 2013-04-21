package demo.hairdressstudio.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.ManHaircut;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.WomanHaircut;

public class MainClass {
	static String name;
	static String phoneNumber;
	static String reserveDate;
	static String hour;
	static Calendar initialHour = Calendar.getInstance();
	static Calendar finalHour = Calendar.getInstance();
	static final int man = 30;
	static final int woman = 45;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
	static HairdressStudio hdstudio;

	public static void main(String[] args) {
		hdstudio = new HairdressStudioImpl();
		Menu();
	}

	private static void Menu() {
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("1. Nova rezervaciq");
			System.out.println("2. Anulirane na rezervaciq");
			System.out.println("3. Spisuk s poseshteniq");
			System.out.println("0. Izhod");
			switch (s.nextInt()) {
			case 1: {
				reserve();
				break;
			}
			case 2: {
				delReserve();
				break;
			}
			case 3: {
				showList();
				break;
			}
			case 0: {
				s.close();
				System.exit(1);
			}
			}
		}
	}

	private static void reserve() {
		Scanner s = new Scanner(System.in);

		System.out.println("1. Mujka podstrijka");
		System.out.println("2. Jenska podstrijka");

		switch (s.nextInt()) {
		case 1: {
			inputReserve(man);

			Reservation reserve = new Reservation(name, phoneNumber,
					initialHour, finalHour, new ManHaircut(name, 30, 5));
			hdstudio.addReservation(reserve);
			break;
		}
		case 2: {
			inputReserve(woman);

			Reservation reserve = new Reservation(name, phoneNumber,
					initialHour, finalHour, new WomanHaircut(name, 45, 10));
			hdstudio.addReservation(reserve);
			break;
		}
		}
	}

	private static void inputReserve(int minToAdd) {
		Scanner s = new Scanner(System.in);

		System.out.print("Ime: ");
		name = s.nextLine();

		System.out.print("Telefon: ");
		phoneNumber = s.nextLine();
		do {
			System.out.print("Data: ");
			reserveDate = s.nextLine();
		} while (!VerifyDate.isThisDateValid(reserveDate));

		do {
			System.out.print("4as: ");
			hour = s.nextLine();
		} while (!VerifyDate.isThisHourValid(hour));

		try {
			initialHour.setTime(sdf.parse(reserveDate + " " + hour));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finalHour.set(initialHour.get(Calendar.YEAR),
				initialHour.get(Calendar.MONTH),
				initialHour.get(Calendar.DAY_OF_MONTH),
				initialHour.get(Calendar.HOUR_OF_DAY),
				initialHour.get(Calendar.MINUTE),
				initialHour.get(Calendar.SECOND));
		finalHour.add(Calendar.MINUTE, minToAdd);

		if (!VerifyDate.isReservationOK(initialHour, finalHour,
				hdstudio.getList())) {
			System.out.println("Chasut e zaet ili ne e v rabotno vreme!");
			Menu();
		}
	}

	private static void delReserve() {
		Scanner s = new Scanner(System.in);

		Date dateToDel = new Date();
		do {
			System.out.print("Data: ");
			reserveDate = s.nextLine();
		} while (!VerifyDate.isThisDateValid(reserveDate));

		do {
			System.out.print("4as: ");
			hour = s.nextLine();
		} while (!VerifyDate.isThisHourValid(hour));
		try {
			dateToDel = sdf.parse(reserveDate + " " + hour);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		hdstudio.delReservation(ReservationManipulation.ReservationToBeDeleted(
				dateToDel, hdstudio.getList()));
	}

	private static void showList() {
		for (Reservation res : hdstudio.listReservations()) {
			System.out.println(res.getName() + " " + res.getPhoneNumber() + " "
					+ res.getInitialHour().getTime() + " -> "
					+ res.getFinalHour().getTime());
		}
	}

}
