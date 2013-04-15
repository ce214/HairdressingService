import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class mainClass {
	static String name;
	static String phoneNumber;
	static String reserveDate;

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
				System.out.print("Data za iztrivane: ");
				s.nextLine();
				delReserve(s.nextLine());
				break;
			}
			case 3: {
				Date date = new Date();
				showList(date);
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

		System.out.print("Ime: ");
		name = s.nextLine();

		System.out.print("Telefon: ");
		phoneNumber = s.nextLine();
		do {
			System.out.print("Data: ");
			reserveDate = s.nextLine();
		} while (!VerifyDate.isThisDateValid(reserveDate, "dd/MM/yy"));

		System.out.println("1. Mujka podstrijka");
		System.out.println("2. Jenska podstrijka");

		switch (s.nextInt()) {
		case 1: {
			Reservation reserve = new Reservation(name, phoneNumber,
					reserveDate);
			list.add(reserve);
			break;
		}
		case 2: {
			Reservation reserve = new Reservation(name, phoneNumber,
					reserveDate);
			list.add(reserve);
			break;
		}
		}
	}

	private static void delReserve(String date) {
		Iterator<Reservation> it = list.iterator();
		while (it.hasNext()) {
			if (it.next().getReserveDate().equals(date)) {
				it.remove();
				break;
			}
		}
	}

	private static void showList(Date date) {
		System.out.println("Dnes e: " + date);
		Iterator<Reservation> it = list.iterator();
		while (it.hasNext()) {
			Reservation reserve = it.next();
			System.out.println(reserve.getReserveDate());
		}
	}

}
