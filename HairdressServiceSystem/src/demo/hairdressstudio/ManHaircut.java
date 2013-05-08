package demo.hairdressstudio;

public class ManHaircut implements Service{

	private String name;
	private long duration;
	private double price;
	
	public ManHaircut(String name, long duration, double price){
		this.name = name;
		this.duration = duration;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public long getDuration() {
		return duration;
	}

	public double getPrice() {
		return price;
	}

}
