package demo.hairdressstudio;

public class WomanHaircut implements Service{
	
	private long duration;
	private double price ;
	
	public WomanHaircut(long duration, double price){
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
