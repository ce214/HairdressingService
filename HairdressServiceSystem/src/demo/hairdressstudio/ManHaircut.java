package demo.hairdressstudio;

public class ManHaircut implements Service{

	private long duration;
	private double price;
	
	public ManHaircut(long duration, double price){
		this.duration = duration;
		this.price = price;
	}

	public long getDuration() {
		return duration;
	}

	public double getPrice() {
		return price;
	}

}
