import java.io.IOException;
import java.util.ArrayList;
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
		while (true) {
			System.out.println("1. Nova rezervaciq");
			System.out.println("2. Anulirane na rezervaciq");
			System.out.println("3. Spisuk s poseshteniq");
			System.out.println("0. Izhod");
			try {
				switch (System.in.read()) {
				case '1': {
					reserve();
					break;
				}
				case '2': {
					// anulirane
					break;
				}
				case '3': {
					// spisuk
					break;
				}
				case '0': {
					System.exit(1);
				}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void reserve() {
		Scanner s = new Scanner(System.in);
		s.nextLine();

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

}
