package demo.hairdressstudio.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import demo.hairdressstudio.HaircutFactory;
import demo.hairdressstudio.HairdressStudio;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.Service;

public class HairdressStudioConsoleClient {
	static String name;
	static String phoneNumber;
	static String reserveDate;
	static String hour;
	static Calendar initialHour = Calendar.getInstance();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
	static HairdressStudio hdstudio = new HairdressStudioImpl();
	static Service service;
	
	static void Menu() {
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
		Reservation reserve = null;
		
		Scanner s = new Scanner(System.in);

		System.out.println("1. Mujka podstrijka");
		System.out.println("2. Jenska podstrijka");

		switch (s.nextInt()) {
		case 1:
			service = HaircutFactory.constructManHaircut();
			inputReserve(service.getDuration());
			reserve = HaircutFactory.constructReservation(name, phoneNumber, initialHour, service);
			break;
		case 2: 
			service = HaircutFactory.constructWomanHaircut();
			inputReserve(service.getDuration());
			reserve = HaircutFactory.constructReservation(name, phoneNumber, initialHour, service);
			break;
		}
		if(hdstudio.addReservation(reserve))
			System.out.println("Success!");
		else
			System.out.println("No Success!");
	}

	private static void inputReserve(long duration) {
		Scanner s = new Scanner(System.in);

		System.out.print("Ime: ");
		name = s.nextLine();

		System.out.print("Telefon: ");
		phoneNumber = s.nextLine();
		do {
			System.out.print("Data: ");
			reserveDate = s.nextLine();
		} while (!UserInputValidation.isThisDateValid(reserveDate));

		do {
			System.out.print("4as: ");
			hour = s.nextLine();
		} while (!UserInputValidation.isThisHourValid(hour));

		try {
			initialHour.setTime(sdf.parse(reserveDate + " " + hour));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private static void delReserve() {
		Scanner s = new Scanner(System.in);

		Date dateToDel = new Date();
		do {
			System.out.print("Data: ");
			reserveDate = s.nextLine();
		} while (!UserInputValidation.isThisDateValid(reserveDate));

		do {
			System.out.print("4as: ");
			hour = s.nextLine();
		} while (!UserInputValidation.isThisHourValid(hour));
		try {
			dateToDel = sdf.parse(reserveDate + " " + hour);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Searches the list and deletes reservation
		Iterator<Reservation> it = hdstudio.getList().iterator();
		Reservation reserve = it.next();
		while (it.hasNext()) {
			if (reserve.getInitialHour().equals(dateToDel)) {
				hdstudio.delReservation(reserve);
			}
		}
	}

	private static void showList() {
		for (Reservation res : hdstudio.listReservations()) {
			System.out.println(res.getName() + " " + res.getPhoneNumber() + " "
					+ res.getInitialHour().getTime());
		}
	}

}
