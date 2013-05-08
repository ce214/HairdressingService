package demo.hairdressstudio;

public class HaircutFactory {

	public static Service createHaircurService(String name, long duration,
			double price) {
		if (duration < 45)
			return new ManHaircut(name, duration, price);
		else
			return new WomanHaircut(name, duration, price);
	}
}
