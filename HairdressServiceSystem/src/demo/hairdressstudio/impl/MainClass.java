package demo.hairdressstudio.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import demo.hairdressstudio.ManHaircut;
import demo.hairdressstudio.Reservation;
import demo.hairdressstudio.WomanHaircut;

public class MainClass {
	static String name;
	static String phoneNumber;
	static String reserveDate;
	static String hour;
	static Date initialHour;
	static Date finalHour;
	static final int man = 30;
	static final int woman = 45;
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
	private static List<Reservation> list = new ArrayList<Reservation>();

	public static void main(String[] args) {
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
			list.add(reserve);
			break;
		}
		case 2: {
			inputReserve(woman);

			Reservation reserve = new Reservation(name, phoneNumber,
					initialHour, finalHour, new WomanHaircut(name, 45, 10));
			list.add(reserve);
			break;
		}
		}
	}

	private static void inputReserve(int minToAdd) {
		Scanner s = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");

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
			initialHour = sdf.parse(reserveDate + " " + hour);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finalHour = VerifyDate.add(initialHour, Calendar.MINUTE, minToAdd);

		if (!VerifyDate.isReservationOK(initialHour, finalHour,
				list)) {
			System.out.println("Chasut e zaet ili ne e v rabotno vreme!");
			Menu();
		}
	}

	private static void delReserve() {
		Scanner s = new Scanner(System.in);
		String dateToDelStr = s.nextLine();

		Date dateToDel = new Date();
		try {
			dateToDel = sdf.parse(dateToDelStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Iterator<Reservation> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().getInitialHour().equals(dateToDel)) {
				it.remove();
				break;
			}
		}
	}

	private static void showList() {
		Collections.sort(list);

		Date currDate = new Date();
		System.out.println("Dnes e: " + currDate);

		Calendar curr = Calendar.getInstance();
		curr.setTime(currDate);

		for (int i = 0; i < 4; i++) {
			Iterator<Reservation> it = list.iterator();
			while (it.hasNext()) {
				Reservation reserve = it.next();

				if (reserve.getDate().get(Calendar.DATE) == curr.get(Calendar.DATE)) {
					System.out.println(reserve.getName() + " "
							+ reserve.getPhoneNumber());
					System.out.println(reserve.getInitialHour().toString()
							+ " - " + reserve.getFinalHour().toString());
				}
			}
			curr.add(Calendar.DAY_OF_WEEK, 1);
		}
	}

}
